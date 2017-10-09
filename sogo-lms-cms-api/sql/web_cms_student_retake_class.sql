delimiter //

DROP PROCEDURE IF EXISTS web_cms_student_retake_class;
//

CREATE PROCEDURE `web_cms_student_retake_class`(
	p_course_trainee_id INT,
    p_old_class_id INT,
    p_class_id INT,
    p_unit_id INT,
    p_created_by INT
)
BEGIN
	
    DECLARE errorText TEXT;
    DECLARE v_student_has_miss_class INT;
    DECLARE v_is_retake_old_class INT;
    DECLARE v_is_class_exist INT;
    DECLARE v_is_class_taken_place INT;
    DECLARE v_is_class_full INT;
    DECLARE v_from_class_time TIMESTAMP;
    DECLARE v_from_class_name TEXT;
    DECLARE v_contract_no TEXT;
    
    /**Delare handler sql_exception*/
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
	  GET DIAGNOSTICS CONDITION 1 errorText = MESSAGE_TEXT;
		  SELECT -1 `id`,
				errorText `message`;
                
		ROLLBACK;
	END;
    
    SELECT COUNT(1) INTO v_student_has_miss_class
    FROM sg_offline_class_trainee
    WHERE is_attended = 0
		  AND class_id = p_old_class_id
          AND course_trainee_id = p_course_trainee_id;
          
	SELECT COUNT(1) INTO v_is_retake_old_class
    FROM sg_offline_class_trainee
    WHERE is_attended = 0
		  AND IFNULL(is_retake, 0) = 1
		  AND class_id = p_old_class_id
          AND course_trainee_id = p_course_trainee_id;
    
    
    SELECT COUNT(1),
		   (CASE WHEN addtime(soc.study_date, soc.study_time) <= NOW() THEN 1 ELSE 0 END),
           (CASE WHEN soc.total_user_booked >= sogc.limit_user THEN 1 ELSE 0 END)
    INTO v_is_class_exist,
         v_is_class_taken_place,
         v_is_class_full
    FROM sg_offline_class soc
    JOIN sg_offline_group_class sogc ON sogc.group_class_id = soc.group_class_id
    WHERE soc.class_id = p_class_id 
		  AND soc.is_delete = 0 
          AND soc.is_active = 1;
    
    
    IF v_student_has_miss_class = 0 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Student has attended this class';
	ELSEIF v_is_retake_old_class = 1 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Class already retaken';
    ELSEIF v_is_class_exist = 0 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Class does not exist';
    ELSEIF v_is_class_taken_place = 1 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Class already taken place';
	ELSEIF v_is_class_full = 1 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Class already full';
	END IF;
    
    /**Start transaction*/
	START TRANSACTION;
    
    /*insert trainee class*/
    INSERT INTO sg_offline_class_trainee(class_id, 
										 course_trainee_id, 
                                         unit_id,
										 created_by)
	SELECT class_id,
		   p_course_trainee_id,
           unit_id,
           p_created_by
    FROM sg_offline_class
    WHERE class_id = p_class_id;
    
    /*update old trainee class*/
    UPDATE sg_offline_class_trainee
    SET is_retake = 1,
		modified_date = NOW(),
        modified_by = p_created_by
    WHERE class_id = p_old_class_id
		  AND course_trainee_id = p_course_trainee_id;
    
    /*update sg_offline_class*/
    UPDATE sg_offline_class
    SET total_user_booked = IFNULL(`total_user_booked`, 0) + 1,
		modified_date = NOW(),
        modified_by = p_created_by
    WHERE class_id = p_class_id;
    
    SELECT TIMESTAMP(ADDTIME(soc.study_date, soc.study_time)),
			sogc.group_class_name
    INTO v_from_class_time,
		 v_from_class_name
    FROM sg_offline_class soc
    JOIN sg_offline_group_class sogc on sogc.group_class_id = soc.group_class_id
    WHERE class_id = p_old_class_id;
    
    SELECT contract_no
    INTO v_contract_no
    FROM sg_course_trainee
    WHERE course_trainee_id = p_course_trainee_id;
    
    /*insert sg_offline_class_retake_tracker*/
    INSERT INTO sg_offline_class_retake_tracker(course_trainee_id,
												contract_no,
                                                unit_id,
                                                unit_title,
                                                from_class_id,
                                                from_class_time,
                                                from_class_name,
                                                to_class_id,
                                                to_class_time,
                                                to_class_name,
                                                created_by)
	SELECT p_course_trainee_id,
		   v_contract_no,
           soc.unit_id,
           su.unit_title,
           p_old_class_id,
           v_from_class_time,
           v_from_class_name,
           p_class_id,
           TIMESTAMP(ADDTIME(soc.study_date, soc.study_time)),
           sogc.group_class_name,
           p_created_by
    FROM sg_offline_class soc
    JOIN sg_offline_group_class sogc on sogc.group_class_id = soc.group_class_id
    JOIN sg_unit su on su.unit_id = soc.unit_id
    WHERE soc.class_id = p_class_id;
    
    /*update sg_trainee_unit_completed*/
    UPDATE sg_trainee_unit_completed
    SET is_delete = 1
    WHERE unit_completed_id = p_unit_id
		  AND course_trainee_id = p_course_trainee_id;
          
	/*update sg_trainee_lesson*/
    UPDATE sg_trainee_lesson
    SET is_delete = 1
    WHERE lesson_id IN (select lesson_id FROM sg_lesson WHERE unit_id = p_unit_id)
		  AND course_trainee_id = p_course_trainee_id;
	
    CREATE TEMPORARY TABLE IF NOT EXISTS v_tbl_trainee_activity_id 
    AS (SELECT trainee_activity_id 
		FROM sg_trainee_activity
        WHERE course_trainee_id = p_course_trainee_id
			  AND lesson_id IN (select lesson_id FROM sg_lesson WHERE unit_id = p_unit_id));
              
	CREATE TEMPORARY TABLE IF NOT EXISTS v_tbl_activity 
    AS (SELECT activity_id 
		FROM sg_activity
        WHERE lesson_id IN (select lesson_id FROM sg_lesson WHERE unit_id = p_unit_id));
    
    /*update sg_trainee_activity*/
    UPDATE sg_trainee_activity
    SET is_delete = 1
    WHERE trainee_activity_id IN (SELECT trainee_activity_id FROM v_tbl_trainee_activity_id);
    
	/*update sg_trainee_activity_detail*/
    UPDATE sg_trainee_activity_detail
    SET is_delete = 1
    WHERE trainee_activity_id IN (SELECT trainee_activity_id FROM v_tbl_trainee_activity_id);
    
    /*update sg_trainee_property*/
    UPDATE sg_trainee_property
    SET is_delete = 1
    WHERE course_trainee_id = p_course_trainee_id
		  AND activity_id IN (SELECT activity_id FROM v_tbl_activity);
    
    SELECT 1 `id`,
		   'Class retake success' `message`;
    
    COMMIT;
    
END
//

delimiter ;