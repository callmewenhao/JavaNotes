USE mybatis;
DROP TABLE IF EXISTS tb_account;
CREATE TABLE IF NOT EXISTS tb_account 
(
	account_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name  VARCHAR(50) NOT NULL,
    money      INT NOT NULL DEFAULT 0
);
INSERT INTO tb_account (
	user_name,
    money
) VALUES (
	'zwh',
    1000
), (
	"xiaobendan",
    1000
);


