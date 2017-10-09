delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_intro_class_by_center_and_course;
//

CREATE PROCEDURE `web_cms_get_intro_class_by_center_and_course`(
	p_center_id INT,
    p_course_id INT
)
BEGIN
	SELECT soc.class_id `classId`,
		   sc.center_name `centerName`,
           TIMESTAMP(ADDTIME(soc.study_date, soc.study_time)) `studyDateTime`,
           su.full_name `trainerName`,
           soc.total_user_booked `totalUserBooked`,
           sogc.limit_user `limitUser`
    FROM sg_offline_class soc
    JOIN sg_offline_group_class sogc ON sogc.group_class_id = soc.group_class_id AND sogc.class_type_id = 1 /*lớp introduction*/
    JOIN sg_center sc on sc.center_id = sogc.center_id
    JOIN sg_user su on su.user_id = sogc.trainer_id
    WHERE soc.is_active = 1
          AND soc.is_delete = 0
          AND sogc.is_active = 1
          AND sogc.is_delete = 0
          AND sogc.center_id = p_center_id
          AND sogc.course_id = p_course_id
          AND ADDTIME(soc.study_date, soc.study_time) > NOW()
	ORDER BY ADDTIME(soc.study_date, soc.study_time);
END
//

delimiter ;