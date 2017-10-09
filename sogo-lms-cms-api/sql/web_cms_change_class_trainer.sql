delimiter //

DROP PROCEDURE IF EXISTS web_cms_change_class_trainer;
//

CREATE PROCEDURE `web_cms_change_class_trainer`(
	p_is_admin BIT,
	p_class_id INT,
    p_trainer_id INT,
    p_new_trainer_id INT
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
        
    IF NOT EXISTS (SELECT 1 FROM sg_offline_class WHERE class_id = p_class_id AND is_delete = 0 AND is_active = 1) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Offline class not exists';
	ELSEIF NOT EXISTS (SELECT 1 FROM sg_offline_class WHERE p_is_admin = 1 OR (p_is_admin = 0 
														AND class_id = p_class_id 
														AND is_delete = 0 
                                                        AND is_active = 1 
                                                        AND trainer_id = p_trainer_id)) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'This is not your offline class';
	ELSEIF NOT EXISTS (SELECT 1 FROM sg_offline_class 
								WHERE class_id = p_class_id 
									  AND is_delete = 0 
                                      AND is_active = 1 
									  AND TIMESTAMP(study_date, study_time) > NOW()) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Class was taken place';
	ELSEIF NOT EXISTS (SELECT 1 FROM sg_user WHERE user_id = p_new_trainer_id AND is_active = 1 AND user_type_id = 2) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid trainer';
	END IF;
    
    UPDATE sg_offline_class
    SET trainer_id = p_new_trainer_id
    WHERE class_id = p_class_id;
    
    SELECT 1 `id`,
			  'Class trainer changed' `message`;
END
//

delimiter ;