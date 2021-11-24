-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 24-Nov-2021 às 03:24
-- Versão do servidor: 10.4.21-MariaDB
-- versão do PHP: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `empresa`
--
CREATE DATABASE IF NOT EXISTS `empresa` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `empresa`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `cpf` varchar(32) NOT NULL,
  `job` varchar(32) NOT NULL,
  `salary` int(11) NOT NULL,
  `phone` varchar(32) NOT NULL,
  `created_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `employee`
--

INSERT INTO `employee` (`id`, `name`, `cpf`, `job`, `salary`, `phone`, `created_at`) VALUES
(1, 'Funcionario 1', '000.000.000-01', 'Dev', 1000, '(71) 99999-9999', '2021-11-23'),
(2, 'Funcionario 2', '000.000.000-02', 'Dev', 1000, '(71) 99999-9999', '2021-11-23'),
(3, 'Funcionario 3', '000.000.000-03', 'Dev', 1000, '(71) 99999-9999', '2021-11-23'),
(4, 'Funcionario 4', '000.000.000-04', 'Dev', 1000, '(71) 99999-9999', '2021-11-23'),
(5, 'Funcionario 5', '000.000.000-05', 'Dev', 1000, '(71) 99999-9999', '2021-11-23'),
(6, 'Funcionario 6', '000.000.000-06', 'Dev', 1000, '(71) 99999-9999', '2021-11-23'),
(7, 'Funcionario 7', '000.000.000-07', 'Dev', 1000, '(71) 99999-9999', '2021-11-23'),
(8, 'Funcionario 8', '000.000.000-08', 'Dev', 1000, '(71) 99999-9999', '2021-11-23'),
(9, 'Funcionario 9', '000.000.000-09', 'Dev', 1000, '(71) 99999-9999', '2021-11-23'),
(10, 'Funcionario 10', '000.000.000-10', 'Dev', 1000, '(71) 99999-9999', '2021-11-23');

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `isAdmin`) VALUES
(1, 'admin', '1234', 1);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
