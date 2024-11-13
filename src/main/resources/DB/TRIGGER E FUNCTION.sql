-- FUNÇÃO
CREATE OR REPLACE FUNCTION cancelar_pagamento()
RETURNS TRIGGER AS $$
BEGIN
    -- Apenas alterar o status para 'CANCELADO' antes de excluir
    UPDATE PAGAMENTOS
    SET STATUS = 'CANCELADO'
    WHERE ID_PAGAMENTO = OLD.ID_PAGAMENTO;

    -- Impedir a exclusão da linha, retornando NULL
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- TRIGGER
CREATE OR replace TRIGGER trg_cancelar_pagamento
BEFORE DELETE ON PAGAMENTOS
FOR EACH ROW
EXECUTE FUNCTION cancelar_pagamento();
