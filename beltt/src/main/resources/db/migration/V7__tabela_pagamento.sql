create table pagamento(
    id_pagamento serial primary key ,
    fk_pedido_id int not null unique,
    metodo varchar(20) not null,
    valor_original numeric(10,2) not null,
    taxa numeric(10,2) not null ,
    valor_final numeric(10,2) not null,
    status varchar(20) not null,
    data_pagamento timestamp default current_timestamp,
    foreign key (fk_pedido_id) references pedidos(id)
);