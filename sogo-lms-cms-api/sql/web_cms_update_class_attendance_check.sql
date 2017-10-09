delimiter //

DROP PROCEDURE IF EXISTS web_cms_update_class_attendance_check;
//

CREATE PROCEDURE `web_cms_update_class_attendance_check`(
	p_class_id INT,
    p_trainer_id INT
)
BEGIN
    
	DECLARE errorText TEXT;
    DECLARE v_end_cursor BOOLEAN DEFAULT FALSE;
    DECLARE v_trainee_id INT;
    DECLARE v_attend_status BOOLEAN;
    
	DEClARE student_cur CURSOR FOR 
	SELECT sct.trainee_id, soct.is_attended
    FROM sg_offline_class_trainee soct
    JOIN sg_course_trainee sct on sct.course_trainee_id = soct.course_trainee_id
    WHERE soct.class_id = p_class_id
		  AND soct.is_cancel = 0
          AND soct.is_delete = 0;
    
    /**Delare handler sql_exception*/
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
	  GET DIAGNOSTICS CONDITION 1 errorText = MESSAGE_TEXT;
		  SELECT -1 `id`,
				errorText `message`;
	  ROLLBACK;
	END;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_end_cursor = TRUE;
    
	IF NOT EXISTS (SELECT 1 FROM sg_offline_class WHERE class_id = p_class_id AND is_delete = 0 AND is_active = 1 AND trainer_id = p_trainer_id) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'This is not your offline class';
    ELSEIF NOT EXISTS (SELECT 1 FROM sg_offline_class WHERE class_id = p_class_id AND is_delete = 0 AND is_active = 1) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Offline class not exists';
	    ELSEIF NOT EXISTS (SELECT 1 FROM sg_offline_class WHERE class_id = p_class_id AND is_delete = 0 AND is_active = 1 AND is_attendance_check = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Class is already checked';
    ELSEIF (SELECT COUNT(1) FROM sg_offline_class_trainee 
							WHERE class_id = p_class_id 
								  AND is_cancel = 0 
                                  AND is_delete = 0 
                                  AND is_attended is null) > 0 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Class has not check attendance student';
	END IF;
    
	/**Start transaction*/
	START TRANSACTION;

		/*update attendance check*/
		UPDATE sg_offline_class
        SET is_attendance_check = 1
        WHERE class_id = p_class_id;
		
        OPEN student_cur;
        
        get_student: LOOP
			
			FETCH student_cur INTO v_trainee_id, v_attend_status;

			IF v_end_cursor THEN LEAVE get_student;
			END IF;
            
            /*update student result*/

			IF v_attend_status THEN
				/*insert sg_trainee_timeline Unit Completed*/
				INSERT INTO sg_trainee_timeline (timeline_type_id, 
												 trainee_id, 
												 course_id, 
												 post_content, 
												 post_by, 
												 post_by_name, 
												 is_show_timeline,
                                                 course_trainee_id) 
				SELECT 4, 
					   v_trainee_id, 
					   sct.course_id, 
					   concat('You just completed unit - ', sun.unit_title), 
					   v_trainee_id, 
					   su.full_name, 
					   1,
                       sct.course_trainee_id
				FROM sg_offline_class_trainee soct
				JOIN sg_unit sun on sun.unit_id = soct.unit_id
				JOIN sg_course_trainee sct on sct.course_trainee_id = soct.course_trainee_id
				JOIN sg_user su on su.user_id = sct.trainee_id
				where soct.class_id = p_class_id
					  AND sct.trainee_id = v_trainee_id;
			END IF;

		END LOOP get_student;

		CLOSE student_cur;
        
        SELECT 1 `id`,
				'Class attendance check saved' `message`;
        
	COMMIT;
END
//

delimiter ;