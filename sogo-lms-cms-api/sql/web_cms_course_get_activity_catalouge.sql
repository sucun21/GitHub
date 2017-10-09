delimiter //

DROP PROCEDURE IF EXISTS web_cms_course_get_activity_catalouge;
//

CREATE PROCEDURE `web_cms_course_get_activity_catalouge`(
	IN p_course_id INT,
	IN p_unit_id INT,
    IN p_lesson_id INT
)
BEGIN
    
    SELECT sa.activity_id `activityId`,
		   sa.activity_name `activityName`,
           sat.activity_type_name `activityTypeName`,
           sa.activity_time `activityTime`,
           sl.lesson_id `lessonId`,
           sl.lesson_title `lessonTitle`,
           su.unit_id `unitId`,
           su.unit_title `unitTitle`
    FROM sg_activity sa
    JOIN sg_lesson sl on sl.lesson_id = sa.lesson_id
    JOIN sg_unit su on su.unit_id = sl.unit_id
    LEFT JOIN sg_activity_type sat on sat.activity_type_id = sa.activity_type_id
    WHERE sa.is_active = 1
		  AND sa.is_delete = 0
          AND (p_course_id IS NULL OR p_course_id = 0 OR su.course_id = p_course_id)
          AND (p_unit_id IS NULL OR p_unit_id = 0 OR su.unit_id = p_unit_id)
          AND (p_lesson_id IS NULL OR p_lesson_id = 0 OR sl.lesson_id = p_lesson_id)
	ORDER BY IFNULL(su.sort_order, 9999) ASC,
			 su.unit_id ASC,
             IFNULL(sl.sort_order, 9999) ASC,
			 sl.lesson_id ASC,
			 IFNULL(sa.sort_order, 9999) ASC,
			 sa.activity_id ASC;
    
END
//

delimiter ;