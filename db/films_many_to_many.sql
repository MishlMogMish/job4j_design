create table films(
    id serial primary key,
    title varchar(255)
);

create table actors(
    id serial primary key,
    actor_name varchar(255)
);

create table actors_films(
    id serial primary key,
    actor_id int references actors(id),
    film_id int references films(id)
);