delimiter //

DROP PROCEDURE IF EXISTS web_cms_student_get_class_by_course;
//

CREATE PROCEDURE `web_cms_student_get_class_by_course`(
	p_trainee_id INT,
    p_course_trainee_id INT
)
BEGIN
	SELECT soct.class_id `classId`,
		   sco.course_title `courseTitle`,
		   sun.unit_title `unitTitle`,
		   TIMESTAMP(ADDTIME(soc.study_date, soc.study_time)) `studyDateTime`, 
		   soct.is_attended `isAttended`, 
		   su.user_email `userEmail`,
           sc.center_name `centerName`,           
		   su.full_name `teacherName`,
           sogc.group_class_name `groupClassName`,
           sun.unit_id `unitId`,
           sc.center_id `centerId`,
           cast_to_bit(IFNULL(soct.is_retake, 0)) `isRetake`
	FROM sg_offline_class_trainee soct 
    JOIN sg_course_trainee sct on sct.course_trainee_id = soct.course_trainee_id and sct.trainee_id = p_trainee_id 
	JOIN sg_offline_class soc on soc.class_id = soct.class_id
    JOIN sg_offline_group_class sogc on sogc.group_class_id = soc.group_class_id
    JOIN sg_course sco on sct.course_id = sco.course_id 
    JOIN sg_unit sun on sun.unit_id = soct.unit_id 
    JOIN sg_user su on su.user_id = soc.trainer_id 
    JOIN sg_center sc on sc.center_id = sogc.center_id
    Where (p_course_trainee_id = 0 or sct.course_trainee_id = p_course_trainee_id) 
    ORDER BY ADDTIME(soc.study_date, soc.study_time) ASC;
END
//

delimiter ;