create table roles(
    id serial primary key,
    role varchar(50)
);

create table "rules"(
    id serial primary key,
    rule varchar(50)
);

create table categories(
    id serial primary key,
    category varchar(20)
);

create table states(
    id serial primary key,
    state varchar(20)
);

create table "users"(
    id serial primary key,
    "name" varchar(50),
    role_id int references roles(id)
);

create table items(
    id serial primary key,
    item varchar(50),
    user_id int references "users"(id),
    category_id int references categories(id),
    states_id int references states(id)
);

create table comments(
    id serial primary key,
    comment_text text,
    item_id int references items(id)
);

create table attachs(
    id serial primary key,
    attached text,
    item_id int references items(id)
);

create table roles_rules(
    id serial primary key,
    rule_id int references "rules"(id),
    role_id int references roles(id)
);
