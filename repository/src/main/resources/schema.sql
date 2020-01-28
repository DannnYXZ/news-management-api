create table author
(
    id      bigint      not null
        constraint author_pk
        primary key,
    name    varchar(30) not null,
    surname varchar(30) not null
);

alter table author
    owner to postgres;

create table news
(
    id                bigint        not null
        constraint news_pk
        primary key,
    title             varchar(30)   not null,
    short_text        varchar(100)  not null,
    full_text         varchar(2000) not null,
    creation_date     date          not null,
    modification_date date          not null
);

alter table news
    owner to postgres;

create table news_author
(
    news_id   bigint not null
        constraint news_id
        references news,
    author_id bigint not null
        constraint author_id
        references author
);

alter table news_author
    owner to postgres;

create table tag
(
    id   bigint      not null
        constraint tag_pk
        primary key,
    name varchar(30) not null
);

alter table tag
    owner to postgres;

create table news_tag
(
    news_id bigint not null
        constraint news_id
        references news,
    tag_id  bigint not null
        constraint tag_id
        references tag
);

alter table news_tag
    owner to postgres;

create table "user"
(
    id       bigint      not null
        constraint user_pk
        primary key,
    name     varchar(20) not null,
    surname  varchar(20) not null,
    login    varchar(30) not null,
    password varchar(30) not null
);

alter table "user"
    owner to postgres;

create table roles
(
    user_id   bigint      not null
        constraint user_id
        references "user",
    role_name varchar(30) not null
);

alter table roles
    owner to postgres;

