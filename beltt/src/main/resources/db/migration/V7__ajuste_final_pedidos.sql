-- 1. Garante que a tabela pedidos tenha a estrutura correta
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='pedidos' AND column_name='data_pedido') THEN
ALTER TABLE pedidos ADD COLUMN data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
END IF;

    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='pedidos' AND column_name='valor_total') THEN
ALTER TABLE pedidos ADD COLUMN valor_total DECIMAL(10,2);
END IF;
END $$;

-- 2. Recria o Trigger de Log para garantir que ele aponte para as colunas certas
CREATE OR REPLACE FUNCTION log_pedido()
RETURNS TRIGGER AS $$
BEGIN
INSERT INTO log_pedidos (pedido_id, acao)
VALUES (NEW.fk_pedido_id, 'item adicionado');
RETURN NEW;
END;
$$ LANGUAGE plpgsql;