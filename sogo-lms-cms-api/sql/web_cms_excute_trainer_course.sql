delimiter //

DROP PROCEDURE IF EXISTS web_cms_excute_trainer_course;
//

CREATE PROCEDURE PROCEDURE `web_cms_excute_trainer_course`(
	p_course_id INT,
	p_trainer_id INT,
    p_excute_type VARCHAR(1)
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
			IF EXISTS (SELECT 1 FROM sg_course_trainer WHERE course_id = p_course_id AND trainer_id = p_trainer_id) THEN
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Trainer course does exist';
			END IF;
			INSERT INTO sg_course_trainer(course_id,trainer_id,is_active,is_delete)
			VALUES (p_course_id,p_trainer_id,1,0);
		ELSE 
			DELETE FROM sg_course_trainer
            WHERE course_id=p_course_id
            AND trainer_id=p_trainer_id;
        END IF;
	 SELECT 1 `id`,
			   'Trainer course excuted successful' `message`;
END
//

delimiter ;