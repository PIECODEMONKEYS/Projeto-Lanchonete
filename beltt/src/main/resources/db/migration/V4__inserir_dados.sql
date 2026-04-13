INSERT INTO ingredientes (nome, estoque) VALUES
    ('Pão de Hambúrguer', 100),   -- ID 1
    ('Hambúrguer 150g', 150),     -- ID 2
    ('Queijo Cheddar', 200),      -- ID 3
    ('Bacon em Fatias', 80),      -- ID 4
    ('Alface', 50),               -- ID 5
    ('Tomate', 50);               -- ID 6

INSERT INTO produtos (nome, preco) VALUES
    ('X-Burguer Clássico', 22.50), -- ID 1
    ('X-Bacon Monstro', 28.90),    -- ID 2
    ('X-Salada Leve', 24.00);      -- ID 3

INSERT INTO produto_ingredientes (fk_produto_id, fk_ingredientes_id, quantidade) VALUES
(1, 1, 1), -- 1 Pão
(1, 2, 1), -- 1 Carne
(1, 3, 2), -- 2 Fatias de Queijo

(2, 1, 1), -- 1 Pão
(2, 2, 2), -- 2 Carnes (Monstro!)
(2, 3, 2), -- 2 Fatias de Queijo
(2, 4, 4), -- 4 Fatias de Bacon

(3, 1, 1), -- 1 Pão
(3, 2, 1), -- 1 Carne
(3, 3, 1), -- 1 Fatia de Queijo
(3, 5, 2), -- 2 Folhas de Alface
(3, 6, 3); -- 3 Fatias de Tomate