delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_all_comment_type;
//

CREATE PROCEDURE `web_cms_get_all_comment_type`()
BEGIN
    
    SELECT comment_type_id `commentTypeId`,
		   comment_type_name `commentTypeName`,
           description `commentTypeDescription`
    FROM sg_comment_type;
    
	
END
//

delimiter ;