create table devices
(
    id    serial primary key,
    "name"  varchar(255),
     price numeric(8, 2)
);

create table people
(
    id   serial primary key,
    "name" varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices("name", price) values
('tablet', 25000.00),
('smartphone1', 10000.00),
('smartphone2', 30000.00),
('smartphone3', 50000.00),
('notebook', 180000.00),
('painting_tablet', 45000.00),
('smart_watches', 20000.00),
('ebook_reader', 15000.00);

insert into people("name") values
('Ivan'),
('Anton'),
('Katya');

insert into devices_people(people_id, device_id) values
(1, 1),
(1, 3),
(1, 5),
(2, 2),
(2, 7),
(3, 4),
(3, 8),
(3, 1);

select avg(price) from devices
select max(price) from devices
select min(price) from devices

select p."name", avg(d.price) from
devices_people as dp
join people p on p.id = dp.people_id
join devices d on d.id = dp.device_id
group  by p."name"

select p."name", avg(d.price) from
devices_people as dp
join people p on p.id = dp.people_id
join devices d on d.id = dp.device_id
group  by p."name"
having avg(d.price) > 20000;