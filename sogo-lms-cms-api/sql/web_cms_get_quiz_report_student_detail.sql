delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_quiz_report_student_detail;
//

CREATE PROCEDURE `web_cms_get_quiz_report_student_detail`(
	IN p_class_id INT,
    IN p_activity_id INT
)
BEGIN
            
	SELECT sct.trainee_id `userId`,
           sq.question_id `questionId`,
           stad.is_trainee_answer_right `isTraineeAnswerRight`
    FROM sg_offline_class_trainee soct
    JOIN sg_course_trainee sct on sct.course_trainee_id = soct.course_trainee_id
    JOIN sg_question sq on sq.activity_id = p_activity_id
    LEFT JOIN (SELECT MAX(trainee_activity_id) `max_trainee_activity_id`, course_trainee_id 
				FROM sg_trainee_activity 
			   WHERE activity_id = p_activity_id
					 AND is_active = 1
               GROUP BY course_trainee_id) sta_f ON sta_f.course_trainee_id = sct.course_trainee_id
    LEFT JOIN sg_trainee_activity_detail stad on stad.trainee_activity_id = sta_f.max_trainee_activity_id
                                                 AND stad.question_id = sq.question_id
	WHERE soct.class_id = p_class_id 
    ORDER BY sct.trainee_id ASC, 
			sq.question_id ASC;
    
END
//

delimiter ;