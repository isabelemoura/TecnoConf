-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 12-Jun-2023 às 00:15
-- Versão do servidor: 10.4.28-MariaDB
-- versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `tecnoconf`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `andamento`
--

CREATE TABLE `andamento` (
  `idPedido` int(11) NOT NULL,
  `andamentoPedido` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `andamento`
--

INSERT INTO `andamento` (`idPedido`, `andamentoPedido`) VALUES
(12, 'Finalizado'),
(13, 'Finalizado'),
(17, 'Em processo'),
(27, 'Em processo');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `cpfCliente` varchar(11) NOT NULL,
  `nomeCliente` varchar(30) NOT NULL,
  `emailCliente` varchar(70) NOT NULL,
  `celularCliente` varchar(20) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `enderecoCliente` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`cpfCliente`, `nomeCliente`, `emailCliente`, `celularCliente`, `cep`, `enderecoCliente`) VALUES
('11111111111', 'Gabriel Eler', 'gabriel@outlook.com', '11939035620', '04472132', 'Rua anderlina'),
('22222222222', 'Isaque', 'isaque@gmail.com', '1193905618', '04472130', 'Rua teste'),
('33333333333', 'Gabriel Eler', 'gabriel@outlook.com', '11939035620', '04472132', 'Rua anderlina'),
('55555555555', 'Cavalo Gabriel', 'cavalo@gmail.com', '1188888888888', '04472130', 'rua teste ');

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE `fornecedor` (
  `Cnpj` varchar(14) NOT NULL,
  `telefoneFornecedor` varchar(20) NOT NULL,
  `nomeFornecedor` varchar(30) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `enderecoFornecedor` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`Cnpj`, `telefoneFornecedor`, `nomeFornecedor`, `cep`, `enderecoFornecedor`) VALUES
('11111111111111', '988521949', 'teste', '12312313', 'rua teste'),
('22222222222222', '981910298347', 'Gabriel', '04472130', 'Rua teste'),
('77777777777777', '627184924', 'CavaloDiCaprio', '04472130', 'Rua teste'),
('88888888888888', '11111111111', 'Celinha', '04472130', 'Rua teste');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ingredientes`
--

CREATE TABLE `ingredientes` (
  `idProduto` int(11) NOT NULL,
  `nomeProduto` varchar(30) NOT NULL,
  `Qtde` int(11) NOT NULL,
  `nomeFornecedor` varchar(30) NOT NULL,
  `tipoProduto` varchar(15) DEFAULT NULL,
  `unMedida` varchar(10) NOT NULL,
  `Cnpj` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `ingredientes`
--

INSERT INTO `ingredientes` (`idProduto`, `nomeProduto`, `Qtde`, `nomeFornecedor`, `tipoProduto`, `unMedida`, `Cnpj`) VALUES
(1, 'Morango', 100, 'Gabriel', 'PERECIVEL', 'CAIXA', '22222222222222'),
(2, 'Chocolate', 10, 'Gabriel', 'PERECIVEL', 'KG', '22222222222222'),
(3, 'abacaxi', 100, 'Gabriel', 'PERECIVEL', 'CAIXA', '22222222222222'),
(4, 'abacaxi', 150, 'Celinha', 'NÃO PERECIVEL', 'KG', '88888888888888'),
(5, 'produto teste', 0, 'teste', 'PERECIVEL', 'KG', '11111111111111'),
(8, 'café', 5, 'Gabriel', 'NÃO PERECIVEL', 'KG', '22222222222222'),
(10, 'teste', 111, 'Gabriel', 'NÃO PERECIVEL', 'ML', '22222222222222'),
(11, 'teste', 111, 'Gabriel', 'NÃO PERECIVEL', 'ML', '22222222222222'),
(12, 'Uva verde', 10, 'CavaloDiCaprio', 'PERECIVEL', 'CAIXA', '77777777777777'),
(13, 'Jabuticaba', 150, 'CavaloDiCaprio', 'PERECIVEL', 'CAIXA', '77777777777777'),
(14, 'Jujuba', 5, 'Celinha', 'PERECIVEL', 'CAIXA', '88888888888888'),
(98, 'Teste', 100, 'teste', 'PERECIVEL', 'KG', '11111111111111');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido`
--

CREATE TABLE `pedido` (
  `idPedido` int(11) NOT NULL,
  `valorPedido` decimal(11,2) NOT NULL,
  `celularCliente` varchar(20) NOT NULL,
  `dataPedido` varchar(10) NOT NULL,
  `obsPedido` varchar(80) DEFAULT NULL,
  `cpfCliente` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `pedido`
--

INSERT INTO `pedido` (`idPedido`, `valorPedido`, `celularCliente`, `dataPedido`, `obsPedido`, `cpfCliente`) VALUES
(11, 200.00, '988521950', '15-05-2023', 'TESTE', '11111111111'),
(12, 270.00, '988521950', '12-05-2023', 'OPA', '11111111111'),
(13, 270.00, '11939035620', '12-05-2023', 'OPA', '11111111111'),
(17, 200.00, '588521950', '15-05-2023', 'TESTE', '11111111111'),
(27, 100.00, '1193905618', '24-02-2023', 'teste', '22222222222');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `andamento`
--
ALTER TABLE `andamento`
  ADD PRIMARY KEY (`idPedido`);

--
-- Índices para tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cpfCliente`),
  ADD UNIQUE KEY `uq_cpfCliente` (`cpfCliente`);

--
-- Índices para tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD PRIMARY KEY (`Cnpj`);

--
-- Índices para tabela `ingredientes`
--
ALTER TABLE `ingredientes`
  ADD PRIMARY KEY (`idProduto`),
  ADD KEY `Cnpj` (`Cnpj`);

--
-- Índices para tabela `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`idPedido`),
  ADD KEY `FK_Pedido_Cliente` (`cpfCliente`);

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `andamento`
--
ALTER TABLE `andamento`
  ADD CONSTRAINT `andamento_ibfk_1` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`idPedido`) ON DELETE CASCADE;

--
-- Limitadores para a tabela `ingredientes`
--
ALTER TABLE `ingredientes`
  ADD CONSTRAINT `ingredientes_ibfk_1` FOREIGN KEY (`Cnpj`) REFERENCES `fornecedor` (`Cnpj`) ON DELETE CASCADE;

--
-- Limitadores para a tabela `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK_Pedido_Cliente` FOREIGN KEY (`cpfCliente`) REFERENCES `cliente` (`cpfCliente`) ON DELETE CASCADE,
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`cpfCliente`) REFERENCES `cliente` (`cpfCliente`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
