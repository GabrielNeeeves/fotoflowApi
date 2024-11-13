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

--ATUALIZAR CLIENTE
CREATE PROCEDURE sp_atualizarCliente (
	cliente_id int,
	novo_nome VARCHAR(200),
    novo_email VARCHAR(200),
    novo_senha VARCHAR(200),
    novo_tel VARCHAR(200),
    novo_ende VARCHAR(200)
)
LANGUAGE plpgsql
AS $$
BEGIN
	-- Verificar se o cliente_id existe na tabela CLIENTES
    IF NOT EXISTS (SELECT 1 FROM CLIENTES WHERE ID_CLIENTE = CLIENTE_ID) THEN
        RAISE EXCEPTION 'Cliente com ID % não encontrado.', CLIENTE_ID;
    END IF;

    -- Atualizar os dados do CLIENTE na tabela USUARIOS
    UPDATE USUARIOS
    SET NOME = novo_nome,
        EMAIL = novo_email,
        SENHA = novo_senha,
        TELEFONE = novo_tel,
        ENDERECO = novo_ende
    WHERE ID_USUARIO = CLIENTE_ID;
END;
$$;

CALL SP_ATUALIZARCLIENTE(3, 'Jose', 'jos@gmail.com', '123456789', '(34)34543-5454', 'Rua Exemplo, 789')

SELECT * FROM V_CLIENTES

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

CALL SP_cadAlbum('Paisagens Naturais', 'Natureza', 'OURO', 2);

--ATUALIZAR ALBUM
CREATE PROCEDURE SP_atualizarAlbum (
	album_id int,
	nv_nome varchar(100),
	nv_tipo_fotografia varchar(100),
	nv_tipo_pacote varchar(100),
	nv_curtidas int,
	nv_fotografo_id int
)
LANGUAGE PLPGSQL
AS $$
BEGIN
	--VERIFICA SE O FOTOGRAFO EXISTE
    IF NOT EXISTS (SELECT 1 FROM fotografos WHERE ID_FOTOGRAFO = nv_fotografo_id) THEN
        RAISE EXCEPTION 'Fotógrafo com ID % não encontrado.', nv_fotografo_id;
    END IF;

	--VERIFICA SE O ALBUM EXISTE
	IF NOT EXISTS(SELECT 1 FROM ALBUM WHERE ID_ALBUM = ALBUM_ID) THEN
		RAISE EXCEPTION 'Album com ID % não existe.', album_id;
	END IF;

UPDATE ALBUM
SET
	NOME = nv_nome,
	TIPO_FOTOGRAFIA = nv_tipo_fotografia,
	TIPO_PACOTE = nv_tipo_pacote,
	CURTIDAS = nv_curtidas,
	fotografo_id = nv_fotografo_id
	WHERE id_album = album_id;
END;
$$;

DROP PROCEDURE SP_atualizarAlbum

CALL SP_atualizarAlbum(6, 'Paisagens Urbanas', 'Urbanismo', 'PRATA', 5, 2)

SELECT * FROM v_album
SELECT * FROM V_FOTOGRAFOS
	

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


--ATULIZAR FOTO
CREATE PROCEDURE SP_atualizarFoto (
	foto_id int,
	nv_url varchar(200),
	nv_descricao varchar(200),
	nv_album_id int
)
LANGUAGE plpgsql
AS $$
BEGIN
	--VERIFICAR SE A FOTO EXISTE
	IF NOT EXISTS(SELECT 1 FROM FOTOS WHERE ID_FOTO = FOTO_ID) THEN 
		RAISE EXCEPTION 'Foto com ID % nao existe', foto_id;
	END IF;

	--VERIFICAR SE O ALBUM CORRESPONDETE EXISTE
	IF NOT EXISTS(SELECT 1 FROM ALBUM WHERE ID_ALBUM = NV_ALBUM_ID) THEN 
		RAISE EXCEPTION 'Album com ID % nao existe', nv_album_id;
	END IF;

	--ATUALIZAR FOTO
	UPDATE fotos
	SET URL = nv_url,
		DESCRICAO = nv_descricao,
		ALBUM_ID = nv_album_id
	WHERE ID_FOTO = FOTO_ID;
END;
$$;
DROP PROCEDURE sp_atualizarFoto

SELECT * FROM v_fotografos vf 
SELECT * FROM v_album va 
SELECT * FROM v_fotos vf 

CALL sp_cadFoto ('https://www.google.com/url', 'Arvore no por do Sol', 3)
CALL sp_atualizarFoto (1, 'https://www.google.com/url', 'Lagoa no nascer do Sol', 3)

SELECT * FROM fotos


