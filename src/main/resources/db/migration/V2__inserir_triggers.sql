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