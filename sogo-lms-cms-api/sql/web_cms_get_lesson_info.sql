delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_lesson_info;
//

CREATE PROCEDURE `web_cms_get_lesson_info`(IN p_lesson_id INT)
BEGIN
	
    SELECT lesson_id `lessonId`,
		   lesson_title `lessonTitle`,
		   convert(lesson_des,char)   `lessonDes`
	FROM lms_portal.sg_lesson
	where is_active>0
	and is_delete=0
	and lesson_id=p_lesson_id
	ORDER BY IFNULL(sort_order, 9999) ASC,
				 unit_id ASC;
    
END
//

delimiter ;