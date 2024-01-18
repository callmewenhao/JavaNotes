USE mybatis;
DROP TABLE IF EXISTS tb_book;
CREATE TABLE IF NOT EXISTS tb_book 
(
	id           INT PRIMARY KEY AUTO_INCREMENT,
    type         VARCHAR(20),
    name         VARCHAR(50),
    description  VARCHAR(200)
);
INSERT INTO tb_book (
	type,
    name,
    description
) VALUES (
	'数学',
    '高等数学上册',
    '理工科必修的数学教材'
), (
	'数学',
    '高等数学下册',
    '理工科必修的数学教材'
)