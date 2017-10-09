delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_class_schedule;
//

CREATE PROCEDURE `web_cms_get_class_schedule`(
	p_is_admin BIT,
	p_course_id INT,
    p_center_id INT,
    p_trainer_id INT,
    p_start_date DATE,
    p_end_date DATE
)
BEGIN
	
    SELECT soc.class_id `classId`,
		   sun.unit_title `unitTitle`,
           sc.center_name `centerName`,
           su.full_name `teacherName`,
		   TIMESTAMP(ADDTIME(soc.study_date, soc.study_time)) `studyDateTime`,
           CAST_TO_BIT(soc.is_attendance_check) `isCheckAttendance`,
           CAST_TO_BIT(CASE WHEN ADDTIME(soc.study_date, soc.study_time) > NOW() THEN 1 ELSE 0 END) `isIncomingClass`
	FROM sg_offline_class soc
    JOIN sg_offline_group_class sogc on sogc.group_class_id = soc.group_class_id
    JOIN sg_unit sun on sun.unit_id = soc.unit_id
    JOIN sg_center sc on sc.center_id = sogc.center_id
    JOIN sg_user su on su.user_id = sogc.trainer_id
	WHERE   (
			  (
				p_is_admin = 0 AND 
				soc.trainer_id = p_trainer_id AND
				(p_course_id IS NULL OR sogc.course_id = p_course_id)
			  )
		   OR (
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
            AND soc.study_date >= p_start_date
            AND soc.study_date <= p_end_date
	ORDER BY ADDTIME(soc.study_date, soc.study_time) ASC;
    
    
END
//

delimiter ;