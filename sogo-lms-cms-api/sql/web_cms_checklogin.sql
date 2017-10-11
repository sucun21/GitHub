delimiter //

DROP PROCEDURE IF EXISTS web_cms_checklogin;
//

CREATE PROCEDURE `web_cms_checklogin`(
	p_email VARCHAR(250), 
    p_password VARCHAR(200)
)
BEGIN
	SELECT user_id `userId`,
		   user_type_id `userTypeId`,
           account_id `accountId`,
           user_email `userEmail`,
           user_phone `userPhone`,
           full_name `fullName`,
           avatar_url `avatarUrl`
    FROM sg_user
    WHERE user_email = p_email
		  AND password = p_password
          AND user_type_id <> 4
          AND is_active > 0
          AND is_delete = 0
    LIMIT 1;
END
//

delimiter ;