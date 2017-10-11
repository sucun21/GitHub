delimiter //
DROP procedure IF EXISTS `web_cms_get_trainer_course`;
//

CREATE PROCEDURE `web_cms_get_trainer_course`(
    IN p_course_id INT
)
BEGIN
    
	SELECT course_trainer_id `courseTrainerId`,
		   trainer_id `trainerId` 
	FROM sg_course_trainer
						WHERE is_active = 1
						AND is_delete = 0
						AND course_id = p_course_id
                        ORDER BY course_trainer_id ASC;
END
//

delimiter ;