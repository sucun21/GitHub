delimiter //

DROP PROCEDURE IF EXISTS sp_update_student_uncomplete_online_unit;
//

CREATE PROCEDURE `sp_update_student_uncomplete_online_unit`(
)
BEGIN
	
    DECLARE v_job_name TEXT DEFAULT 'update_student_uncomplete_online_unit';
    DECLARE v_ret_msg TEXT DEFAULT 'Execute job success';
    DECLARE v_ret_id INT DEFAULT -1;
	DECLARE v_end_cursor BOOLEAN DEFAULT FALSE;
    DECLARE v_course_trainee_id INT;
    
    DECLARE v_current_unit_id INT;
    DECLARE v_current_lesson_id INT;
    DECLARE v_current_activity_id INT;
    
    DECLARE v_unit_id INT;
    DECLARE v_lesson_id INT;
    DECLARE v_activity_id INT;
    DECLARE v_unit_remain_time TIME;
    
	DEClARE student_cur CURSOR FOR 
	SELECT str.course_trainee_id, str.current_unit_id, str.current_lesson_id, str.next_activity_id
    FROM sg_trainee_result str
	JOIN sg_offline_class_trainee soct ON soct.unit_id = str.current_unit_id
										  AND soct.course_trainee_id = str.course_trainee_id
										  AND soct.is_cancel = 0 
										  AND soct.is_delete = 0
										  AND soct.attended_date <= NOW()
	WHERE str.current_unit_id is not null;
    
    /**Delare handler sql_exception*/
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
	  
      GET DIAGNOSTICS CONDITION 1 v_ret_msg = MESSAGE_TEXT;
      
	  ROLLBACK;
		/*ghi log job*/
        INSERT INTO job_log(job_name, log_content, is_success)
        VALUE(v_job_name, v_ret_msg, 0);
      
	END;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_end_cursor = TRUE;
	
	/**Start transaction*/
	START TRANSACTION;
        
        OPEN student_cur;
        
        get_student: LOOP
			
			FETCH student_cur INTO v_course_trainee_id, v_current_unit_id, v_current_lesson_id, v_current_activity_id;

			IF v_end_cursor THEN LEAVE get_student;
			END IF;
            
            SET v_unit_id := null;
            SET v_lesson_id := null;
            SET v_activity_id := null;
            
            SELECT unit_id INTO v_unit_id
            FROM sg_offline_class_trainee
			WHERE course_trainee_id = v_course_trainee_id
			AND is_cancel = 0
			AND is_delete = 0
			AND attended_date > NOW()
			ORDER BY attended_date asc
			LIMIT 1;
            
            IF v_unit_id IS NOT NULL THEN
				
                SELECT lesson_id INTO v_lesson_id
                FROM sg_lesson sl 
                WHERE sl.unit_id = v_unit_id AND sl.is_active = 1 AND sl.is_delete = 0
				ORDER BY IFNULL(sl.sort_order, 9999) ASC, created_date ASC
				LIMIT 1;
                
                SELECT activity_id INTO v_activity_id
                FROM sg_activity sa 
				WHERE sa.lesson_id = v_lesson_id
				ORDER BY IFNULL(sa.sort_order, 9999) ASC, created_date ASC
				LIMIT 1;
                
                SELECT SEC_TO_TIME(sum(activity_time) * 60)
                INTO v_unit_remain_time
				FROM sg_activity sa 
				WHERE sa.lesson_id = v_lesson_id;
                
                /*update reverse info*/
                UPDATE sg_trainee_unit_completed
                SET reserve_lesson_id = v_current_lesson_id,
					reserve_activity_id = v_current_activity_id
                WHERE course_trainee_id = v_course_trainee_id
					  AND unit_completed_id = v_current_unit_id;
                
                /*insert sg_trainee_lesson*/
				INSERT INTO sg_trainee_lesson(
					course_trainee_id,
					lesson_id,
					lesson_title,
					lesson_des,
					unit_id,
					unit_title,
					unit_des
				)
				SELECT v_course_trainee_id,
					   sl.lesson_id,
					   sl.lesson_title,
					   sl.lesson_des,
					   su.unit_id,
					   su.unit_title,
					   su.unit_des
				FROM sg_unit su
				JOIN sg_lesson sl ON sl.unit_id = su.unit_id
									 AND sl.lesson_id = v_lesson_id
				LIMIT 1;
                
			    /*insert sg_trainee_unit_completed*/
				INSERT INTO sg_trainee_unit_completed(
					course_trainee_id,
					unit_completed_id
				)
				SELECT v_course_trainee_id,
					   su.unit_id
				FROM sg_unit su
				WHERE su.unit_id = v_unit_id;
                
                /*update sg_trainee_result*/
				update sg_trainee_result
				SET current_unit_id = v_unit_id,
					current_lesson_id = v_lesson_id,
                    next_activity_id = v_activity_id,
                    unit_remain_time = v_unit_remain_time
                WHERE course_trainee_id = v_course_trainee_id;
                
            END IF;
            
		END LOOP get_student;

		CLOSE student_cur;
        
        /*ghi log job
        INSERT INTO job_log(job_name, log_content)
        VALUE(v_job_name, v_ret_msg);*/
        
    COMMIT;

    
END
//

delimiter ;