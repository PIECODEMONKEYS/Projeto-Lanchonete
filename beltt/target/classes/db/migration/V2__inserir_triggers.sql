create or replace function fn_atualizar_estoque()
returns trigger as $$
begin
update ingredientes
set estoque = estoque - (produto_ingredientes.quantidade * NEW.quantidade)
    from produto_ingredientes
where produto_ingredientes.fk_produto_id = NEW.fk_produto_id
  and ingredientes.id = produto_ingredientes.fk_ingredientes_id;
return new;
end;
$$ language plpgsql;

create trigger tg_atualiza_estoque after insert
    on pedidos_produtos
    for each row
    execute function fn_atualizar_estoque();

CREATE OR REPLACE FUNCTION atualizar_total_pedido()
returns TRIGGER AS $$
BEGIN
UPDATE pedidos
SET valor_total = (
    SELECT SUM(pedidos_produtos.quantidade * produtos.preco)
    FROM pedidos_produtos
             JOIN produtos ON produtos.id = pedidos_produtos.fk_produto_id
    WHERE pedidos_produtos.fk_pedido_id = new.fk_pedido_id
)
WHERE pedidos.id = NEW.fk_pedido_id;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

create trigger tg_atualizar_valores after insert
    on pedidos_produtos
    for each row
    execute function atualizar_total_pedido();

