delimiter //

DROP PROCEDURE IF EXISTS web_fe_get_lesson_can_do;
//

CREATE PROCEDURE `web_fe_get_lesson_can_do`(IN p_lesson_id INT)
BEGIN
	
    SELECT cando_id `canDoId`,
		   cando_content `canDoContent`
    FROM sg_cando
    WHERE lesson_id = p_lesson_id
		  AND is_active = 1
          AND is_delete = 0
	ORDER BY cando_id asc;
    
END
//

delimiter ;