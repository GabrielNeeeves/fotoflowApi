--FUNCTION
CREATE OR REPLACE FUNCTION cancelar_pagamento()
RETURNS TRIGGER AS $$
BEGIN
    -- Alterar o status para "CANCELADO" ao invés de excluir o registro
    UPDATE PAGAMENTOS
    SET STATUS = 'CANCELADO'
    WHERE ID_PAGAMENTO = OLD.ID_PAGAMENTO;
    
    -- Impedir a exclusão do registro
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

--TRIGGER
CREATE TRIGGER trg_cancelar_pagamento
BEFORE DELETE ON PAGAMENTOS
FOR EACH ROW
EXECUTE FUNCTION cancelar_pagamento();

