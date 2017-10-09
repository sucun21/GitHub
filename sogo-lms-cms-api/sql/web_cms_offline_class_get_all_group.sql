delimiter //

DROP PROCEDURE IF EXISTS web_cms_offline_class_get_all_group;
//

CREATE PROCEDURE `web_cms_offline_class_get_all_group`(
	IN p_search_key VARCHAR(250),
    IN p_trainer_id INT,
    IN p_course_id INT,
    IN p_center_id INT,
	IN p_page_number INT,
    IN p_page_size INT,
    IN p_class_type_id INT,
    IN p_status_id BIT
)
BEGIN
	
    DECLARE v_offset_row INT;
    DECLARE v_total_row INT;

    set v_offset_row = (p_page_number - 1) * p_page_size;
    
	SELECT COUNT(1) INTO v_total_row
    FROM sg_offline_group_class sogc
    JOIN sg_user su on su.user_id = sogc.trainer_id
    JOIN sg_center sct on sct.center_id = sogc.center_id
    JOIN sg_unit sun on sun.unit_id = sogc.unit_start_id
    JOIN sg_course sc on sc.course_id = sogc.course_id
    WHERE (p_search_key IS NULL OR p_search_key = '' OR UPPER(sogc.group_class_name) LIKE CONCAT('%', UPPER(p_search_key), '%'))
		  AND (p_search_key != '' or p_course_id IS NULL OR sogc.course_id = p_course_id)
          AND (p_search_key != '' or p_center_id IS NULL OR sogc.center_id = p_center_id)
          AND (p_search_key != '' or p_trainer_id IS NULL OR sogc.trainer_id = p_trainer_id)
          AND (p_search_key != '' or p_class_type_id IS NULL OR sogc.class_type_id = p_class_type_id)
          AND (p_search_key != '' or p_status_id IS NULL OR sogc.is_active = p_status_id);
    
    SELECT sogc.group_class_id `groupClassId`,
		   sogc.group_class_name `groupClassName`,
           sogc.start_date `startDate`,
           sogc.start_date_in_week `startDateInWeek`,
           sogc.start_time `startTime`,
           sogc.limit_user `limitUser`,
           su.full_name `trainerName`,
           sct.center_name `centerName`,
           sc.course_title `courseTitle`,
           sogc.is_active `isActive`,
           sun.unit_title `unitTitle`,
           v_total_row `totalRow`
    FROM sg_offline_group_class sogc
    JOIN sg_user su on su.user_id = sogc.trainer_id
    JOIN sg_center sct on sct.center_id = sogc.center_id
    JOIN sg_unit sun on sun.unit_id = sogc.unit_start_id
    JOIN sg_course sc on sc.course_id = sogc.course_id
    WHERE (p_search_key IS NULL OR p_search_key = '' OR UPPER(sogc.group_class_name) LIKE CONCAT('%', UPPER(p_search_key), '%'))
		  AND (p_search_key != '' or p_course_id IS NULL OR sogc.course_id = p_course_id)
          AND (p_search_key != '' or p_center_id IS NULL OR sogc.center_id = p_center_id)
          AND (p_search_key != '' or p_trainer_id IS NULL OR sogc.trainer_id = p_trainer_id)
          AND (p_search_key != '' or p_class_type_id IS NULL OR sogc.class_type_id = p_class_type_id)
          AND (p_search_key != '' or p_status_id IS NULL OR sogc.is_active = p_status_id)
    ORDER BY sogc.created_date DESC
    LIMIT v_offset_row, p_page_size;
END
//

delimiter ;