USE mybatis;
DROP TABLE IF EXISTS tb_log;
CREATE TABLE IF NOT EXISTS tb_log 
(
	log_id      INT PRIMARY KEY AUTO_INCREMENT,
    info        VARCHAR(50) NOT NULL,
    createTime  Datetime NOT NULL
);