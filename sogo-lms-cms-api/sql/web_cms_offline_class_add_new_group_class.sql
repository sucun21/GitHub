delimiter //

DROP PROCEDURE IF EXISTS web_cms_offline_class_add_new_group_class;
//

CREATE PROCEDURE `web_cms_offline_class_add_new_group_class`(
	IN p_groupClassName VARCHAR(250),
    IN p_courseId INT,
    IN p_centerId INT,
    IN p_trainerId INT,
    IN p_unitStartId INT,
    IN p_startDayInWeek INT,
    IN p_startTime TIME,
    IN p_startDate DATE,
    IN p_limitUser INT,
    IN p_facilitatorId INT,
    IN p_createdBy INT,
    IN p_classTypeId INT
)
BEGIN

    DECLARE v_errorText TEXT;
    DECLARE v_unit_id INT;
	DECLARE v_end_cursor BOOLEAN DEFAULT FALSE;
    DECLARE v_group_class_id INT;
    DECLARE v_study_date DATE;
    DECLARE v_is_holiday BOOLEAN DEFAULT FALSE;
    DECLARE v_index INT DEFAULT 0;
        
	DECLARE unit_cur CURSOR FOR 
    SELECT unit_id
	FROM 
    (SELECT @curRow1 := @curRow1 + 1 as row_number,
		   unit_id
    FROM sg_unit
	WHERE course_id = p_courseId
		  AND class_type_id = p_classTypeId
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
    
    IF ADDTIME(p_startDate, p_startTime) <= NOW() OR (DAYOFWEEK(p_startDate) - 1) != p_startDayInWeek THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid start date';
	ELSEIF NOT EXISTS (SELECT 1 FROM sg_unit WHERE unit_id = p_unitStartId AND course_id = p_courseId AND class_type_id = p_classTypeId) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid start unit';
	END IF;
	
	/**Start transaction*/
	START TRANSACTION;
    
    SET @v_unit_start_index := 0;
    SET @curRow1 = 0;
    SET @curRow = 0;
    
    SELECT t.row_number
    INTO @v_unit_start_index
    FROM
    (SELECT @curRow := @curRow + 1 as row_number,
		   unit_id
    FROM sg_unit
	WHERE course_id = p_courseId
		  AND class_type_id = p_classTypeId
		  AND is_active = 1
		  AND is_delete = 0
	ORDER BY IFNULL(sort_order, 9999) ASC, unit_id ASC) t
    WHERE t.unit_id = p_unitStartId;

    
    INSERT INTO sg_offline_group_class (class_type_id,
										center_id,
										trainer_id,
                                        course_id,
                                        unit_start_id,
                                        start_date,
                                        start_date_in_week,
                                        start_time,
                                        limit_user,
                                        created_by,
                                        facilitator_id,
                                        group_class_name)
	VALUE(p_classTypeId,
		  p_centerId,
          p_trainerId,
          p_courseId,
          p_unitStartId,
          p_startDate,
          p_startDayInWeek + 1,
          p_startTime,
          p_limitUser,
          p_createdBy,
          p_facilitatorId,
          p_groupClassName);
    
    SET v_group_class_id = LAST_INSERT_ID();
    
    OPEN unit_cur;
    
    get_unit: LOOP
		
        
        FETCH unit_cur INTO v_unit_id;

		IF v_end_cursor THEN LEAVE get_unit;
		END IF;
        SET v_is_holiday = TRUE;
        
        WHILE(v_is_holiday) DO
		   
           SET v_study_date = DATE_ADD(p_startDate, INTERVAL ( v_index * 7) DAY);
           
           SELECT CASE WHEN COUNT(1) > 0 THEN TRUE ELSE FALSE END
           INTO v_is_holiday
           FROM sg_setting_holiday
           WHERE is_active = 1
				 AND is_delete = 0
                 AND STR_TO_DATE( CONCAT(`day`, '/', `month`, '/', `year`), '%d/%m/%Y') = v_study_date;
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
		VALUE(p_classTypeId,
			  1,/*first*/
              v_group_class_id,
              v_unit_id,
              p_trainerId,
              v_study_date,
              p_startTime,
              p_createdBy,
              p_facilitatorId);
        
	END LOOP get_unit;

	CLOSE unit_cur;
	
	SELECT 1 `id`,
		   'Group class added' `message`;
	
	/**Commit update and insert*/
	COMMIT;
END //
delimiter ;