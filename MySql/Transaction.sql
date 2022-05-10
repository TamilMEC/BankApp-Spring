CREATE TABLE `transaction` (
     id INT PRIMARY KEY AUTO_INCREMENT ,
    `mobilenumber` VARCHAR (255) NOT NULL,
    `useraccountnumber` VARCHAR (255),
    amount INT,
    `type` VARCHAR(255),
    accountnumber VARCHAR (255),
    currentbalance INT,
    `dateandtime` VARCHAR(50)
);
SELECT * FROM `transaction`;
DROP TABLE `transaction`;
TRUNCATE `transaction`;
