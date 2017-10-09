delimiter //

DROP PROCEDURE IF EXISTS web_cms_md_get_all_class_type;
//

CREATE PROCEDURE `web_cms_md_get_all_class_type`()
BEGIN
	
    SELECT class_type_id `classTypeId`,
		   class_type_name `classTypeName`,
           description `classTypeDescription`
    FROM sg_offline_class_type;
    
END
//

delimiter ;