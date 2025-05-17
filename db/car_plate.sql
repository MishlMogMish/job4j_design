create table car_plate(
id serial primary key,
number varchar(20),
country varchar(20)
);

create table car(
id serial primary key,
model varchar(20),
manuf_yr date,
car_plate_number int references car_plate(id) unique
);

insert into car_plate(number, country)
values
('GHN56778-89', 'France'),
('K9U 98799098', 'Africa'),
('МОС 34-67', 'ГДР')

insert into car(model, manuf_yr, car_plate_number) values
('BMW', '2005-10-11', 1),
('Fiat', '2015-10-12', 2),
('Reno', '2020-01-01', 3)

select c.model, cp.number, cp.country, c.manuf_yr
from car_plate as cp join car as c on cp.id = c.car_plate_number;


select c.model Модель, cp.number Номер, cp.country Страна, c.manuf_yr Год
from car_plate as cp join car as c on cp.id = c.car_plate_number;

select c.model Модель, cp.number Номер, cp.country Страна, c.manuf_yr as "Год производства"
from car_plate as cp join car as c on cp.id = c.car_plate_number;