delimiter //

DROP PROCEDURE IF EXISTS web_cms_offline_class_get_group_schedule;
//

CREATE PROCEDURE `web_cms_offline_class_get_group_schedule`(
	IN p_group_class_id INT,
    IN p_week_view INT
)
BEGIN
	
    SET @curWeek := WEEK(CURRENT_DATE);
    
	CREATE TEMPORARY TABLE IF NOT EXISTS tblWeek AS 
    (
		SELECT id,
			   DATE_ADD(CURRENT_DATE, INTERVAL 1 - DAYOFWEEK(CURRENT_DATE) + 7 * (id - @curWeek) DAY) `startDate`,
			   DATE_ADD(CURRENT_DATE, INTERVAL 7 - DAYOFWEEK(CURRENT_DATE) + 7 * (id - @curWeek) DAY) `endDate`
		FROM Weeks
		WHERE id >= @curWeek && id < @curWeek + p_week_view
    );
    
    SELECT  cast_to_int(CASE WHEN tw.id > 53 THEN tw.id - 53 ELSE tw.id END) `weekNumber`,
			concat(DATE_FORMAT(tw.startDate, '%d/%m/%Y'), ' - ', DATE_FORMAT(tw.endDate, '%d/%m/%Y')) `weekDuration`,
			TIMESTAMP(addtime(soc.study_date, soc.study_time)) `classDateTime`,
            soc.total_user_booked `totalBooked`,
            (SELECT su.unit_title FROM sg_unit su WHERE su.unit_id = soc.unit_id) `unitTitle`
    FROM tblWeek tw
    LEFT JOIN sg_offline_class soc ON soc.study_date >= tw.startDate
									  AND soc.study_date <= tw.endDate
                                      AND soc.group_class_id = p_group_class_id
                                      AND soc.is_active = 1
                                      AND soc.is_delete = 0;
                                          
    DROP TEMPORARY TABLE IF EXISTS tblWeek;
    
END
//

delimiter ;