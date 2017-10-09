delimiter //

DROP PROCEDURE IF EXISTS web_cms_add_new_user;
//

CREATE PROCEDURE `web_cms_add_new_user`(
	p_firstName VARCHAR(100),
    p_lastName VARCHAR(100),
    p_user_type_id INT,
	p_userEmail VARCHAR(250),
    p_userPhone VARCHAR(20),
    p_password VARCHAR(200),
    p_dob DATE,
    p_gender INT,
    p_createdBy INT
)
BEGIN
	DECLARE v_userFullName TEXT;
	DECLARE errorText TEXT;
    
	/**Delare handler sql_exception*/
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
	  GET DIAGNOSTICS CONDITION 1 errorText = MESSAGE_TEXT;
		  SELECT -1 `id`,
				errorText `message`;
	  ROLLBACK;
	END;
    
    IF EXISTS (SELECT 1 FROM sg_user WHERE user_email = p_userEmail) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'User already exist';
    END IF;
    
    /**Start transaction*/
	START TRANSACTION;
    
    SET v_userFullName = concat(p_firstName, ' ', p_lastName);
    
    /*insert sg_user*/
	INSERT INTO sg_user (user_type_id, 
						 user_email, 
						 password, 
						 full_name, 
						 first_name, 
						 last_name, 
						 date_of_birth,
                         user_phone,
						 gender, 
						 created_by,
                         full_name_ascii)
	value(p_user_type_id, 
		  p_userEmail, 
		  p_password, 
		  v_userFullName, 
		  p_firstName,
		  p_lastName,
		  p_dob,
          p_userPhone,
		  p_gender, 
		  p_createdBy,
          convert_to_unsign(v_userFullName));
          
	SELECT 1 `id`,
		   'User added' `message`;
    /**Commit update and insert*/
	COMMIT;

END
//

delimiter ;