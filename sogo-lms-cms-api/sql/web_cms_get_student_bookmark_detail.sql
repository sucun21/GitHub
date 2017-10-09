delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_student_bookmark_detail;
//

CREATE PROCEDURE `web_cms_get_student_bookmark_detail`(
	IN p_course_trainee_id INT,
    IN p_student_id INT
)
BEGIN
	
    DECLARE v_estimate_start_date TIMESTAMP;
    DECLARE v_estimate_end_date TIMESTAMP;
    DECLARE v_actual_start_date TIMESTAMP;
    DECLARE v_actual_end_date TIMESTAMP;
    
    SELECT attended_date INTO v_estimate_start_date
    FROM sg_offline_class_trainee
    WHERE course_trainee_id = p_course_trainee_id
	ORDER BY attended_date ASC
    LIMIT 1;
    
    SELECT attended_date INTO v_estimate_end_date
    FROM sg_offline_class_trainee
    WHERE course_trainee_id = p_course_trainee_id
	ORDER BY attended_date DESC
    LIMIT 1;
    
	SELECT attended_date INTO v_actual_start_date
    FROM sg_offline_class_trainee
    WHERE course_trainee_id = p_course_trainee_id
		  AND is_attended = 1
	ORDER BY attended_date ASC
    LIMIT 1;
    
    SELECT attended_date INTO v_actual_end_date
    FROM sg_offline_class_trainee
    WHERE course_trainee_id = p_course_trainee_id
		  AND is_attended = 1
	ORDER BY attended_date DESC
    LIMIT 1;
    
	SELECT sc.course_title `courseTitle`,
		   sct.contract_no `contractNo`,
           sgc.center_name `centerName`,
		   str.current_unit_id `currentUnitId`,
		   str.current_lesson_id `currentLessonId`,
           str.next_activity_id `currentActivityId`,
           v_estimate_start_date `estimateStartDate`,
           v_estimate_end_date `estimateEndDate`,
           v_actual_start_date `actualStartDate`,
           v_actual_end_date `actualEndDate`,
           su.is_active `isActive`
    FROM sg_trainee_result str
    JOIN sg_course_trainee sct on sct.course_trainee_id = str.course_trainee_id
    JOIN sg_course sc on sc.course_id = sct.course_id
    JOIN sg_center sgc on sgc.center_id = sct.study_center
    JOIN sg_user su on su.user_id = sct.trainee_id
    WHERE str.course_trainee_id = p_course_trainee_id;
    
END
//

delimiter ;