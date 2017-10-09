delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_system_user;
//

CREATE PROCEDURE `web_cms_get_system_user`(
	p_search_key VARCHAR(250),
    p_user_status INT,
    p_user_type_id INT,
    p_page_number INT,
    p_page_size INT
)
BEGIN
	
    DECLARE v_offset_row INT;
    DECLARE v_total_row INT;

    set v_offset_row = (p_page_number - 1) * p_page_size;
    
    SET v_total_row = (SELECT COUNT(user_id)
    FROM sg_user
    WHERE user_type_id != 4
		And is_delete = 0
		AND (p_search_key is null or p_search_key = '' or user_email = p_search_key
			Or user_phone = p_search_key)
		AND (p_user_status is null or is_active = p_user_status)
		AND (p_user_type_id is null or user_type_id = p_user_type_id));
    
	SELECT su.user_id `userId`,
		   su.user_type_id `userTypeId`,
           sgt.user_type_name `userTypeName`,
           su.user_email `userEmail`,
           su.user_phone `userPhone`,
           su.full_name `fullName`,
           su.is_active `isActive`,
           v_total_row `totalRow`
    FROM sg_user su
    JOIN sg_user_type sgt on sgt.user_type_id = su.user_type_id
    WHERE su.user_type_id != 4
		And su.is_delete = 0
		AND (p_search_key is null or p_search_key = '' 
			or su.user_email = p_search_key
			Or su.user_phone = p_search_key
            OR UPPER(su.full_name_ascii) LIKE CONCAT('%', p_search_key, '%'))
		AND (p_user_status is null or su.is_active = p_user_status)
		AND (p_user_type_id is null or su.user_type_id = p_user_type_id)
	ORDER BY su.created_date desc
    LIMIT v_offset_row, p_page_size;
END
//

delimiter ;