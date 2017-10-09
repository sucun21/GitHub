delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_quiz_report_summary;
//

CREATE PROCEDURE `web_cms_get_quiz_report_summary`(
	IN p_class_id INT,
    IN p_activity_id INT
)
BEGIN
    
    DECLARE v_totalStudentCount INT;
    DECLARE v_countRightAnswer INT;
    
    SELECT COUNT(1) INTO v_totalStudentCount 
    FROM sg_offline_class_trainee
    WHERE class_id = p_class_id
		  AND is_cancel = 0
          AND is_delete = 0;
          
	SELECT COUNT(1) INTO v_countRightAnswer
    FROM sg_trainee_activity_detail
    WHERE activity_id = p_activity_id
		  AND is_active = 1
          AND is_trainee_answer_right = 1
          AND trainee_activity_id IN (SELECT MAX(trainee_activity_id)
										FROM sg_trainee_activity 
									   WHERE activity_id = p_activity_id
											 AND is_active = 1
									   GROUP BY course_trainee_id);
    
    SELECT su.unit_title `unitTitle`,
		   sl.lesson_title `lessonTitle`,
           sa.activity_name `activityName`,
		   cast_to_int(COUNT(1)) `numberOfQuestion`,
		   cast_to_int(	
						(
						 SELECT COUNT(1) FROM
							(SELECT sq.activity_id
							FROM sg_question sq
							JOIN sg_trainee_activity_detail stad ON stad.question_id = sq.question_id AND stad.is_active = 1
							JOIN sg_offline_class_trainee soct ON soct.course_trainee_id = stad.course_trainee_id AND class_id = p_class_id 
																  AND soct.is_cancel = 0 AND soct.is_delete = 0
							JOIN (SELECT MAX(trainee_activity_id) `max_trainee_activity_id`, course_trainee_id, activity_id
										FROM sg_trainee_activity 
									   WHERE activity_id = p_activity_id
											 AND is_active = 1
									   GROUP BY course_trainee_id, activity_id) sta_f on sta_f.course_trainee_id = soct.course_trainee_id
																						 AND stad.trainee_activity_id = sta_f.max_trainee_activity_id
							GROUP BY sq.activity_id, sq.question_id
							HAVING SUM(CASE WHEN stad.is_trainee_answer_right = 1 THEN 1 ELSE 0 END) / v_totalStudentCount < 0.6) t
						 )
                        ) `totalLowQuestion`,
			cast_to_int((SELECT COUNT(1)
					   FROM sg_trainee_activity sta
                       WHERE sta.activity_id = sa.activity_id 
							 AND sta.is_active = 1
                             AND sta.is_delete = 0
							 AND course_trainee_id IN (SELECT course_trainee_id 
														FROM sg_offline_class_trainee
														WHERE class_id = p_class_id
															  AND is_cancel = 0
															  AND is_delete = 0)
					  )) `totalStudentDone`,
			 cast_to_float(v_countRightAnswer / v_totalStudentCount) `avarageRightAnswer`
    FROM sg_activity sa
    JOIN sg_question sq on sq.activity_id = sa.activity_id AND sq.activity_id = p_activity_id
    JOIN sg_lesson sl on sl.lesson_id = sa.lesson_id
    JOIN sg_unit su on su.unit_id = sl.unit_id
    WHERE sq.is_active = 1
		  AND sq.is_delete = 0;
    
END
//

delimiter ;