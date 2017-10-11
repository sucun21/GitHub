delimiter //

DROP PROCEDURE IF EXISTS web_cms_course_get_activity_by_id;
//

CREATE PROCEDURE `web_cms_course_get_activity_by_id`(
	IN p_activity_id INT
)
BEGIN
	
    SELECT activity_id `activityId`,
		   activity_type_id `activityTypeId`,
           activity_name `activityName`,
           CONVERT(activity_data USING UTF8) `activityData`,
           CONVERT(activity_des USING UTF8) `activityDes`,
           activity_length `activityLength`,
           activity_time `activityTime`,
           video_url `videoUrl`
    FROM sg_activity
    WHERE activity_id = p_activity_id;
    
END
//

delimiter ;