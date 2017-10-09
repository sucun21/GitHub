delimiter //

DROP PROCEDURE IF EXISTS web_cms_student_update_course_status;
//

CREATE PROCEDURE `web_cms_student_update_course_status`(
	p_course_trainee_id INT,
    p_current_status BIT(1),
    p_new_status BIT(1)
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
    
    IF NOT EXISTS (SELECT 1 FROM sg_course_trainee WHERE course_trainee_id = p_course_trainee_id AND is_delete = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Trainee course does not exist';
    ELSEIF NOT EXISTS (SELECT 1 FROM sg_course_trainee WHERE course_trainee_id = p_course_trainee_id AND is_active = p_current_status) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Trainee course status has changed';
	END IF;
    

	   UPDATE sg_course_trainee
       SET is_active = p_new_status
       WHERE course_trainee_id = p_course_trainee_id;
        
        SELECT 1 `id`,
			   'Trainee course status updated' `message`;
END
//

delimiter ;