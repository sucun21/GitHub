delimiter //

DROP PROCEDURE IF EXISTS web_cms_course_get_lesson_in_unit;
//

CREATE PROCEDURE `web_cms_course_get_lesson_in_unit`(
	IN p_unit_id INT
)
BEGIN
    
    SELECT lesson_id `lessonId`,  
           lesson_title `lessonTitle`
	FROM sg_lesson
    WHERE unit_id = p_unit_id
		  AND is_active = 1
          AND is_delete = 0
	ORDER BY IFNULL(sort_order, 9999) ASC,
			 lesson_id ASC;
    
END
//

delimiter ;