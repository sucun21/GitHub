delimiter //

DROP PROCEDURE IF EXISTS web_cms_get_all_comment_result;
//

CREATE PROCEDURE `web_cms_get_all_comment_result`(
	p_comment_type_id INT
)
BEGIN
    
    SELECT comment_result_id `commentResultId`,
		   comment_result_name `commentResultName`,
           description `commentResultDescription`
    FROM sg_comment_result
    WHERE comment_type_id = p_comment_type_id;
    
	
END
//

delimiter ;