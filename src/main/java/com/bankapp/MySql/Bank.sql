CREATE TABLE banking(account_number INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(100),
age INT ,
gender VARCHAR(10),
mobile_number VARCHAR(13) UNIQUE,
`password` VARCHAR(200),
amount INT,
`user` VARCHAR(10) DEFAULT 'user',
`status` VARCHAR(20) DEFAULT 'Inactive'
);
SELECT * FROM banking;
INSERT INTO 
banking(account_number,`name`,age,gender,mobile_number,`password`,amount,`user`,`status`) 
VALUES('663263','Admin','30','Male','7871170126','admin123','500','Admin','Active');
INSERT INTO 
banking(account_number,`name`,age,gender,mobile_number,`password`,amount,`user`,`status`) 
VALUES('663255','Thalapathy','30','Male','7871270126','tamil123','500','User','Active');
DROP TABLE banking;
TRUNCATE TABLE banking;