delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_quiz_report;
//

CREATE PROCEDURE `web_cms_get_quiz_report`(
	IN p_class_id INT
)
BEGIN
	
    DECLARE v_totalStudentCount INT;
    
    SELECT COUNT(1) INTO v_totalStudentCount 
    FROM sg_offline_class_trainee
    WHERE class_id = p_class_id
		  AND is_cancel = 0
          AND is_delete = 0;
    
    SELECT sl.lesson_title `lessonTitle`,
		   sa.activity_id `activityId`,
           sa.activity_name `activityName`,
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
           v_totalStudentCount `totalStudent`,
			cast_to_int(	
						(
						 SELECT COUNT(1) FROM
							(
								SELECT sq.activity_id
								FROM sg_question sq
								JOIN sg_trainee_activity_detail stad ON stad.question_id = sq.question_id AND stad.is_active = 1
								JOIN sg_offline_class_trainee soct ON soct.course_trainee_id = stad.course_trainee_id AND class_id = p_class_id 
																	  AND soct.is_cancel = 0 AND soct.is_delete = 0
								JOIN (SELECT MAX(trainee_activity_id) `max_trainee_activity_id`, course_trainee_id
											FROM sg_trainee_activity 
											WHERE is_active = 1
										   GROUP BY course_trainee_id) sta_f on sta_f.course_trainee_id = soct.course_trainee_id
																				and sta_f.max_trainee_activity_id = stad.trainee_activity_id
								where stad.is_trainee_answer_right = 1
								GROUP BY sq.activity_id, sq.question_id
								HAVING COUNT(1) / v_totalStudentCount < 0.6
                            ) t
						 WHERE t.activity_id = sa.activity_id
						 )
                        ) `totalLowQuestion`,
			cast_to_int((SELECT COUNT(1)
						 FROM sg_question sq
                         WHERE sq.activity_id = sa.activity_id
							   AND sq.is_active = 1
                               AND sq.is_delete = 0)) `totalQuestion`
    FROM sg_offline_class soc
    JOIN sg_lesson sl on sl.unit_id = soc.unit_id AND sl.is_active = 1 AND sl.is_delete = 0
    JOIN sg_activity sa on sa.lesson_id = sl.lesson_id AND sa.is_active = 1 AND sa.is_delete = 0
    WHERE sa.activity_type_id = 4
		  AND soc.class_id = p_class_id
    ORDER BY IFNULL(sl.sort_order, 99) ASC, 
			 sl.lesson_id ASC,
             IFNULL(sa.sort_order, 99) ASC,
             sa.activity_id ASC;
END
//

delimiter ;