delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_incoming_first_class;
//

CREATE PROCEDURE `web_cms_get_incoming_first_class`(
	p_center_id INT,
	p_course_id INT
)
BEGIN
	
    DECLARE v_course_first_unit INT;
    
    
	SELECT su.unit_id INTO v_course_first_unit
    FROM sg_unit su
    JOIN sg_offline_class soc on soc.unit_id = su.unit_id
    JOIN sg_offline_group_class sogc on sogc.group_class_id = soc.group_class_id
    WHERE sogc.course_id = p_course_id
		  AND soc.is_active = 1
          AND soc.is_delete = 0
          AND su.is_active = 1
          AND su.is_delete = 0
          AND sogc.is_active = 1
          AND sogc.is_delete = 0
          AND sogc.class_type_id = 2
	ORDER BY IFNULL(sort_order, 99) ASC, su.unit_id asc
    LIMIT 1;
    
    
    SELECT soc.class_id `classId`,
		   sogc.group_class_id `groupClassId`,
		   TIMESTAMP(ADDTIME(soc.study_date, soc.study_time)) `studyDateTime`,
           TIMESTAMP(soc.study_date) `studyDate`,
           su.full_name `trainerName`,
           sogc.group_class_name `groupClassName`,
           sc.center_name `centerName`,
           soc.total_user_booked `totalUserBooked`,
           sogc.limit_user `limitUser`
    FROM sg_offline_class soc
    JOIN sg_offline_group_class sogc on sogc.group_class_id = soc.group_class_id
    JOIN sg_user su on su.user_id = soc.trainer_id
    JOIN sg_center sc on sc.center_id = sogc.center_id
    WHERE soc.unit_id = v_course_first_unit
		  AND ADDTIME(soc.study_date, soc.study_time) > NOW()
          AND soc.is_active = 1
          AND soc.is_delete = 0
          AND sogc.is_active = 1
          AND sogc.is_delete = 0
          AND sogc.center_id = p_center_id
          AND sogc.course_id = p_course_id
          AND soc.total_user_booked < sogc.limit_user
          AND sogc.class_type_id = 2
	ORDER BY ADDTIME(soc.study_date, soc.study_time) ASC;
END
//

delimiter ;