delimiter //

DROP PROCEDURE IF EXISTS web_cms_course_get_unit_in_course;
//

CREATE PROCEDURE `web_cms_course_get_unit_in_course`(
	IN p_course_id INT
)
BEGIN
    
    SELECT unit_id `unitId`,  
           unit_title `unitTitle`
	FROM sg_unit
    WHERE course_id = p_course_id
		  AND is_active = 1
          AND is_delete = 0
	ORDER BY IFNULL(sort_order, 9999) ASC,
			 unit_id ASC;
    
END
//

delimiter ;