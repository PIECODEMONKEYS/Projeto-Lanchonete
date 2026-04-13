create table ingredientes
(
    id bigserial not null primary key,
    nome varchar(30) not null,
    estoque integer not null,
    constraint ingr_no_repeat_name unique (nome)
);

create table produtos(
    id bigserial primary key,
    nome varchar(100) not null,
    preco numeric(10,2) not null
);


create table pedidos(
    id bigserial primary key,
    data_hora timestamp default CURRENT_TIMESTAMP null,
    valor_total numeric(10,2) not null
);


create table pedidos_produtos(
    id bigserial primary key,
    fk_pedido_id bigint,
    fk_produto_id bigint,
    quantidade int,

    foreign key (fk_produto_id) references produtos(id),
    foreign key (fk_pedido_id) references pedidos(id)
);


create table produto_ingredientes(
    id bigserial primary key,
    fk_produto_id bigint,
    fk_ingredientes_id bigint,
    quantidade int,

    foreign key (fk_produto_id) references produtos(id),
    foreign key (fk_ingredientes_id) references ingredientes(id)
);

