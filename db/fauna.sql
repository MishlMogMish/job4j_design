create table fauna
(
    id             serial primary key,
    "name"           text,
    avg_age        int,
    discovery_date date
);

insert into fauna("name", avg_age, discovery_date)
values
('red_monkey', 30, '1600-01-01'),
('harvest_mouse', 30, '1600-01-01'),
('asian_elephant', 15000, '1750-01-01'),
('giraffe', 20000, NULL),
('blue_parrot', 350, '2005-04-11'),
('icefish', 10, '1750-01-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';

