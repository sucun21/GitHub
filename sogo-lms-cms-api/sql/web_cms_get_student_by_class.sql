delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_student_by_class;
//

CREATE PROCEDURE `web_cms_get_student_by_class`(
	p_class_id INT
)
BEGIN
    
	SELECT su.user_id `userId`,
           su.user_email `userEmail`,
           su.user_phone `userPhone`,
           su.full_name `fullName`,
           stuc.unit_score `unitScore`,
           stuc.unit_time_spend `unitTimeSpend`,
           stuc.unit_completion `unitCompletion`,
           soct.is_attended `isAttended`,
           sct.course_id `courseId`,
           sct.course_trainee_id `courseTraineeId`
    FROM sg_user su
    JOIN sg_course_trainee sct ON sct.trainee_id = su.user_id
    JOIN sg_offline_class_trainee soct ON soct.course_trainee_id = sct.course_trainee_id
    JOIN sg_offline_class soc ON soc.class_id = soct.class_id
    LEFT JOIN sg_trainee_unit_completed stuc ON stuc.course_trainee_id = soct.course_trainee_id AND stuc.unit_completed_id = soc.unit_id
    WHERE su.user_type_id = 4
		  AND su.is_active = 1
          AND su.is_delete = 0
          AND soc.is_active = 1
          AND soc.is_delete = 0
          AND soct.is_cancel = 0
          AND soct.is_delete = 0
          AND soc.class_id = p_class_id
	ORDER BY su.full_name asc;
END
//

delimiter ;