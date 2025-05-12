-- create table visits (
--     id serial primary key,
--     city varchar(100) not null,
--     visit_date date not null,
--     duration_days integer not null check (duration_days > 0));

-- insert into visits(city, visit_date, duration_days) values ('Москва', '2025-11-05', 2);

-- select *  from visits;

delete from visits;
select * from visits;