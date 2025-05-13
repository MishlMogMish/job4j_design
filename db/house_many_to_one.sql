create table house(
    id serial primary key,
    address varchar(255)
);

create table locals(
    id serial primary key,
    first_name varchar(255) ,
    house_id int references house(id)
);
