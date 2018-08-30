DROP TABLE  IF EXISTS contact;

CREATE TABLE  contact
(id INT NOT NULL AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
  PRIMARY KEY (id)
);

drop PROCEDURE if EXISTS InsertRand;

DELIMITER $$
CREATE PROCEDURE InsertRand(IN NumRows INT)
    BEGIN
        DECLARE i INT;
        SET i = 1;
        START TRANSACTION;
        WHILE i <= NumRows DO
            INSERT INTO contact VALUES (i, conv(floor(rand() * 99999999999999), 20, 36));
            SET i = i + 1;
        END WHILE;
        COMMIT;
    END$$
DELIMITER ;

CALL InsertRand(1000000);
COMMIT ;





