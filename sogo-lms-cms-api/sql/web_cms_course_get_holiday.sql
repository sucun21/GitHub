delimiter //

DROP PROCEDURE IF EXISTS web_cms_course_get_holiday;
//

CREATE PROCEDURE `web_cms_course_get_holiday`()
BEGIN
    
    SELECT holiday_id `holidayId`,
			STR_TO_DATE( CONCAT(`day`, '/', `month`, '/', `year`), '%d/%m/%Y') `holidayDate`,
            description `holidayDescription`
    FROM sg_setting_holiday
    WHERE is_active = 1
		  AND is_delete = 0;
    
END
//

delimiter ;