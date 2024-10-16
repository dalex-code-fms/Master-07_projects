DROP DATABASE IF EXISTS Shop;
CREATE DATABASE Shop;
USE shop;

CREATE TABLE T_Articles (
	IdArticle		int(4) 		PRIMARY KEY AUTO_INCREMENT,
	Description		varchar(30) NOT NULL,
	Brand			varchar(30) NOT NULL,
	UnitaryPrice	float(8) 	NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Souris', 'Logitoch', 65);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Clavier', 'Microhard', 49.5);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Systeme d''exploitation', 'Fenetres Vistouille', 150);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Tapis Souris', 'Chapeau Blue', 5);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Cle USB 8 To', 'Syno', 8);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'WebCam', 'Logitoch', 755);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Casque Audio', 'Syno', 105);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Laptop', 'PH', 1199);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'CD x 500', 'CETME', 250);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'DVD-R x 100', 'CETME', 99);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'DVD+R x 100', 'CETME', 105);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Batterie Laptop', 'PH', 80);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Office', 'Microsoft', 150);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'S10', 'Samxhung', 2000);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'MacBrute', 'Pomme', 2000);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Iphone50', 'Pomme', 20000);

SELECT * FROM T_Articles;

SELECT '* - - - - - - - - - - * EXO 1.2 * - - - - - - - - - - *' AS '';
SHOW tables;
SELECT '* - - - - - - - - - - * EXO 1.3 * - - - - - - - - - - *' AS '';
DESCRIBE T_Articles;
SELECT '* - - - - - - - - - - * EXO 1.4 * - - - - - - - - - - *' AS '';
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Nokia3310', 'Chuck Nokia', 103 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Clavier', 'Microhard', 50 );
SELECT * FROM T_Articles;
SELECT '* - - - - - - - - - - * EXO 1.5 * - - - - - - - - - - *' AS '';

SELECT '* - - - - - - - - - - * EXO 1.6 * - - - - - - - - - - *' AS '';
DELETE FROM T_Articles WHERE Description = 'Clavier' AND UnitaryPrice = 49.5; 
SELECT * FROM T_Articles;
SELECT '* - - - - - - - - - - * EXO 1.7 * - - - - - - - - - - *' AS '';
SELECT * FROM T_Articles WHERE UnitaryPrice > 100;
SELECT '* - - - - - - - - - - * EXO 1.8 * - - - - - - - - - - *' AS '';
SELECT * FROM T_Articles WHERE UnitaryPrice >= 50 AND UnitaryPrice <= 150;
SELECT '* - - - - - - - - - - * EXO 1.9 * - - - - - - - - - - *' AS '';
SELECT * FROM T_Articles ORDER BY UnitaryPrice;
SELECT '* - - - - - - - - - - * EXO 1.10 * - - - - - - - - - - *' AS '';
SELECT Description FROM T_Articles;
SELECT '* - - - - - - - - - - * EXO 1.11 * - - - - - - - - - - *' AS '';

SELECT '* - - - - - - - - - - * EXO 1.12 * - - - - - - - - - - *' AS '';
CREATE TABLE T_Categories (
	IdCategory		int(4) PRIMARY KEY AUTO_INCREMENT,
	CatName			varchar(30) NOT NULL,
	Description		varchar(100)
) ENGINE = InnoDB;


ALTER TABLE T_Articles ADD COLUMN IdCategory INT(4);
ALTER TABLE T_Articles ADD FOREIGN KEY(IdCategory) REFERENCES T_Categories(IdCategory);

INSERT INTO T_Categories ( CatName , Description ) VALUES ( 'Materiel Info', 'Ici ça depend de quel materiel on parle');
INSERT INTO T_Categories ( CatName , Description ) VALUES ( 'Smartphone', 'Boite electronique qui parle avec toi.... ou pas.');
INSERT INTO T_Categories ( CatName , Description ) VALUES ( 'PC', 'Boite avec des boutons et un ecran inclus ou sans boutons et ecran');
INSERT INTO T_Categories ( CatName , Description ) VALUES ( 'Materiel de Son', 'Materiel prefere des ORL');
INSERT INTO T_Categories ( CatName , Description ) VALUES ( 'Software', 'Certainement trés cher...');
INSERT INTO T_Categories ( CatName , Description ) VALUES ( 'Cameras', 'L"oeil de Sauron');

SELECT * FROM T_Categories;

SELECT '* - - - - - - - - - - * EXO 1.13 * - - - - - - - - - - *' AS '';

UPDATE T_Articles SET IdCategory = 1 WHERE Description = 'Souris';
UPDATE T_Articles SET IdCategory = 1 WHERE Description = 'Clavier';
UPDATE T_Articles SET IdCategory = 5 WHERE Description = 'Systeme d''exploitation';
UPDATE T_Articles SET IdCategory = 1 WHERE Description = 'Tapis Souris';
UPDATE T_Articles SET IdCategory = 1 WHERE Description = 'Cle USB 8 To';
UPDATE T_Articles SET IdCategory = 6 WHERE Description = 'WebCam';
UPDATE T_Articles SET IdCategory = 4 WHERE Description = 'Casque Audio';
UPDATE T_Articles SET IdCategory = 3 WHERE Description = 'Laptop';
UPDATE T_Articles SET IdCategory = 1 WHERE Description = 'CD x 500';
UPDATE T_Articles SET IdCategory = 1 WHERE Description = 'DVD-R x 100';
UPDATE T_Articles SET IdCategory = 1 WHERE Description = 'DVD+R x 100';
UPDATE T_Articles SET IdCategory = 1 WHERE Description = 'Batterie Laptop';
UPDATE T_Articles SET IdCategory = 5 WHERE Description = 'Office';
UPDATE T_Articles SET IdCategory = 2 WHERE Description = 'S10';
UPDATE T_Articles SET IdCategory = 3 WHERE Description = 'MacBrute';
UPDATE T_Articles SET IdCategory = 2 WHERE Description = 'Iphone50';
UPDATE T_Articles SET IdCategory = 2 WHERE Description = 'Nokia3310';

-- UPDATE T_Articles SET CatName = (SELECT CatName FROM T_Categories WHERE T_Categories.idCategory = T_Articles.IdCategory);

SELECT a.IdArticle, a.Description, a.Brand, a.UnitaryPrice, c.CatName FROM T_Articles AS a JOIN T_Categories AS c ON a.IdCategory = c.IdCategory;




