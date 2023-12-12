use mybatis;

drop table if exists tb_brand;

create table tb_brand (
	id           int primary key auto_increment,  -- 主键
	brand_name   varchar(20), -- 品牌名
    company_name varchar(20), -- 企业名
    ordered      int,         -- 排序字段
    description  varchar(100),-- 描述信息
    status       int          -- 状态tb_brand
);

insert into tb_brand 
( brand_name, company_name, ordered, description, status ) 
values 
('三只松鼠', '三只松鼠股份有限公司', 5, '好吃不上火', 0),
('华为', '华为技术有限公司', 100, '华为致力于把数字世界带入每个人、每个家庭、每个组织，构建万物互联的智能世界', 1),
('小米', '小米科技有限公司', 50, 'are you ok', 1);









