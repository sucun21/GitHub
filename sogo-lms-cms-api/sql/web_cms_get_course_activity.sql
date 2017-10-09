delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_course_activity;
//

CREATE PROCEDURE `web_cms_get_course_activity`(
	IN p_course_id INT
)
BEGIN
    
    SELECT sa.activity_id `activityId`, 
		   sa.activity_name `activityName`,
		   sl.lesson_id `lessonId`, 
           sl.lesson_title `lessonTitle`,
           su.unit_id `unitId`,  
           su.unit_title `unitTitle`
	FROM sg_activity sa
	JOIN sg_lesson sl ON sl.lesson_id = sa.lesson_id
	JOIN sg_unit su on su.unit_id = sl.unit_id
	JOIN    (SELECT @curRow := 0) r
	WHERE su.course_id = p_course_id
	ORDER BY IFNULL(su.sort_order, 9999) ASC,
			 su.unit_id ASC,
			 IFNULL(sl.sort_order, 9999) ASC,
			 sl.lesson_id ASC,
			 IFNULL(sa.sort_order, 9999) ASC,
			 sa.activity_id ASC;
    
END
//

delimiter ;