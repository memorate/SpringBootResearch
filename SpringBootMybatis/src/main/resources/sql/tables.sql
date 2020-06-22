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

create table date_table
(
	id int auto_increment,
	date_one date null,
	date_two date null,
	date_three date null,
	date_four date null,
	date_five date null,
	date_six date null,
	constraint date_table_pk
		primary key (id)
);

create table datetime_table
(
	id int auto_increment,
	datetime_one datetime null,
	datetime_two datetime null,
	datetime_three datetime null,
	datetime_four datetime null,
	datetime_five datetime null,
	datetime_six datetime null,
	constraint datetime_table_pk
		primary key (id)
);

create table year_table
(
	id int auto_increment,
	year_one year null,
	year_two year null,
	year_three year null,
	year_five year null,
	year_six year null,
	constraint year_table_pk
		primary key (id)
);



