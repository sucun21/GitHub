delimiter //

DROP PROCEDURE IF EXISTS web_cms_student_get_quiz;
//

CREATE PROCEDURE `web_cms_student_get_quiz`(
	IN p_course_trainee_id INT,
    IN p_lesson_id INT,
    IN p_unit_id INT
)
BEGIN
    
    SELECT  sta.start_time `startTime`,
            sta.end_time `endTime`,
            sta.time_spend `timeSpend`,
            sa.activity_name `activityName`,
            sta.trainee_activity_id `traineeActivityId`,
            sta.activity_name `activityName`
    FROM sg_trainee_activity sta
    JOIN (SELECT MAX(trainee_activity_id) `max_trainee_activity_id` 
		  FROM sg_trainee_activity
          GROUP BY course_trainee_id, activity_id) sta_f ON sta_f.max_trainee_activity_id = sta.trainee_activity_id
	JOIN sg_activity sa ON sa.activity_id = sta.activity_id
    WHERE (p_lesson_id IS NULL OR sta.lesson_id = p_lesson_id)
		  AND (p_unit_id IS NULL OR sta.unit_id = p_unit_id)
		  AND sta.course_trainee_id = p_course_trainee_id
          AND sa.activity_type_id = 4
	ORDER BY sta.created_date DESC;
    
END
//

delimiter ;