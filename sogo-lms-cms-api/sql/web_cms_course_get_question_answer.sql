delimiter //

DROP PROCEDURE IF EXISTS web_cms_course_get_question_answer;
//

CREATE PROCEDURE `web_cms_course_get_question_answer`(
	IN p_question_id INT
)
BEGIN
    
    SELECT answer_id `answerId`,
		   `index` `answerIndex`,
		   `text` `answerText`
    FROM sg_question_answer
    WHERE question_id = p_question_id
		  AND is_active = 1
          AND is_delete = 0
    ORDER BY `index` ASC;
    
END
//

delimiter ;