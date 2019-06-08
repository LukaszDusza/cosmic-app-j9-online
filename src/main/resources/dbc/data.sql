drop table if exists planets;

create table planets (
  id int auto_increment primary key,
  name varchar(10) not null
);

insert into planets (id,planet_name, planet_info, planet_type) values (1, 'Earth','abc','cde');
insert into planets (id,planet_name, planet_info, planet_type) values (2, 'Mars','abc','cde');
insert into planets (id,planet_name, planet_info, planet_type) values (3, 'Jupiter','abc','cde');
insert into planets (id,planet_name, planet_info, planet_type) values (4, 'Wenus','abc','cde');
insert into planets (id,planet_name, planet_info, planet_type) values (5, 'Mercury','abc','cde');
insert into planets (id,planet_name, planet_info, planet_type) values (6, 'Neptun','abc','cde');
insert into planets (id,planet_name, planet_info, planet_type) values (7, 'Saturn','abc','cde');
insert into planets (id,planet_name, planet_info, planet_type) values (8, 'Uran','abc','cde');
insert into planets (id,planet_name, planet_info, planet_type) values (9, 'Pluto''abc','cde');


