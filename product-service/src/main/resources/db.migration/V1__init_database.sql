drop table if exists category;
create table if not exists category(
    id integer not null primary key,
    name varchar(255),
    description varchar(255)
);

drop table if exists product;
create table if not exists product(
    id integer not null primary key,
    name varchar(255),
    description varchar(255),
    price numeric(38, 2),
    quantity integer,
    language varchar(50),
    publish_date date,
    author varchar(100),
    publisher varchar(255),
    image varchar(255),
    category_id integer not null constraint fk_product_category references category
);

drop sequence if exists category_seq;
drop sequence if exists product_seq;
create sequence if not exists category_seq increment by 1;
create sequence if not exists product_seq increment by 1;
