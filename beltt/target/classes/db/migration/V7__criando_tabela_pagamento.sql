create table pagamento (
    id_pagamento bigserial primary key,
    metodo varchar(20) not null,
    valor_original numeric(10,2) not null,
    taxa numeric(10,2) not null,
    valor_final numeric(10,2) not null,
    status varchar(20) not null,
    data_pagamento timestamp not null,
    fk_pedido_id bigint not null unique,
    constraint fk_pagamento_pedido
    foreign key (fk_pedido_id) references pedidos(id)
);