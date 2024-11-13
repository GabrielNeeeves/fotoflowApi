--USUARIOS
CREATE TABLE USUARIOS (
	ID_USUARIO SERIAL PRIMARY KEY,
	NOME VARCHAR(200) NOT NULL,
	EMAIL VARCHAR(200) NOT NULL,
	SENHA VARCHAR(200) NOT NULL,
	TELEFONE VARCHAR(200) NOT NULL,
<<<<<<< HEAD
	ENDERECO VARCHAR(200) NOT NULL
=======
	ENDERECO VARCHAR(200) NOT NULL,
	TIPO_USUARIO VARCHAR(200),
	CHECK (TIPO_USUARIO IN('FOTOGRAFO', 'CLIENTE'))
>>>>>>> 4707cf2e9110270b277e32353cbeaa6aff7755c8
)

SELECT * FROM usuarios

--FOTOGRAFOS
CREATE TABLE FOTOGRAFOS (
	ID_FOTOGRAFO INT NOT NULL,
	ESPECIALIDADE VARCHAR(200) NULL,
	CERTIFICACOES VARCHAR(200) NULL,
	--ID_ALBUM INT NULL,
	FOREIGN KEY (ID_FOTOGRAFO) REFERENCES USUARIOS(ID_USUARIO)
	--FOREIGN KEY (ID_ALBUM) REFERENCES ALBUM(ALBUM_ID)
)

SELECT * FROM fotografos

--CLIENTES
CREATE TABLE CLIENTES (
	ID_CLIENTE INT NOT NULL,
	FOREIGN KEY(ID_CLIENTE) REFERENCES USUARIOS(ID_USUARIO)
)

SELECT * FROM clientes

--ALBUM
CREATE TABLE ALBUM (
	ID_ALBUM SERIAL PRIMARY KEY,
	NOME VARCHAR(200) NOT NULL,
	TIPO_FOTOGRAFIA VARCHAR(200) NOT NULL, -- natureza, casamento, eventos, etc
	TIPO_PACOTE VARCHAR(100) CHECK(TIPO_PACOTE IN('OURO', 'PRATA', 'BRONZE')),
	CURTIDAS INT NOT NULL CHECK (CURTIDAS >= 0),
	FOTOGRAFO_ID INT NOT NULL,
	FOREIGN KEY(FOTOGRAFO_ID) REFERENCES USUARIOS(ID_USUARIO)
)
SELECT * FROM album

--FOTOS
CREATE TABLE FOTOS (
	ID_FOTO SERIAL PRIMARY KEY,
	URL VARCHAR(200) NOT NULL,
<<<<<<< HEAD
	DESCRICAO VARCHAR(200) NULL,
=======
>>>>>>> 4707cf2e9110270b277e32353cbeaa6aff7755c8
	ALBUM_ID INT NOT NULL,
	FOREIGN KEY(ALBUM_ID) REFERENCES ALBUM(ID_ALBUM)
)

<<<<<<< HEAD
SELECT * FROM FOTOS
=======
SELECT * FROM fotos
>>>>>>> 4707cf2e9110270b277e32353cbeaa6aff7755c8

--PAGAMENTOS
CREATE TABLE PAGAMENTOS (
	ID_PAGAMENTO SERIAL PRIMARY KEY,
<<<<<<< HEAD
	VALOR DECIMAL(10,2) CHECK(VALOR > 0) NOT NULL,
=======
	VALOR DECIMAL(10,2) NOT NULL,
>>>>>>> 4707cf2e9110270b277e32353cbeaa6aff7755c8
	TIPO_PAGAMENTO VARCHAR(100) CHECK (TIPO_PAGAMENTO IN('DINHEIRO', 'CARTAO', 'PIX')) NOT NULL,
	STATUS VARCHAR(100) CHECK(STATUS IN('PENDENTE', 'CANCELADO', 'VENCIDO')) NOT NULL,
	DATA_CRIACAO DATE NOT NULL,
	DATA_VENCIMENTO DATE NOT NULL,
	USUARIO_ID INT NOT NULL,
	FOTOGRAFO_ID INT NOT NULL,
	FOREIGN KEY(USUARIO_ID) REFERENCES USUARIOS(ID_USUARIO),
	FOREIGN KEY(FOTOGRAFO_ID) REFERENCES USUARIOS(ID_USUARIO)
)
<<<<<<< HEAD
=======

>>>>>>> 4707cf2e9110270b277e32353cbeaa6aff7755c8
SELECT * FROM pagamentos
