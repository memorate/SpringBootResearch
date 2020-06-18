create table users
(
	id int auto_increment,
	name varchar(18) null,
	age int null,
	description varchar(36) null,
	constraint users_pk
		primary key (id)
);



create table time_table
(
	id int auto_increment,
	time_one time null,
	time_two time null,
	time_three time null,
	time_four time null,
	time_five time null,
	time_six time null,
	constraint time_table_pk
		primary key (id)
);

create table timestamp_table
(
	id int auto_increment,
	timestamp_one timestamp null,
	timestamp_two timestamp null,
	timestamp_three timestamp null,
	timestamp_four timestamp null,
	timestamp_five timestamp null,
	timestamp_six   timestamp null,
	constraint timestamp_table_pk
		primary key (id)
);

