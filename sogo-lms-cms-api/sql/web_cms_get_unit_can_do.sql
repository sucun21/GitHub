delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_unit_can_do;
//

CREATE PROCEDURE `web_cms_get_unit_can_do`(IN p_unit_id INT)
BEGIN
	
    SELECT cando_id `canDoId`,
		   cando_content `canDoContent`
    FROM sg_cando
    WHERE unit_id = p_unit_id
		  AND is_active = 1
          AND is_delete = 0
	ORDER BY cando_id asc;
    
END
//

delimiter ;