-- 商品表
create table `product_info`(
	`product_id` varchar(32) not null,
	`product_name` varchar(64) not null comment '商品名称', 
	`product_price` decimal(8,2) not null comment '商品金额',
	`product_stock` int not null comment '商品库存',
	`product_description` varchar(64) comment '商品描述',
	`product_icon` varchar(512) comment '商品小图',
	`product_status` tinyint(3) not null comment '商品状态',
	`category_type` int not null comment '类目编号',
	`create_time` timestamp not null default current_timestamp comment '创建时间', -- 在创建该条时自动插入当前时间在这个字段
	`update_time` timestamp not null default current_timestamp on update 
		current_timestamp comment '修改时间', -- 在修改该条目时自动插入当前时间在这个字段
	primary key (`product_id`) -- 设置 product_id 为该表主键
) comment '商品表';

-- 类目表
create table `product_category`(
	`category_id` int not null auto_increment, -- 自增
	`category_name` varchar(64) not null comment '类目名字',
	`category_type` int not null comment '类目标号',
	`create_time` timestamp not null default current_timestamp comment '创建时间', -- 在创建该条时自动插入当前时间在这个字段
	`update_time` timestamp not null default current_timestamp on update 
		current_timestamp comment '修改时间', -- 在修改该条目时自动插入当前时间在这个字段
	primary key (`category_id`), -- 设置 product_id 为该表主键
	unique key `uqe_category_type` (`category_type`) -- 唯一设置
) comment '类目表';


--订单表
create table `order_master`(
	`order_id` varchar(32) not null,
	`buyer_name` varchar(32) not null comment '买家名字',
	`buyer_phone` varchar(32) not null comment '买家电话',
	`buyer_address` varchar(128) not null comment '买家地址',
	`buyer_openid` varchar(64) not null comment '买家微信openid',
	`order_amount` decimal(8,2) not null comment '订单总金额',
	`order_status` tinyint(3) not null default '0' comment '订单状态,默认0新下单', -- tinyint 占用一个字节  取值范围从-128 到 127
	`pay_status` tinyint(3) not null default '0' comment '支付状态,默认0未支付',
	`create_time` timestamp not null default current_timestamp comment '创建时间', -- 在创建该条时自动插入当前时间在这个字段
	`update_time` timestamp not null default current_timestamp on update 
		current_timestamp comment '修改时间', -- 在修改该条目时自动插入当前时间在这个字段
	primary key (`order_id`),
	key `idx_buyer_openid` (`buyer_openid`) -- 加一个索引,根据微信openid查询
) comment '订单表';


-- 订单详情表
create table `order_detail` (
	`detail_id` varchar(32) not null,
	`order_id` varchar(32) not null,
	`product_id` varchar(32) not null,
	`product_name` varchar(64) not null comment '商品名称',
	`product_price` decimal(8,2) not null comment '商品价格',
	`product_quantity` int not null comment '商品数量',
	`product_icon` varchar(512) comment '商品小图',
	`create_time` timestamp not null default current_timestamp comment '创建时间', -- 在创建该条时自动插入当前时间在这个字段
	`update_time` timestamp not null default current_timestamp on update 
		current_timestamp comment '修改时间', -- 在修改该条目时自动插入当前时间在这个字段
	primary key (`detail_id`),
	key `idx_order_id` (`order_id`) -- 加一个索引，根据订单 id 查询
) comment '订单详情表';






















































