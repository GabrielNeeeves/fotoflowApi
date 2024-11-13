--CADASTRAR FOTOGRAFO
CREATE PROCEDURE SP_cadFotografo (
    nome VARCHAR(200),
    email VARCHAR(200),
    senha VARCHAR(200),
    tel VARCHAR(200),
    ende VARCHAR(200),
    esp VARCHAR(200),
    cert VARCHAR(200)
)
LANGUAGE plpgsql
AS $$
DECLARE
    ultimo_id INT;
BEGIN
    -- INSERT USUARIO
    INSERT INTO USUARIOS (NOME, EMAIL, SENHA, TELEFONE, ENDERECO)
    VALUES (nome, email, senha, tel, ende)
    RETURNING ID_USUARIO INTO ultimo_id;

    -- INSERT FOTOGRAFO COM O ID CRIADO ACIMA
    INSERT INTO FOTOGRAFOS (ID_FOTOGRAFO, ESPECIALIDADE, CERTIFICACOES)
    VALUES (ultimo_id, esp, cert);
END;
$$;

CALL SP_cadFotografo('Maria', 'maria@gmail.com', 'M2R1a123@', '123456789', 'Rua Pereira, 14', 'Urbanismo', 'bacharelado');

SELECT * FROM v_fotografos

--ATUALIZAR FOTOGRAFO
CREATE PROCEDURE SP_atualizarFotografo (
    fotografo_id INT,
    novo_nome VARCHAR(200),
    novo_email VARCHAR(200),
    novo_senha VARCHAR(200),
    novo_tel VARCHAR(200),
    novo_ende VARCHAR(200),
    novo_esp VARCHAR(200),
    novo_cert VARCHAR(200)
)
LANGUAGE plpgsql
AS $$
BEGIN
    -- Verificar se o fotografo_id existe na tabela FOTOGRAFOS
    IF NOT EXISTS (SELECT 1 FROM FOTOGRAFOS WHERE ID_FOTOGRAFO = fotografo_id) THEN
        RAISE EXCEPTION 'Fotógrafo com ID % não encontrado.', fotografo_id;
    END IF;

    -- Atualizar os dados do fotógrafo na tabela USUARIOS
    UPDATE USUARIOS
    SET NOME = novo_nome,
        EMAIL = novo_email,
        SENHA = novo_senha,
        TELEFONE = novo_tel,
        ENDERECO = novo_ende
    WHERE ID_USUARIO = fotografo_id;

    -- Atualizar os dados específicos do fotógrafo na tabela FOTOGRAFOS
    UPDATE FOTOGRAFOS
    SET ESPECIALIDADE = novo_esp,
        CERTIFICACOES = novo_cert
    WHERE ID_FOTOGRAFO = fotografo_id;
END;
$$;
DROP PROCEDURE SP_atualizarFotografo

CALL SP_atualizarFotografo(4, 'João Silva', 'joao.novo@email.com', 'nova_senha123', '987654321', 'Novo Endereço', 'Fotografia de Moda', 'Certificação ABC');
SELECT * FROM v_fotografos

--CADASTRAR CLIENTE
CREATE PROCEDURE SP_cadCliente (
    nome VARCHAR(200),
    email VARCHAR(200),
    senha VARCHAR(200),
    tel VARCHAR(200),
    ende VARCHAR(200)
)
LANGUAGE plpgsql
AS $$
DECLARE
    ultimo_id INT;
BEGIN
    -- Inserindo o usuário e capturando o ID gerado
    INSERT INTO USUARIOS (NOME, EMAIL, SENHA, TELEFONE, ENDERECO)
    VALUES (nome, email, senha, tel, ende)
    RETURNING ID_USUARIO INTO ultimo_id;

    -- Inserindo o cliente com o ID do usuário criado
    INSERT INTO CLIENTES (ID_CLIENTE)
    VALUES (ultimo_id);
END;
$$;

CALL SP_cadCliente('Pedro', 'pedro@gmail.com', 'p3dr@a0', '(12)34434-5544', 'Rua Gloria Jardim, 97');

SELECT * FROM v_clientes


--CADASTRAR ALBUM
CREATE PROCEDURE SP_cadAlbum (
    nome VARCHAR(200),
    tipo_fotografia VARCHAR(200),
    tipo_pacote VARCHAR(100),
    fotografo_id INT
)
LANGUAGE plpgsql
AS $$
DECLARE
    fotografo_existe BOOLEAN;
BEGIN
    -- Verificar se o fotografo_id existe na tabela FOTOGRAFOS
    SELECT EXISTS (SELECT 1 FROM FOTOGRAFOS WHERE ID_FOTOGRAFO = fotografo_id) INTO fotografo_existe;

    -- Se o fotografo_id não existir, lançar uma exceção
    IF NOT fotografo_existe THEN
        RAISE EXCEPTION 'Fotógrafo com ID % não existe.', fotografo_id;
    END IF;

    -- Inserir o novo álbum se o fotógrafo existir
    INSERT INTO ALBUM (NOME, TIPO_FOTOGRAFIA, TIPO_PACOTE, CURTIDAS, FOTOGRAFO_ID)
    VALUES (nome, tipo_fotografia, tipo_pacote, 0, fotografo_id);
END;
$$;

DROP PROCEDURE sp_cadAlbum

CALL SP_cadAlbum('Paisagens Naturais', 'Natureza', 'OURO', 2);

SELECT * FROM v_album


--CADASTRAR FOTO
CREATE PROCEDURE SP_cadFoto (
    url VARCHAR(200),
    descricao VARCHAR(200),
    album_id INT
)
LANGUAGE plpgsql
AS $$
DECLARE
    album_existe BOOLEAN;
BEGIN
    -- Verificar se o album_id existe na tabela ALBUM
    SELECT EXISTS (SELECT 1 FROM ALBUM WHERE ID_ALBUM = album_id) INTO album_existe;

    -- Se o album_id não existir, lançar uma exceção
    IF NOT album_existe THEN
        RAISE EXCEPTION 'Álbum com ID % não existe.', album_id;
    END IF;

    -- Inserir a foto se o álbum existir
    INSERT INTO FOTOS (URL, DESCRICAO, ALBUM_ID)
    VALUES (url, descricao, album_id);
END;
$$;

CALL SP_cadFoto('http://exemplo.com/foto.jpg', 'foto de varias arvores verdes', 6);

SELECT * FROM v_fotos
