delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_incoming_class;
//

CREATE PROCEDURE `web_cms_get_incoming_class`(
	p_is_admin BIT,
	p_course_id INT,
    p_center_id INT,
    p_trainer_id INT,
    p_next_day_num INT
)
BEGIN
	
    SELECT soc.class_id `classId`,
		   sun.unit_title `unitTitle`,
           sc.center_name `centerName`,
           su.full_name `teacherName`,
		   TIMESTAMP(ADDTIME(soc.study_date, soc.study_time)) `studyDateTime`
	FROM sg_offline_class soc
    JOIN sg_offline_group_class sogc on sogc.group_class_id = soc.group_class_id
    JOIN sg_unit sun on sun.unit_id = soc.unit_id
    JOIN sg_center sc on sc.center_id = sogc.center_id
    JOIN sg_user su on su.user_id = sogc.trainer_id
	WHERE 	(
				(
					p_is_admin = 0 AND 
					soc.trainer_id = p_trainer_id AND
					(p_course_id IS NULL OR sogc.course_id = p_course_id)
				) OR 
				(
					p_is_admin = 1 AND
					(p_course_id IS NULL OR sogc.course_id = p_course_id) AND
					(p_center_id IS NULL OR sogc.center_id = p_center_id) AND
					(p_trainer_id IS NULL OR sogc.trainer_id = p_trainer_id)
				)
			)
			AND soc.is_active = 1
			AND soc.is_delete = 0
            AND sogc.is_active = 1
			AND sogc.is_delete = 0
            AND ADDTIME(soc.study_date, soc.study_time) > NOW()
            AND DATEDIFF(soc.study_date, NOW()) <= p_next_day_num
	ORDER BY ADDTIME(soc.study_date, soc.study_time) ASC;
    
    
END
//

delimiter ;