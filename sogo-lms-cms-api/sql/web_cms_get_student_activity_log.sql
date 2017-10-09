delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_student_activity_log;
//

CREATE PROCEDURE `web_cms_get_student_activity_log`(
	p_student_id INT,
    p_course_trainee_id INT
)
BEGIN
	
    SELECT stt.trainee_timeline_id `timelineId`,
		   stt.timeline_type_id `timelineTypeId`,
		   CAST(stt.post_content AS CHAR) `postContent`,
           stt.post_by_name `postByName`,
           cast_to_bit(stt.is_show_timeline) `isShowtimeline`,
           su.full_name `createdByName`,
           stt.created_date `createdDate`,
           scr.comment_result_name `commentResultName`,
           sct.comment_type_name `commentTypeName`
    FROM sg_trainee_timeline stt
    JOIN sg_course_trainee sctr on sctr.course_id = stt.course_id and sctr.trainee_id = stt.trainee_id
    LEFT JOIN sg_comment sc on sc.comment_id = stt.comment_id
    LEFT JOIN sg_comment_result scr on scr.comment_result_id = sc.comment_result_id
    LEFT JOIN sg_comment_type sct on sct.comment_type_id = sc.comment_type_id
    LEFT JOIN sg_user su on su.user_id = stt.created_by
    WHERE stt.is_delete = 0
		  AND sctr.course_trainee_id = p_course_trainee_id
          AND stt.trainee_id = p_student_id
    ORDER BY stt.created_date DESC,
			 stt.trainee_timeline_id DESC;
    
END
//

delimiter ;