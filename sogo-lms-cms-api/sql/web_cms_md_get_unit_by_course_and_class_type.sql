delimiter //

DROP PROCEDURE IF EXISTS web_cms_md_get_unit_by_course_and_class_type;
//

CREATE PROCEDURE `web_cms_md_get_unit_by_course_and_class_type`(
	IN p_course_id INT,
    IN p_class_type_id INT
)
BEGIN
    
    SELECT unit_id `unitId`,  
           unit_title `unitTitle`
	FROM sg_unit
    WHERE course_id = p_course_id
		  AND class_type_id = p_class_type_id
		  AND is_active = 1
          AND is_delete = 0
	ORDER BY IFNULL(sort_order, 9999) ASC,
			 unit_id ASC;
    
END
//

delimiter ;