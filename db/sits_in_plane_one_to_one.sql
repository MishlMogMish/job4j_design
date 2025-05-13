create table passengers (
    id serial primary key,
    passenger_name varchar(255) not null
);

create table seats (
    id serial primary key,
    seat_number varchar(10) not null unique
);

create table seat_assignments (
    id serial primary key,
    passenger_id int unique references passengers(id),
    seat_id int unique references seats(id)
);

