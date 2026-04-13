CREATE TABLE log_pedidos (
    id bigserial PRIMARY KEY,
    pedido_id INT,
    acao VARCHAR(50),
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create or replace function log_pedido()
returns trigger as $$
begin
insert into log_pedidos (pedido_id, acao)
values (new.fk_pedido_id, 'item adicionado');

return new;
end;
$$ language plpgsql;

create trigger tg_log_pedido
    after insert on pedidos_produtos
    for each row
    execute function log_pedido();