create table ingredientes
(
    id serial not null primary key,
    nome varchar(30) not null,
    estoque integer not null,
    frequencia varchar(20) not null
)