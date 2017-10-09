delimiter //

DROP PROCEDURE IF EXISTS web_cms_offline_class_get_class_for_retake;
//

CREATE PROCEDURE `web_cms_offline_class_get_class_for_retake`(
	p_unit_id INT
)
BEGIN
	
    SELECT soc.class_id `classId`,
		   soc.total_user_booked `totalUserBooked`,
		   sogc.limit_user `limitUser`,
           sogc.group_class_name `groupClassName`,
           TIMESTAMP(ADDTIME(soc.study_date, soc.study_time)) `studyDateTime`,
           sc.center_name `centerName`,
           su.full_name `trainerName`
    FROM sg_offline_class soc
    JOIN sg_offline_group_class sogc on sogc.group_class_id = soc.group_class_id
    JOIN sg_center sc on sc.center_id = sogc.center_id
    JOIN sg_user su on su.user_id = sogc.trainer_id
    WHERE soc.unit_id = p_unit_id
		  AND soc.is_active = 1
          AND soc.is_delete = 0
          AND ADDTIME(soc.study_date, soc.study_time) > NOW()
	ORDER BY ADDTIME(soc.study_date, soc.study_time) ASC;
    
    
END
//

delimiter ;