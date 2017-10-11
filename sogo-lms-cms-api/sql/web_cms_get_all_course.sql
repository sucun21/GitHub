delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_all_course;
//

CREATE PROCEDURE `web_cms_get_all_course`()
BEGIN
	SELECT course_id `courseId`,
		   course_title `courseTitle`,
           convert(course_des, char) `courseDes`,
           course_short_des `courseShortDes`,
           course_image `courseImage`
    FROM sg_course
    WHERE is_active > 0
          AND is_delete = 0
	ORDER BY created_date;
END
//

delimiter ;