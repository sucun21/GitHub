delimiter //

DROP EVENT IF EXISTS job_update_student_uncomplete_online_unit;
//

CREATE EVENT job_update_student_uncomplete_online_unit
ON SCHEDULE EVERY 30 MINUTE
STARTS CURRENT_TIMESTAMP
DO CALL sp_update_student_uncomplete_online_unit();
//

delimiter ;