create table todo_data
(
    id bigserial not null
        constraint todo_data_pk
            primary key,
    task_name varchar default 255 not null,
    task_context varchar not null,
    create_date timestamp not null,
    deadline timestamp not null
);
