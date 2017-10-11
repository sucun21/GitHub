delimiter //

DROP FUNCTION IF EXISTS convert_to_unsign;
//

CREATE FUNCTION convert_to_unsign (inputVar VARCHAR(1000)) RETURNS VARCHAR(1000)
BEGIN    
	
	DECLARE RT VARCHAR(1000);
	DECLARE SIGN_CHARS VARCHAR(256);
	DECLARE UNSIGN_CHARS VARCHAR(256);
    DECLARE COUNTER int;
	DECLARE COUNTER1 int;
    DECLARE charIndex INT;

    IF inputVar IS NOT NULL AND inputVar <> '' THEN
        
        /*SET RT = inputVar;*/
		SET SIGN_CHARS = 'ăâđêôơưàảãạáằẳẵặắầẩẫậấèẻẽẹéềểễệếìỉĩịíòỏõọóồổỗộốờởỡợớùủũụúừửữựứỳỷỹỵýĂÂĐÊÔƠƯÀẢÃẠÁẰẲẴẶẮẦẨẪẬẤÈẺẼẸÉỀỂỄỆẾÌỈĨỊÍÒỎÕỌÓỒỔỖỘỐỜỞỠỢỚÙỦŨỤÚỪỬỮỰỨỲỶỸỴÝĐĐ';
		SET UNSIGN_CHARS = 'aadeoouaaaaaaaaaaaaaaaeeeeeeeeeeiiiiiooooooooooooooouuuuuuuuuuyyyyyAADEOOUAAAAAAAAAAAAAAAEEEEEEEEEEIIIIIOOOOOOOOOOOOOOOUUUUUUUUUUYYYYYDD';
	   
		SET COUNTER = 1;
        
		WHILE (COUNTER <= CHAR_LENGTH(inputVar)) DO
			SET COUNTER1 = 1;
			
			myloop: WHILE (COUNTER1 <= CHAR_LENGTH(SIGN_CHARS) + 1) DO
				IF SUBSTRING(inputVar, COUNTER , 1) != ' ' AND SUBSTRING(SIGN_CHARS, COUNTER1, 1) = SUBSTRING(inputVar, COUNTER , 1) THEN
					IF COUNTER = 1 THEN
						SET inputVar = CONCAT(SUBSTRING(UNSIGN_CHARS, COUNTER1, 1), SUBSTRING(inputVar, COUNTER + 1, CHAR_LENGTH(inputVar) - 1));    
					ELSE
						SET inputVar = CONCAT(SUBSTRING(inputVar, 1, COUNTER - 1), SUBSTRING(UNSIGN_CHARS, COUNTER1, 1), SUBSTRING(inputVar, COUNTER + 1, CHAR_LENGTH(inputVar) - COUNTER));
					END IF;
					leave myloop;
				END IF;
				SET COUNTER1 = COUNTER1 +1;
			END WHILE;
			SET COUNTER = COUNTER +1;
		END WHILE;
        
    END IF;
    
    RETURN inputVar;
    
END

//

delimiter ;