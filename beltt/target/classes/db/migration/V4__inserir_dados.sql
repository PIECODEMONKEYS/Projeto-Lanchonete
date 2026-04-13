INSERT INTO ingredientes (nome, estoque) VALUES
    ('pão de hambúrguer', 100),   -- ID 1
    ('hambúrguer 150g', 150),     -- ID 2
    ('queijo cheddar', 200),      -- ID 3
    ('bacon em fatias', 80),      -- ID 4
    ('alface', 50),               -- ID 5
    ('tomate', 50);               -- ID 6

INSERT INTO produtos (nome, preco) VALUES
    ('x-burguer clássico', 22.50), -- ID 1
    ('x-bacon monstro', 28.90),    -- ID 2
    ('x-salada leve', 24.00);      -- ID 3

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