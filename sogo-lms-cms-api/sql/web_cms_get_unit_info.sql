delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_unit_info;
//

CREATE PROCEDURE `web_cms_get_unit_info`(IN p_unit_id INT)
BEGIN
	
    SELECT unit_id `unitId`,
	   unit_title `unitTitle`,
       convert(unit_des,char)    `unitDes`,
       convert(unit_summary,char) `unitSummary`
	FROM lms_portal.sg_unit
	where is_active=1
	and is_delete=0
	and unit_id=p_unit_id
	ORDER BY IFNULL(sort_order, 9999) ASC,
			 unit_id ASC;
    
END
//

delimiter ;