-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Creato il: Ott 06, 2025 alle 07:51
-- Versione del server: 8.0.40
-- Versione PHP: 8.3.14

-- Svuota e ricrea il database prima del seed
DROP DATABASE IF EXISTS `gestione_permessi`;
CREATE DATABASE `gestione_permessi` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `gestione_permessi`;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestione_permessi`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `gruppi`
--

CREATE TABLE `gruppi` (
  `id` bigint NOT NULL,
  `alias` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dump dei dati per la tabella `gruppi`
--

INSERT INTO `gruppi` (`id`, `alias`, `created_at`, `nome`, `updated_at`) VALUES
(1, 'UTENTI', '2025-09-29 13:27:07.000000', 'Gestione Utenti', '2025-09-29 13:27:07.000000'),
(2, 'RUOLI', '2025-09-29 13:27:07.000000', 'Gestione Ruoli', '2025-09-29 13:27:07.000000'),
(3, 'PERMESSI', '2025-09-29 13:27:07.000000', 'Gestione Permessi', '2025-09-29 13:27:07.000000'),
(4, 'GRUPPI', '2025-09-29 13:27:07.000000', 'Gestione Gruppi', '2025-09-29 13:27:07.000000');

-- Nuovi gruppi: CANI e RAZZE
INSERT INTO `gruppi` (`id`, `alias`, `created_at`, `nome`, `updated_at`) VALUES
(5, 'CANI', '2025-09-29 13:27:08.000000', 'Gestione Cani', '2025-09-29 13:27:08.000000'),
(6, 'RAZZE', '2025-09-29 13:27:08.000000', 'Gestione Razze', '2025-09-29 13:27:08.000000');

-- --------------------------------------------------------

--
-- Struttura della tabella `permessi`
--

CREATE TABLE `permessi` (
  `id` bigint NOT NULL,
  `alias` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `gruppo_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dump dei dati per la tabella `permessi`
--

INSERT INTO `permessi` (`id`, `alias`, `created_at`, `nome`, `updated_at`, `gruppo_id`) VALUES
(1, 'UTENTE_READ', '2025-09-29 13:27:07.000000', 'Lettura Utenti', '2025-09-29 13:27:07.000000', 1),
(2, 'UTENTE_CREATE', '2025-09-29 13:27:07.000000', 'Creazione Utenti', '2025-09-29 13:27:07.000000', 1),
(3, 'UTENTE_UPDATE', '2025-09-29 13:27:07.000000', 'Aggiornamento Utenti', '2025-09-29 13:27:07.000000', 1),
(4, 'UTENTE_DELETE', '2025-09-29 13:27:07.000000', 'Eliminazione Utenti', '2025-09-29 13:27:07.000000', 1),
(5, 'RUOLO_READ', '2025-09-29 13:27:07.000000', 'Lettura Ruoli', '2025-09-29 13:27:07.000000', 2),
(6, 'RUOLO_CREATE', '2025-09-29 13:27:07.000000', 'Creazione Ruoli', '2025-09-29 13:27:07.000000', 2),
(7, 'RUOLO_UPDATE', '2025-09-29 13:27:07.000000', 'Aggiornamento Ruoli', '2025-09-29 13:27:07.000000', 2),
(8, 'RUOLO_DELETE', '2025-09-29 13:27:07.000000', 'Eliminazione Ruoli', '2025-09-29 13:27:07.000000', 2),
(9, 'PERMESSO_READ', '2025-09-29 13:27:07.000000', 'Lettura Permessi', '2025-09-29 13:27:07.000000', 3),
(10, 'PERMESSO_CREATE', '2025-09-29 13:27:08.000000', 'Creazione Permessi', '2025-09-29 13:27:08.000000', 3),
(11, 'PERMESSO_UPDATE', '2025-09-29 13:27:08.000000', 'Aggiornamento Permessi', '2025-09-29 13:27:08.000000', 3),
(12, 'PERMESSO_DELETE', '2025-09-29 13:27:08.000000', 'Eliminazione Permessi', '2025-09-29 13:27:08.000000', 3),
(13, 'GRUPPO_READ', '2025-09-29 13:27:08.000000', 'Lettura Gruppi', '2025-09-29 13:27:08.000000', 4),
(14, 'GRUPPO_CREATE', '2025-09-29 13:27:08.000000', 'Creazione Gruppi', '2025-09-29 13:27:08.000000', 4),
(15, 'GRUPPO_UPDATE', '2025-09-29 13:27:08.000000', 'Aggiornamento Gruppi', '2025-09-29 13:27:08.000000', 4),
(16, 'GRUPPO_DELETE', '2025-09-29 13:27:08.000000', 'Eliminazione Gruppi', '2025-09-29 13:27:08.000000', 4);

