create table passport(
    id serial primary key,
    seria int,
    number int
);

create table people(
    id serial primary key,
    "name" varchar(255),
    passport_id int references passport(id) unique
);

insert into passport(seria, number) values (1111, 123456);

insert into passport(seria, number) values (1112, 123457);
insert into passport(seria, number) values (1113, 123458);

insert into people(name, passport_id) values ('Ivan', 1);
insert into people(name, passport_id) values ('Boris', 2);
insert into people(name, passport_id) values ('Petr', 3);
insert into people(name) values ('Vasya');
insert into people(name) values ('Anya');

select * from people inner
join passport on people.passport_id = passport.id;

select * from passport inner
join people on people.passport_id = passport.id;

select * from people
join passport on people.passport_id = passport.id;

create table "position"(
    id serial primary key,
    "name" varchar(255)
);

create table employees(
    id serial primary key,
    "name" varchar(255),
    position_id int references position(id)
);

insert into "position"("name") values ('programmer');
insert into "position"("name") values ('project manager');
insert into "position"("name") values ('director');

insert into employees("name", position_id) values('Boris', 1);
insert into employees("name", position_id) values('Ivan', 1);
insert into employees("name", position_id) values('Kiril', 1);
insert into employees("name", position_id) values ('Marina', 2);
insert into employees("name", position_id) values ('Pers', 3);

insert into employees("name") values ('Alexander');

select * from employees
join "position" on employees."position_id" = "position".id;


create table passport(
    id serial primary key,
    seria int,
    number int
);

create table people(
    id serial primary key,
    "name" varchar(255),
    passport_id int references passport(id) unique
);

insert into passport(seria, number) values (1111, 123456);
insert into passport(seria, number) values (1112, 123457);
insert into passport(seria, number) values (1113, 123458);

insert into people(name, passport_id) values ('Ivan', 1);
insert into people(name, passport_id) values ('Boris', 2);
insert into people(name, passport_id) values ('Petr', 3);
insert into people(name) values ('Vasya');
insert into people(name) values ('Anya');

select pp.name, p.seria, p.number
from people as pp join passport as p on pp.passport_id = p.id;

select pp.name  Имя, p.seria  Серия, p.number  Номер
from people as pp join passport as p on pp.passport_id = p.id;

select pp.name as "Имя владельца", p.seria Серия, p.number Номер
from people pp join passport p on pp.passport_id = p.id;
