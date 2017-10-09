delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_student_info;
//

CREATE PROCEDURE `web_cms_get_student_info`(
	p_user_id INT
)
BEGIN
	DECLARE v_lead_id INT DEFAULT 0;

	SELECT user_id `userId`,
           user_email `userEmail`,
           user_phone `userPhone`,
           full_name `fullName`,
           avatar_url `avatarUrl`,
           is_active `isActive`,
           gender,
           TIMESTAMP(date_of_birth) `dateOfBirth`,
           v_lead_id `leadId`,
           'ACADEMY' `type`
    FROM sg_user
    WHERE is_delete = 0
    AND user_id = p_user_id
	ORDER BY created_date DESC;
END
//

delimiter ;