-- Nuovi permessi per CANI
INSERT INTO `permessi` (`id`, `alias`, `created_at`, `nome`, `updated_at`, `gruppo_id`) VALUES
(17, 'CANE_READ', '2025-09-29 13:27:08.000000', 'Lettura Cani', '2025-09-29 13:27:08.000000', 5),
(18, 'CANE_CREATE', '2025-09-29 13:27:08.000000', 'Creazione Cani', '2025-09-29 13:27:08.000000', 5),
(19, 'CANE_UPDATE', '2025-09-29 13:27:08.000000', 'Aggiornamento Cani', '2025-09-29 13:27:08.000000', 5),
(20, 'CANE_DELETE', '2025-09-29 13:27:08.000000', 'Eliminazione Cani', '2025-09-29 13:27:08.000000', 5),
(21, 'CANE_READ_MIEI', '2025-09-29 13:27:08.000000', 'Lettura Miei Cani', '2025-09-29 13:27:08.000000', 5);

-- Nuovi permessi per RAZZE
INSERT INTO `permessi` (`id`, `alias`, `created_at`, `nome`, `updated_at`, `gruppo_id`) VALUES
(22, 'RAZZA_READ', '2025-09-29 13:27:08.000000', 'Lettura Razze', '2025-09-29 13:27:08.000000', 6),
(23, 'RAZZA_CREATE', '2025-09-29 13:27:08.000000', 'Creazione Razze', '2025-09-29 13:27:08.000000', 6),
(24, 'RAZZA_UPDATE', '2025-09-29 13:27:08.000000', 'Aggiornamento Razze', '2025-09-29 13:27:08.000000', 6),
(25, 'RAZZA_DELETE', '2025-09-29 13:27:08.000000', 'Eliminazione Razze', '2025-09-29 13:27:08.000000', 6);

