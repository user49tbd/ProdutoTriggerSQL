CREATE DATABASE PRODUTOMVC
GO
USE PRODUTOMVC
GO
CREATE TABLE PRODUTO(
CODIGO			INT				NOT NULL,
NOME			VARCHAR(30)		NOT NULL,
VALOR_UNITARIO	DECIMAL(7,2)	NOT NULL,
QTD_ESTOQUE		INT				NOT NULL,
PRIMARY KEY (CODIGO)
)
GO
--BUSCAR
SELECT CODIGO, NOME, VALOR_UNITARIO, QTD_ESTOQUE  FROM PRODUTO WHERE PRODUTO.CODIGO = 1
GO
--LISTAR
SELECT CODIGO, NOME, VALOR_UNITARIO, QTD_ESTOQUE  FROM PRODUTO
GO
CREATE PROCEDURE P (@OP VARCHAR(01), @CODP INT, @NOME VARCHAR(30), @VALUNI DECIMAL(7,2),@QTD INT)
AS
BEGIN
	DECLARE @CK VARCHAR(30)
	SET @CK = NULL
	SET @CK = (SELECT P.CODIGO FROM PRODUTO P WHERE P.CODIGO = @CODP)
	BEGIN TRY
	IF(@OP LIKE 'I')
	BEGIN
		INSERT INTO PRODUTO VALUES (@CODP,@NOME, @VALUNI,@QTD)
	END
	ELSE
	BEGIN
	IF(@CK NOT LIKE '')
	BEGIN
			IF(@OP LIKE 'U')
			BEGIN
				UPDATE PRODUTO
				SET NOME = @NOME, VALOR_UNITARIO = @VALUNI,  QTD_ESTOQUE = @QTD
				WHERE PRODUTO.CODIGO = @CODP
			END
			IF(@OP LIKE 'D')
			BEGIN
				DELETE FROM PRODUTO WHERE PRODUTO.CODIGO LIKE @CODP
			END
	END
	END
	END TRY
	BEGIN CATCH
	END CATCH
END
GO
CREATE PROCEDURE P_SCL (@VAL INT, @RES INT OUT)
AS 
BEGIN
SET @RES = (SELECT COUNT(P.CODIGO) FROM PRODUTO P WHERE P.QTD_ESTOQUE < @VAL)
END
GO
CREATE FUNCTION FC (@VAL INT)
RETURNS @TAB TABLE(
CODIGO	INT			NOT NULL,
NOME	VARCHAR(30)	NOT NULL,
QTD		INT			NOT NULL
)
AS 
BEGIN
	INSERT INTO @TAB SELECT P.CODIGO, P.NOME, P.QTD_ESTOQUE FROM PRODUTO P WHERE P.QTD_ESTOQUE < @VAL
	RETURN
END
GO
CREATE TRIGGER TG ON PRODUTO
FOR DELETE
AS 
BEGIN
	DECLARE @VL INT
	SET @VL = (SELECT QTD_ESTOQUE FROM deleted)
	IF(@VL NOT LIKE 0)
	BEGIN
		ROLLBACK TRANSACTION
		RAISERROR('NAO E POSSIVEL EXCLUIR',16,1)
	END
END
---------------------------------------------------------------------------------------------------------------------------
