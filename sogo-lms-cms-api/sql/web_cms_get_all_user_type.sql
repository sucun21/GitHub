delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_all_user_type;
//

CREATE PROCEDURE `web_cms_get_all_user_type`()
BEGIN
	SELECT user_type_id `userTypeId`,
		   user_type_name `userTypeName` 
    FROM lms_portal.sg_user_type 
    WHERE user_type_id <> 4;
END
//

delimiter ;