delimiter //

DROP PROCEDURE IF EXISTS web_cms_course_get_activity_quiz_question;
//

CREATE PROCEDURE `web_cms_course_get_activity_quiz_question`(
	IN p_activity_id INT
)
BEGIN
    
    SELECT question_id `questionId`,
		   question_type_id `questionTypeId`,
           question_title `questionTitle`,
           question_content `questionContent`,
           right_answer_index `rightAnswerIndex`,
           question_hint `questionHint`
    FROM sg_question
    WHERE activity_id = p_activity_id
		  AND is_active = 1
          AND is_delete = 0
	ORDER BY question_id ASC;
    
END
//

delimiter ;