-- Nuovo permesso profilo utente
INSERT INTO `permessi` (`id`, `alias`, `created_at`, `nome`, `updated_at`, `gruppo_id`) VALUES
(26, 'UTENTE_PROFILE', '2025-09-29 13:27:08.000000', 'Profilo Utente', '2025-09-29 13:27:08.000000', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `ruoli`
--

CREATE TABLE `ruoli` (
  `id` bigint NOT NULL,
  `alias` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dump dei dati per la tabella `ruoli`
--

INSERT INTO `ruoli` (`id`, `alias`, `created_at`, `nome`, `updated_at`) VALUES
(1, 'ADMIN', '2025-09-29 13:27:08.000000', 'Amministratore', '2025-09-29 13:27:08.000000');

-- Ruolo USER
INSERT INTO `ruoli` (`id`, `alias`, `created_at`, `nome`, `updated_at`) VALUES
(2, 'USER', '2025-09-29 13:27:08.000000', 'Utente', '2025-09-29 13:27:08.000000');

-- --------------------------------------------------------

--
-- Struttura della tabella `ruoli_permessi`
--

CREATE TABLE `ruoli_permessi` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `permesso_id` bigint NOT NULL,
  `ruolo_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dump dei dati per la tabella `ruoli_permessi`
--

INSERT INTO `ruoli_permessi` (`id`, `created_at`, `updated_at`, `permesso_id`, `ruolo_id`) VALUES
(1, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 2, 1),
(2, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 4, 1),
(3, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 1, 1),
(4, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 3, 1),
(8, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 6, 1),
(9, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 8, 1),
(10, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 5, 1),
(11, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 7, 1),
(15, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 10, 1),
(16, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 12, 1),
(17, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 9, 1),
(18, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 11, 1),
(22, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 14, 1),
(23, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 16, 1),
(24, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 13, 1),
(25, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 15, 1);

-- Associa nuovi permessi CANE/RAZZA al ruolo ADMIN
INSERT INTO `ruoli_permessi` (`id`, `created_at`, `updated_at`, `permesso_id`, `ruolo_id`) VALUES
(26, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 17, 1),
(27, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 18, 1),
(28, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 19, 1),
(29, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 20, 1),
(30, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 21, 1),
(31, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 22, 1),
(32, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 23, 1),
(33, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 24, 1),
(34, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 25, 1);

-- Associa permesso di lettura dei propri cani al ruolo USER
INSERT INTO `ruoli_permessi` (`id`, `created_at`, `updated_at`, `permesso_id`, `ruolo_id`) VALUES
(35, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 21, 2);

-- Associa UTENTE_PROFILE ad ADMIN e USER
INSERT INTO `ruoli_permessi` (`id`, `created_at`, `updated_at`, `permesso_id`, `ruolo_id`) VALUES
(36, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 26, 1),
(37, '2025-09-29 13:27:08.000000', '2025-09-29 13:27:08.000000', 26, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` bigint NOT NULL,
  `codice_fiscale` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `data_nascita` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `ruolo_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `codice_fiscale`, `cognome`, `created_at`, `data_nascita`, `email`, `indirizzo`, `nome`, `password`, `telefono`, `updated_at`, `ruolo_id`) VALUES
(1, 'ADMINCF00A00H000X', 'System', '2025-09-29 13:27:08.000000', NULL, 'admin@example.com', 'Via di Test, 1', 'Admin', '$2a$12$Xr5VrqK5eFOIQYgSH62y9O0xY8braMgtUn/omyREWuY.hZfkSAo6C', '0000000000', '2025-09-29 13:27:08.000000', 1);

-- Nuovo utente con ruolo USER
INSERT INTO `utenti` (`id`, `codice_fiscale`, `cognome`, `created_at`, `data_nascita`, `email`, `indirizzo`, `nome`, `password`, `telefono`, `updated_at`, `ruolo_id`) VALUES
(2, 'USERCF00A00H000Y', 'Rossi', '2025-09-29 13:27:08.000000', NULL, 'user@example.com', 'Via di Test, 2', 'User', '$2a$12$Xr5VrqK5eFOIQYgSH62y9O0xY8braMgtUn/omyREWuY.hZfkSAo6C', '0000000001', '2025-09-29 13:27:08.000000', 2);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `gruppi`
--
ALTER TABLE `gruppi`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKkx0mh1isnnufaiwb2qyugyx5t` (`alias`);

--
-- Indici per le tabelle `permessi`
--
ALTER TABLE `permessi`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKmxea2rkqxds31e170gbuiwcb5` (`alias`),
  ADD KEY `FKooykdo5cqwxm6ef4lhe6w1vwc` (`gruppo_id`);

--
-- Indici per le tabelle `ruoli`
--
ALTER TABLE `ruoli`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKq0f8vh2mtwuxm6er1f1j424o7` (`alias`);

--
-- Indici per le tabelle `ruoli_permessi`
--
ALTER TABLE `ruoli_permessi`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uk_ruolo_permesso` (`ruolo_id`,`permesso_id`),
  ADD KEY `FKtfum3dvufoo91nlqrxg00gjbc` (`permesso_id`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKk66k0jmwobyicvdmhhymu4w2u` (`codice_fiscale`),
  ADD UNIQUE KEY `UK9b90mk1nolf3ou90p42a93tjo` (`email`),
  ADD KEY `FKc4u1vnlg84t8p82md71kn0rx1` (`ruolo_id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `gruppi`
--
ALTER TABLE `gruppi`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `permessi`
--
ALTER TABLE `permessi`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT per la tabella `ruoli`
--
ALTER TABLE `ruoli`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `ruoli_permessi`
--
ALTER TABLE `ruoli_permessi`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `permessi`
--
ALTER TABLE `permessi`
  ADD CONSTRAINT `FKooykdo5cqwxm6ef4lhe6w1vwc` FOREIGN KEY (`gruppo_id`) REFERENCES `gruppi` (`id`);

--
-- Limiti per la tabella `ruoli_permessi`
--
ALTER TABLE `ruoli_permessi`
  ADD CONSTRAINT `FK857i15bogkr2k20a4j46i011d` FOREIGN KEY (`ruolo_id`) REFERENCES `ruoli` (`id`),
  ADD CONSTRAINT `FKtfum3dvufoo91nlqrxg00gjbc` FOREIGN KEY (`permesso_id`) REFERENCES `permessi` (`id`);

--
-- Limiti per la tabella `utenti`
--
ALTER TABLE `utenti`
  ADD CONSTRAINT `FKc4u1vnlg84t8p82md71kn0rx1` FOREIGN KEY (`ruolo_id`) REFERENCES `ruoli` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
