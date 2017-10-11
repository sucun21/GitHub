delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_lesson_activity;
//

CREATE PROCEDURE `web_cms_get_lesson_activity`(IN p_unit_id INT)
BEGIN
	
   SELECT activity_id `activityId`,
	   activity_type_id `activityTypeId`,
       activity_name  `activityName`,
       convert(activity_des USING UTF8) `activityDes`,
       convert(activity_data USING UTF8) `activityData`,
       activity_time `activityTime`,
       activity_length `activityLength`,
       video_url `videoUrl`
	FROM lms_portal.sg_activity
	WHERE is_active=1
	AND is_delete=0
	AND lesson_id=p_lesson_id
	ORDER BY IFNULL(sort_order, 9999) ASC,
			 activity_id ASC;
    
END
//

delimiter ;