delimiter //

DROP PROCEDURE IF EXISTS web_cms_add_new_trainer_course;
//

CREATE PROCEDURE PROCEDURE PROCEDURE `web_cms_excute_unit_course`(
	p_course_id INT,
	p_unit_title VARCHAR(250),
    p_excute_type VARCHAR(1),
    p_unit_des BLOB,
    p_unit_summary LONGBLOB
    
)
BEGIN
    
	DECLARE errorText TEXT;
    
    /**Delare handler sql_exception*/
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
	  GET DIAGNOSTICS CONDITION 1 errorText = MESSAGE_TEXT;
		  SELECT -1 `id`,
				errorText `message`;
	  ROLLBACK;
	END;
     
	 IF p_excute_type='I' THEN
		IF EXISTS (SELECT 1 FROM sg_unit WHERE course_id = p_course_id AND unit_title = p_unit_title) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unit course does exist';
		END IF;
		INSERT INTO sg_unit(course_id,unit_title,is_active,is_delete)
		VALUES (p_course_id,p_unit_title,1,0);
    ELSEIF p_excute_type='U' THEN
		UPDATE sg_unit
		SET unit_des=p_unit_des,unit_summary=p_unit_sumary
		WHERE course_id=p_course_id
		AND unit_title=p_unit_title;
    ELSE 
		DELETE FROM sg_unit
		WHERE course_id=p_course_id
		AND unit_title=p_unit_title;
	END IF;
	 SELECT 1 `id`,
			   'Unit course inserted' `message`;
END//

delimiter ;