--CRIAR PAGAMENTO
CREATE PROCEDURE SP_cadPagamento (
	sp_VALOR DECIMAL(10,2),
	sp_TIPO_PAGAMENTO VARCHAR(100),
	sp_STATUS VARCHAR(100),
	sp_DATA_CRIACAO DATE,
	sp_DATA_VENCIMENTO DATE,
	sp_CLIENTE_ID INT,
	sp_FOTOGRAFO_ID INT
)
LANGUAGE plpgsql
AS $$
DECLARE
	cliente_existe boolean;
	fotografo_existe boolean;
BEGIN
	--verificar se Cliente existe
	SELECT EXISTS(SELECT 1 FROM USUARIOS WHERE ID_USUARIO = sp_CLIENTE_ID) INTO CLIENTE_EXISTE;
	IF NOT CLIENTE_EXISTE THEN
		RAISE EXCEPTION 'Cliente com ID % não encontrado.', sp_CLIENTE_ID;
    END IF;

	--verificar se Fotografo existe
	SELECT EXISTS(SELECT 1 FROM USUARIOS WHERE ID_USUARIO = sp_FOTOGRAFO_ID) INTO fotografo_existe;
	IF NOT fotografo_existe THEN
		RAISE EXCEPTION 'Fotografo com ID % não encontrado.', sp_FOTOGRAFO_ID;
    END IF;

	insert into pagamentos (valor, TIPO_PAGAMENTO, STATUS, DATA_CRIACAO, DATA_VENCIMENTO, USUARIO_ID, FOTOGRAFO_ID) values
	(sp_VALOR, sp_TIPO_PAGAMENTO, sp_STATUS, sp_DATA_CRIACAO, sp_DATA_VENCIMENTO, sp_CLIENTE_ID, sp_FOTOGRAFO_ID);
END;
$$;

DROP PROCEDURE sp_cadPagamento

CALL SP_cadPagamento(100.00::DECIMAL(10,2), 'CARTAO', 'PENDENTE', '2024-11-15', '2024-11-20', 13, 12);

SELECT * FROM pagamentos p 
	
	
--ATUALIZAR PAGAMENTO
CREATE PROCEDURE SP_atualizarPagamento (
	pagamento_id int,
	sp_VALOR DECIMAL(10,2),
	sp_TIPO_PAGAMENTO VARCHAR(100),
	sp_STATUS VARCHAR(100),
	sp_DATA_CRIACAO DATE,
	sp_DATA_VENCIMENTO DATE,
	sp_CLIENTE_ID INT,
	sp_FOTOGRAFO_ID INT
)	
LANGUAGE plpgsql
AS $$
BEGIN

	--VERIFICA SE O PAGAMENTO EXISTE (PELO ID)
	IF NOT EXISTS(SELECT 1 FROM pagamentos WHERE id_pagamento = pagamento_id) THEN 
		RAISE EXCEPTION 'Pagamento com ID % nao existe', pagamento_id;
	END IF;

	--verificar se o Cliente existe
	IF NOT EXISTS (SELECT 1 FROM CLIENTES WHERE ID_CLIENTE = sp_CLIENTE_ID) THEN
        RAISE EXCEPTION 'Cliente com ID % não encontrado.', sp_CLIENTE_ID;
    END IF;

	--verificar se o Fotografo existe
	IF NOT EXISTS (SELECT 1 FROM FOTOGRAFOS WHERE ID_FOTOGRAFO = sp_FOTOGRAFO_ID) THEN
        RAISE EXCEPTION 'Fotógrafo com ID % não encontrado.', sp_FOTOGRAFO_ID;
    END IF;

	update pagamentos
	set valor = sp_VALOR,
		tipo_pagamento = sp_TIPO_PAGAMENTO,
		status = sp_STATUS,
		data_criacao = sp_data_criacao,
		data_vencimento = sp_data_vencimento,
		usuario_id = sp_CLIENTE_ID,
		fotografo_id = sp_fotografo_id
	where id_pagamento = pagamento_id;
END;
$$;
DROP PROCEDURE sp_atualizarPagamento

CALL sp_atualizarPagamento (1, 100.00, 'CARTAO', 'CONCLUIDO', '2024-11-15', '2024-11-20', 13, 12)
	
SELECT * FROM pagamentos

pagamento_id int,
	sp_VALOR DECIMAL(10,2),
	sp_TIPO_PAGAMENTO VARCHAR(100),
	sp_STATUS VARCHAR(100),
	sp_DATA_CRIACAO DATE,
	sp_DATA_VENCIMENTO DATE,
	sp_CLIENTE_ID INT,
	sp_FOTOGRAFO_ID INT
