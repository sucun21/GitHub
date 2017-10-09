delimiter //

DROP PROCEDURE IF EXISTS web_cms_student_get_quiz_detail;
//

CREATE PROCEDURE `web_cms_student_get_quiz_detail`(
	IN p_trainee_activity_id INT
)
BEGIN
    
    SELECT question_title `questionTitle`,
		   question_type_id `questionTypeId`,
           right_answer_index `rightAnswerIndex`,
           trainee_answer `traineeAnswer`,
           is_trainee_answer_right `isTraineeAnswerRight`
    FROM sg_trainee_activity_detail
    WHERE trainee_activity_id = p_trainee_activity_id
    ORDER BY question_id ASC;
    
END
//

delimiter ;