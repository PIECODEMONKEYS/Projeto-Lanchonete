-- 1. Criando o Pedido do Cliente A
INSERT INTO pedidos (valor_total) VALUES (51.40); -- Pedido ID 1

-- 2. Adicionando itens ao Pedido 1
-- Vamos pedir 1 X-Bacon Monstro (ID 2) e 1 X-Burguer Clássico (ID 1)
INSERT INTO pedidos_produtos (fk_pedido_id, fk_produto_id, quantidade) VALUES
                                                                           (1, 2, 1), -- 1x X-Bacon
                                                                           (1, 1, 1); -- 1x X-Burguer


-- 3. Criando o Pedido do Cliente B
INSERT INTO pedidos (valor_total) VALUES (48.00); -- Pedido ID 2

-- 4. Adicionando itens ao Pedido 2
-- Vamos pedir 2 X-Salada Leve (ID 3)
INSERT INTO pedidos_produtos (fk_pedido_id, fk_produto_id, quantidade) VALUES
    (2, 3, 2); -- 2x X-Salada