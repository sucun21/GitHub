delimiter //

DROP PROCEDURE IF EXISTS web_cms_reset_student_password;
//

CREATE PROCEDURE `web_cms_reset_student_password`(
	p_action_user INT,
	p_student_id INT,
    p_new_password VARCHAR(200)
)
BEGIN
    
	DECLARE errorText TEXT;
    
    /**Delare handler sql_exception*/
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
	  GET DIAGNOSTICS CONDITION 1 errorText = MESSAGE_TEXT;
		  SELECT -1 `id`,
				errorText `message`;
	END;
	
    IF NOT EXISTS (SELECT 1 FROM sg_user WHERE user_id = p_action_user AND is_delete = 0 AND user_type_id IN (1)) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient permissions';
    ELSEIF NOT EXISTS (SELECT 1 FROM sg_user WHERE user_id = p_student_id AND is_delete = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid student';
	ELSEIF p_new_password IS NULL OR p_new_password = '' THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid password';
	END IF;
    
    UPDATE sg_user
    SET `password` = p_new_password
    WHERE user_id = p_student_id;
    
    SELECT 1 `id`,
			  'Student password reset' `message`;
END
//

delimiter ;