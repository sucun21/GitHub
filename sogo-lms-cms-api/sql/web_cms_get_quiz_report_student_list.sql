delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_quiz_report_student_list;
//

CREATE PROCEDURE `web_cms_get_quiz_report_student_list`(
	IN p_class_id INT,
    IN p_activity_id INT
)
BEGIN
    
    SELECT su.user_id `userId`,
		   su.full_name `studentName`,
		   sta_f.created_date `submissionTime`,
           cast_to_int((SELECT COUNT(1) FROM sg_trainee_activity_detail stad
						WHERE stad.activity_id = p_activity_id 
							  AND stad.is_trainee_answer_right = 1
                              AND stad.course_trainee_id = soct.course_trainee_id
                              AND stad.trainee_activity_id = (SELECT MAX(trainee_activity_id)
															  FROM sg_trainee_activity
                                                              WHERE activity_id = p_activity_id
																	AND course_trainee_id = sct.course_trainee_id
                                                                    AND is_active = 1
                                                              GROUP BY course_trainee_id)
						)
					   ) `totalAnswerRight`
    FROM sg_offline_class_trainee soct
    JOIN sg_course_trainee sct on sct.course_trainee_id = soct.course_trainee_id
    JOIN sg_user su on su.user_id = sct.trainee_id
    LEFT JOIN (SELECT MAX(trainee_activity_id) `max_trainee_activity_id`, course_trainee_id, created_date
				FROM sg_trainee_activity 
			   WHERE activity_id = p_activity_id
               GROUP BY course_trainee_id, created_date) sta_f on sta_f.course_trainee_id = sct.course_trainee_id
    WHERE soct.class_id = p_class_id
    ORDER BY su.full_name ASC;
    
END
//

delimiter ;