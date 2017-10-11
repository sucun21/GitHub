delimiter //

DROP PROCEDURE IF EXISTS web_cms_add_activity_log;
//

CREATE PROCEDURE `web_cms_add_activity_log`(
	p_comment_content VARCHAR(200),
    p_comment_type_id INT,
    p_comment_result_id INT,
    p_trainer_id INT,
    p_course_trainee_id INT,
    p_student_id INT,
    p_is_add_timeline BIT
)
BEGIN
    
	DECLARE errorText TEXT;
    DECLARE v_new_comment_id INT;
    DECLARE v_userFullName TEXT;
    DECLARE v_courseId INT;
    DECLARE v_trainerName TEXT;
    
    /**Delare handler sql_exception*/
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
	  GET DIAGNOSTICS CONDITION 1 errorText = MESSAGE_TEXT;
		  SELECT -1 `id`,
				errorText `message`;
	  ROLLBACK;
	END;
    
	SET v_userFullName = (SELECT full_name
    from sg_user
    where user_id = p_student_id);
    
    SELECT full_name INTO v_trainerName
    FROM sg_user
    WHERE user_id = p_trainer_id;
    
    SET v_courseId = (SELECT course_id
    from sg_course_trainee
    where course_trainee_id = p_course_trainee_id);
    
    /*IF NOT EXISTS (SELECT 1 FROM sg_offline_class WHERE class_id = p_class_id AND is_delete = 0 AND is_active = 1 AND trainer_id = p_trainer_id) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'This is not your offline class';
    ELSEIF NOT EXISTS (SELECT 1 FROM sg_offline_class WHERE class_id = p_class_id AND is_delete = 0 AND is_active = 1) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Offline class not exists';
    ELSEIF NOT EXISTS (SELECT 1 
				   FROM sg_offline_class_trainee soct
                   JOIN sg_course_trainee sct on sct.course_trainee_id = soct.course_trainee_id
				   WHERE class_id = p_class_id 
						 AND sct.trainee_id = p_student_id 
                         AND soct.is_cancel = 0
                         AND soct.is_delete = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Student not exist in class';
	END IF;*/
    
    /**Start transaction*/
		START TRANSACTION;
		
        /*insert comment*/
        INSERT INTO sg_comment (comment_type_id, 
								comment_result_id, 
                                trainer_id, 
                                trainee_id,
                                course_id,
                                comment_body, 
                                is_show_timeline, 
                                created_by)
		VALUE(p_comment_type_id,
			  p_comment_result_id,
              p_trainer_id,
              p_student_id,
              v_courseId,
              p_comment_content,
              p_is_add_timeline,
              p_trainer_id);
        
        
		/*set new comment id*/
		SET v_new_comment_id = LAST_INSERT_ID();
        
        /*insert sg_trainee_timeline activity log*/
		INSERT INTO sg_trainee_timeline (timeline_type_id, 
										 trainee_id, 
										 course_id, 
										 post_content, 
										 post_by, 
										 post_by_name, 
										 is_show_timeline,
										 comment_id,
                                         course_trainee_id) 
		VALUE (6, 
			   p_student_id, 
			   v_courseId, 
			   CONCAT('<b>Your trainer ', v_trainerName, ' wants to tell you something:</b>\r\n', p_comment_content), 
			   p_student_id, 
			   v_userFullName, 
			   1,
			   v_new_comment_id,
               p_course_trainee_id);
	   
        
        SELECT CAST(v_courseId as signed) `id`,
				'Activity log added' `message`;
				/**Commit update and insert*/
		COMMIT;
END
//

delimiter ;