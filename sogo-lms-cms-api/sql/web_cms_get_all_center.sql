delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_all_center;
//

CREATE PROCEDURE `web_cms_get_all_center`()
BEGIN
	SELECT center_id `centerId`,
		   center_name `centerName`,
           center_address `centerAddress`,
           hotline,
           is_active `isActive`,
           created_date	`createdDate`,
           created_by `createdBy`,
           modified_date `modifiedDate`,
           modified_by `modifiedBy`
    FROM sg_center
    WHERE is_active > 0
          AND is_delete = 0
	ORDER BY created_date;
END
//

delimiter ;