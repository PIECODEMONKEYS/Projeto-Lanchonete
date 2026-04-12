-- 1. Cria a tabela APENAS se ela ainda não tiver sido criada por outras migrations
CREATE TABLE IF NOT EXISTS pedidos (
                                       id SERIAL PRIMARY KEY,
                                       data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       valor_total DECIMAL(10,2) NOT NULL
    );

-- 2. Criação da tabela de logs
CREATE TABLE IF NOT EXISTS log_pedidos (
                                           id SERIAL PRIMARY KEY,
                                           pedido_id INT,
                                           acao VARCHAR(50),
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- 3. Função e Trigger (seu código original)
CREATE OR REPLACE FUNCTION log_pedido()
RETURNS TRIGGER AS $$
BEGIN
INSERT INTO log_pedidos (pedido_id, acao)
VALUES (NEW.fk_pedido_id, 'item adicionado');
RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

DROP TRIGGER IF EXISTS tg_log_pedido ON pedidos_produtos;
CREATE TRIGGER tg_log_pedido
    AFTER INSERT ON pedidos_produtos
    FOR EACH ROW
    EXECUTE FUNCTION log_pedido();