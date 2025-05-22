create table type
(
id serial primary key,
"name" varchar(50)
);

create table product
(
id  serial primary key,
"name" varchar(50),
type_id int references type(id),
expired_date date,
price numeric(6, 2)
)

insert into type(name) values('Сыр'),
('Молоко'),
('Мороженое'),
('Хлеб');

insert into product("name", type_id, price, expired_date) values
('Сыр адыгейский', 1, 150.00, '2026-06-22'),
('Сыр моцарела', 1, 250.00, '2026-06-22'),
('Сыр росиийский', 1, 220.00, '2026-07-23'),
('Сыр фета', 1, 100.00, '2025-09-18'),
('Сыр рокфор', 1, 350.00, '2025-09-01'),
('Сыр дорблю', 1, 210.00, '2025-10-12'),
('Сыр пошехонский', 1, 135.00, '2025-06-15'),
('Сыр голландский', 1, 230.00, '2025-06-14'),
('Сыр колбасный', 1, 120.00, '2025-06-13'),
('Сыр эдам', 1, 170.00, '2025-06-11'),
('Сыр пармезан', 1, 350.00, '2025-06-10'),
('Сыр охотничий', 1, 200.00, '2025-06-11'),
('Молоко переделкино', 2, 65.00, '2025-06-05'),
('Молоко простоквашино', 2, 75.00, '2025-06-21'),
('Молоко рузское', 2, 95.00, '2025-05-24'),
('Молоко кленово', 2, 85.00, '2025-05-26'),
('Молоко можайское', 2, 102.00, '2025-10-25'),
('Мороженое лакомка', 3, 50.00, '2025-11-25'),
('Мороженое фрукторое', 3, 75.00, '2026-01-25'),
('Мороженое ленинградское', 3, 100.00, '2025-11-25'),
('Мороженое пломбир', 3, 350.00, '2025-11-25'),
('Хлеб бородинский', 4, 45.00, '2025-06-15'),
('Хлеб батон нарезной', 4, 35.00, '2025-06-01');


select *
from "type" as t
inner join product as p  on p.type_id = t.id
where t."name" = 'Сыр';

select *
from "type" as t
inner join product as p  on p.type_id = t.id
where  lower(p."name") like lower('%Мороженое%');

select "name" as имя_просроченного_продукта, expired_date as срок_годности
from product
where expired_date < current_date;

select t.name as имя_типа, p.name as имя_самого_дорогого_продукта, p.price as цена
from "type" as t
inner join product as p  on p.type_id = t.id
where p.price = (
    select max(price)
    from product
);

select t.name as имя_типа, count(p.id)
from "type" as t
inner join product as p  on p.type_id = t.id
group by t.name;

select *
from "type" as t
inner join product as p  on p.type_id = t.id
where t.name in ('Сыр', 'Молоко')
order by p.id asc;

select t.name, count(p.type_id) as количество_оставшихся_продуктов
from "type" as t
inner join product as p  on p.type_id = t.id
group by t.name
having count(p.type_id) < 10;


select t."name", p."name"
from "type" as t
inner join product as p  on p.type_id = t.id
order by t.name asc;