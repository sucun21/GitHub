delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_class_info_by_id;
//

CREATE PROCEDURE `web_cms_get_class_info_by_id`(
    p_class_id INT
)
BEGIN
	SELECT soc.class_id `classId`,
		   sco.course_title `courseTitle`,
		   sun.unit_title `unitTitle`,
		   TIMESTAMP(ADDTIME(soc.study_date, soc.study_time)) `studyDateTime`,
		   su.user_email `userEmail`,
           sc.center_name `centerName`,           
		   su.full_name `teacherName`,
           su.user_id `teacherId`,
		   soc.is_attendance_check	`isAttendanceCheck`,
           cast_to_bit(CASE WHEN TIMESTAMP(ADDTIME(soc.study_date, soc.study_time)) < NOW() THEN 1 ELSE 0 END) `isPastClass`,
           soct.class_type_id `classTypeId`,
           soct.class_type_name `classTypeName`,
           CAST_TO_BIT(CASE WHEN DATE_ADD(ADDTIME(soc.study_date, soc.study_time), INTERVAL -15 MINUTE) < NOW() THEN 1 ELSE 0 END) `isUpcomingIn15Min`
	FROM sg_offline_class soc
    JOIN sg_offline_group_class sogc on sogc.group_class_id = soc.group_class_id
    JOIN sg_unit sun on sun.unit_id = soc.unit_id
    JOIN sg_center sc on sc.center_id = sogc.center_id
	JOIN sg_course sco on sco.course_id=sogc.course_id
    JOIN sg_user su on su.user_id = soc.trainer_id
    JOIN sg_offline_class_type soct on soct.class_type_id = soc.class_type_id
	WHERE class_id = p_class_id
	ORDER BY ADDTIME(soc.study_date, soc.study_time) ASC;
END
//

delimiter ;