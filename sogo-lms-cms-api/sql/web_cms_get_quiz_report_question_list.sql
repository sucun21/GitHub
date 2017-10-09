delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_quiz_report_question_list;
//

CREATE PROCEDURE `web_cms_get_quiz_report_question_list`(
    IN p_activity_id INT
)
BEGIN
    
    SELECT question_id `questionId`,
			question_title `questionTitle`,
            (SELECT GROUP_CONCAT(`text` SEPARATOR '#@WSE@#')
			FROM sg_question_answer
			WHERE question_id = sq.question_id
				  AND is_active = 1
                  AND is_delete = 0
                  AND find_in_set(`index`, sq.right_answer_index)
			GROUP BY 'all'
            ORDER BY `index` ASC) `rightAnswer`, 
            right_answer_index `rightAnswerIndex`
    FROM sg_question sq
    WHERE activity_id = p_activity_id
		  AND is_active = 1
          AND is_delete = 0
    ORDER BY question_id ASC;
    
END
//

delimiter ;