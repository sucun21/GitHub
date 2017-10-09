delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_trainer_info;
//

CREATE PROCEDURE `web_cms_get_trainer_info`(
)
BEGIN
	SELECT user_id `userId`,
           user_email `userEmail`,
           user_phone `userPhone`,
           full_name `fullName`,
           avatar_url `avatarUrl`
    FROM sg_user
    WHERE user_type_id = 2
	AND is_active = 1
	AND is_delete = 0
	ORDER BY created_date DESC;
END
//

delimiter ;