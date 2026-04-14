INSERT INTO pedidos (valor_total) VALUES (51.40); -- Pedido ID 1

INSERT INTO pedidos_produtos (fk_pedido_id, fk_produto_id, quantidade) VALUES
(1, 2, 1), -- 1x X-Bacon
(1, 1, 1); -- 1x X-Burguer


INSERT INTO pedidos (valor_total) VALUES (48.00); -- Pedido ID 2

INSERT INTO pedidos_produtos (fk_pedido_id, fk_produto_id, quantidade) VALUES
    (2, 3, 2); -- 2x X-Salada