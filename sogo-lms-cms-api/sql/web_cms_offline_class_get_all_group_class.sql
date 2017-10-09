delimiter //

DROP PROCEDURE IF EXISTS web_cms_offline_class_get_all_group_class;
//

CREATE PROCEDURE `web_cms_offline_class_get_all_group_class`()
BEGIN
	
    SELECT * 
    FROM sg_offline_group_class;
    
END
//

delimiter ;