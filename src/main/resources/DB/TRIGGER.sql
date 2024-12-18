--ASSIM QUE UM FOTOGRAFO FOR EXCLUIDO, SEU REGISTRO EM 'USUARIOS' TBM SERÁ APAGADO
CREATE FUNCTION excluir_usuario_associado()
RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM USUARIOS WHERE ID_USUARIO = OLD.ID_FOTOGRAFO;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_excluir_usuario
AFTER DELETE ON FOTOGRAFOS
FOR EACH ROW
EXECUTE FUNCTION excluir_usuario_associado();


--ASSIM QUE UM CLIENTE FOR EXCLUIDO, SEU REGISTRO EM 'USUARIOS' TBM SERÁ APAGADO
CREATE FUNCTION excluir_usuario_associado_cliente()
RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM USUARIOS WHERE ID_USUARIO = OLD.ID_CLIENTE;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER excluir_usuario_associado_cliente
AFTER DELETE ON CLIENTES
FOR EACH ROW
EXECUTE FUNCTION excluir_usuario_associado_cliente();

