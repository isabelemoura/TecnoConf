CREATE DATABASE tecnoconf;
USE tecnoconf;

CREATE TABLE Cliente (
    cpfCliente VARCHAR(11) NOT NULL PRIMARY KEY,
    nomeCliente VARCHAR(30) NOT NULL,
    emailCliente VARCHAR(70) NOT NULL,
    celularCliente VARCHAR(20) NOT NULL,
	cep VARCHAR(9) NOT NULL,
    enderecoCliente VARCHAR(80) NOT NULL
);

CREATE TABLE Pedido (
    idPedido INT PRIMARY KEY AUTO_INCREMENT,
    nomeProduto VARCHAR(30) NOT NULL,
    valorPedido NUMERIC(11,2) NOT NULL,
    celularCliente VARCHAR(20) NOT NULL,
    dataPedido DATE NOT NULL,
    obsPedido VARCHAR(80),
    cpfCliente VARCHAR(11),
    FOREIGN KEY (cpfCliente) REFERENCES Cliente (cpfCliente) ON DELETE CASCADE
);

CREATE TABLE Andamento (
    idPedido INT PRIMARY KEY NOT NULL,
    andamentoPedido VARCHAR(20) NOT NULL,
    FOREIGN KEY (idPedido) REFERENCES Pedido (idPedido) ON DELETE CASCADE
);

CREATE TABLE Fornecedor (
    Cnpj VARCHAR(14) PRIMARY KEY NOT NULL,
    telefoneFornecedor VARCHAR(20) NOT NULL,
    nomeFornecedor VARCHAR(30) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    enderecoFornecedor VARCHAR(80) NOT NULL
);

CREATE TABLE Ingredientes (
    idProduto INT PRIMARY KEY AUTO_INCREMENT,
    nomeProduto VARCHAR(30) NOT NULL,
    Qtde INT NOT NULL,
    nomeFornecedor VARCHAR(30) NOT NULL,
    enderecoFornecedor VARCHAR(80) NOT NULL,
	unMedida VARCHAR(2) NOT NULL,
    Cnpj VARCHAR(14) NOT NULL,
    FOREIGN KEY (Cnpj) REFERENCES Fornecedor (Cnpj) ON DELETE CASCADE
);

-- Não ter repetições de CPFs 
ALTER TABLE Cliente 
    ADD CONSTRAINT uq_cpfCliente 
    UNIQUE (cpfCliente);

-- Adicionar chave estrangeira relacionando CPF na tabela Cliente com Pedido
ALTER TABLE Pedido
    ADD CONSTRAINT FK_Pedido_Cliente
    FOREIGN KEY (cpfCliente) REFERENCES Cliente (cpfCliente) ON DELETE CASCADE;
    
ALTER TABLE Fornecedor
    ADD CONSTRAINT FK_Ingredientes_Fornecedor
    FOREIGN KEY (Cnpj) REFERENCES Fornecedor (Cnpj) ON DELETE CASCADE;

--Altera o enderecoFornecedor para tipoProduto
alter table ingredientes CHANGE COLUMN enderecoFornecedor tipoProduto varchar(15);

--Deleta a coluna nomeProduto da tabela pedido
alter table pedido drop COLUMN nomeProduto;

--O idPedido não pode ser AUTO_INCREMENTO, O USUARIO DEVE INSERIR UM ID MANUAL
ALTER TABLE `pedido` CHANGE `idPedido` `idPedido` INT(11) NOT NULL;

-- Inserir dados na tabela Andamento baseado nos registros da tabela Pedido
INSERT INTO Andamento (idPedido, andamentoPedido)
    SELECT idPedido, 'Finalizado' FROM Pedido;
INSERT INTO Andamento (idPedido, andamentoPedido)
    SELECT idPedido, 'Em Processo' FROM Pedido;
INSERT INTO Andamento (idPedido, andamentoPedido)
    SELECT idPedido, 'Cancelado' FROM Pedido;
