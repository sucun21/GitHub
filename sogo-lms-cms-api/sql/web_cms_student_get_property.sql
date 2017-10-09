delimiter //

DROP PROCEDURE IF EXISTS web_cms_student_get_property;
//

CREATE PROCEDURE `web_cms_student_get_property`(
	IN p_course_trainee_id INT,
    IN p_lesson_id INT,
    IN p_unit_id INT
)
BEGIN
    
    SELECT  sp.property_name `propertyName`,
			sta.start_time `startTime`,
            sta.end_time `endTime`,
            sta.time_spend `timeSpend`,
            stp.trainee_answer `traineeAnswer`,
            sa.activity_name `activityName`
    FROM sg_trainee_activity sta
    JOIN (SELECT MAX(trainee_activity_id) `max_trainee_activity_id` 
		  FROM sg_trainee_activity
          WHERE course_trainee_id = p_course_trainee_id
          GROUP BY course_trainee_id, activity_id) sta_f ON sta_f.max_trainee_activity_id = sta.trainee_activity_id
	JOIN sg_activity sa ON sa.activity_id = sta.activity_id
    JOIN sg_trainee_property stp ON stp.property_id = sa.property_id
    JOIN (SELECT MAX(trainee_property_id) `max_trainee_property_id`
		  FROM sg_trainee_property
          WHERE course_trainee_id = p_course_trainee_id
          GROUP BY course_trainee_id, property_id) stp_f ON stp_f.max_trainee_property_id = stp.trainee_property_id
	JOIN sg_property sp on sp.property_id = stp.property_id
    WHERE (p_lesson_id IS NULL OR sta.lesson_id = p_lesson_id)
		  AND (p_unit_id IS NULL OR sta.unit_id = p_unit_id)
		  AND sta.course_trainee_id = p_course_trainee_id
          AND sa.activity_type_id = 1
	ORDER BY sta.created_date DESC;
    
END
//

delimiter ;