delimiter //

DROP PROCEDURE IF EXISTS web_cms_update_student_attendance;
//

CREATE PROCEDURE `web_cms_update_student_attendance`(
	p_class_id INT,
    p_trainer_id INT,
    p_student_id INT,
    p_status BIT
)
BEGIN
    
	DECLARE errorText TEXT;
    
    /**Delare handler sql_exception*/
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
	  GET DIAGNOSTICS CONDITION 1 errorText = MESSAGE_TEXT;
		  SELECT -1 `id`,
				errorText `message`;
	  ROLLBACK;
	END;
    
    IF NOT EXISTS (SELECT 1 FROM sg_offline_class WHERE class_id = p_class_id AND is_delete = 0 AND is_active = 1 AND trainer_id = p_trainer_id) THEN
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
	END IF;
    

	   UPDATE sg_offline_class_trainee soct, sg_course_trainee sct
       SET soct.is_attended = p_status
	   WHERE class_id = p_class_id 
			 AND sct.course_trainee_id = soct.course_trainee_id
			 AND sct.trainee_id = p_student_id 
			 AND soct.is_cancel = 0
			 AND soct.is_delete = 0;
        
        SELECT 1 `id`,
				'Student attendance checked' `message`;
END
//

delimiter ;