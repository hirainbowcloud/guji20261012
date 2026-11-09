delimiter $$
CREATE PROCEDURE myChar()
BEGIN
        -- 定義變數i並給予初始值        
	    DECLARE i INT DEFAULT 65 ;     

        -- 建立temporary table
        CREATE TEMPORARY TABLE ascii_chart
        (ascii_code int, ascii_char CHAR(1));       

        -- 採用while…do…end while迴圈
        WHILE (i<91) DO
                INSERT INTO ascii_chart VALUES(i,CHAR(i));
                SET i=i+1;
        END WHILE;      

        select * from ascii_chart order by ascii_code;      

        -- 刪除temporary table

        drop table ascii_chart;

END$$
delimiter ;