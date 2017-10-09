delimiter //

DROP PROCEDURE IF EXISTS web_cms_offline_class_get_extend_group;
//

CREATE PROCEDURE `web_cms_offline_class_get_extend_group`(
	IN p_group_class_id INT,
    IN p_modified_by INT
)
BEGIN
	
    DECLARE v_errorText TEXT;
    DECLARE v_courseId INT;
    DECLARE v_classTypeId INT;
    DECLARE v_unitStartId INT;
    DECLARE v_unit_id INT;
	DECLARE v_end_cursor BOOLEAN DEFAULT FALSE;
    DECLARE v_startDate DATE;
    DECLARE v_is_holiday BOOLEAN DEFAULT FALSE;
	DECLARE v_study_date DATE;
    DECLARE v_index INT DEFAULT 1;
    
    DECLARE unit_cur CURSOR FOR 
    SELECT unit_id
	FROM 
    (SELECT @curRow1 := @curRow1 + 1 as row_number,
		   unit_id
    FROM sg_unit
	WHERE course_id = v_courseId
		  AND class_type_id = v_classTypeId
		  AND is_active = 1
		  AND is_delete = 0
	ORDER BY IFNULL(sort_order, 9999) ASC, unit_id ASC) t 
    ORDER BY (CASE WHEN row_number >= @v_unit_start_index THEN 0 ELSE 1 END) ASC, row_number ASC;
    
    /**Delare handler sql_exception*/
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
	  GET DIAGNOSTICS CONDITION 1 v_errorText = MESSAGE_TEXT;
		  SELECT -1 `id`,
				v_errorText `message`;
	  ROLLBACK;
	END;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_end_cursor = TRUE;
    
	IF NOT EXISTS (SELECT 1 FROM sg_offline_group_class WHERE group_class_id = p_group_class_id AND is_delete = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Group class does not exist';
	ELSEIF NOT EXISTS (SELECT 1 FROM sg_offline_group_class WHERE group_class_id = p_group_class_id AND is_active = 1) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Group class is deactive';
	END IF;
    
    /**Start transaction*/
	START TRANSACTION;
    
    SET @v_unit_start_index := 0;
    SET @curRow1 = 0;
    SET @curRow = 0;
    
    SELECT course_id, class_type_id, unit_start_id
    INTO v_courseId, v_classTypeId, v_unitStartId
    FROM sg_offline_group_class
    WHERE group_class_id = p_group_class_id;
    
    SELECT MAX(study_date)
    INTO v_startDate
    FROM sg_offline_class
    WHERE group_class_id = p_group_class_id
		  AND is_delete = 0;
    
    SELECT t.row_number
    INTO @v_unit_start_index
    FROM
    (SELECT @curRow := @curRow + 1 as row_number,
		   unit_id
    FROM sg_unit
	WHERE course_id = v_courseId
		  AND class_type_id = v_classTypeId
		  AND is_active = 1
		  AND is_delete = 0
	ORDER BY IFNULL(sort_order, 9999) ASC, unit_id ASC) t
    WHERE t.unit_id = v_unitStartId;
        
    OPEN unit_cur;
    
    get_unit: LOOP
		
        
        FETCH unit_cur INTO v_unit_id;

		IF v_end_cursor THEN LEAVE get_unit;
		END IF;
        SET v_is_holiday = TRUE;
        
        WHILE(v_is_holiday OR v_study_date < CURRENT_DATE) DO
		   
           SET v_study_date = DATE_ADD(v_startDate, INTERVAL ( v_index * 7) DAY);
           
           SELECT CASE WHEN COUNT(1) > 0 THEN TRUE ELSE FALSE END
           INTO v_is_holiday
           FROM sg_setting_holiday
           WHERE is_active = 1
				 AND is_delete = 0
                 AND STR_TO_DATE(CONCAT(`day`, '/', `month`, '/', `year`), '%d/%m/%Y') = v_study_date;
           SET v_index = v_index + 1;
	    END WHILE;
        
        /*insert sg_offline_class*/
        INSERT INTO sg_offline_class(class_type_id,
									 class_status_id,
                                     group_class_id,
                                     unit_id,
                                     trainer_id,
                                     study_date,
                                     study_time,
                                     created_by,
                                     facilitator_id)
		SELECT class_type_id,
			   1,/*first*/
               group_class_id,
               v_unit_id,
               trainer_id,
               v_study_date,
               start_time,
               p_modified_by,
               facilitator_id
        FROM sg_offline_group_class
        WHERE group_class_id = p_group_class_id;
        
	END LOOP get_unit;

	CLOSE unit_cur;
    
    SELECT 1 `id`,
		   'Group class extended' `message`;
	
	/**Commit update and insert*/
	COMMIT;
    
END
//

delimiter ;