-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mer. 24 oct. 2018 à 14:22
-- Version du serveur :  10.1.35-MariaDB
-- Version de PHP :  7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pidomen_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sous_categories` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `nom`, `sous_categories`) VALUES
(1, 'Categorie test', 'test1,test2,test3'),
(2, 'Categorie test2', 'test1,test2,test3'),
(3, 'categorie1', NULL),
(4, 'Catégorie2', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `mot_cle`
--

CREATE TABLE `mot_cle` (
  `ID` bigint(20) NOT NULL,
  `LIBELLE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `mot_cle`
--

INSERT INTO `mot_cle` (`ID`, `LIBELLE`) VALUES
(3, 'M1'),
(4, 'M2'),
(5, 'MC1'),
(6, 'MC2'),
(7, 'MC1'),
(8, 'MC36'),
(9, 'Ma beautÃ©'),
(10, 'Ma vache'),
(11, 'Ma beaute'),
(12, 'Ma vache'),
(13, 'UN M');

-- --------------------------------------------------------

--
-- Structure de la table `page`
--

CREATE TABLE `page` (
  `id` bigint(20) NOT NULL,
  `contenu` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_creation` datetime DEFAULT NULL,
  `titre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_utilisateur` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `page`
--

INSERT INTO `page` (`id`, `contenu`, `date_creation`, `titre`, `id_utilisateur`) VALUES
(201, 'Tesr', '2018-10-23 19:05:37', 'Une page', 1),
(251, 'PPP', '2018-10-23 20:06:26', 'BOnjour', 1),
(252, '', '2018-10-23 20:08:12', '', 1),
(253, '', '2018-10-23 20:09:24', 'BIDON', 1);

-- --------------------------------------------------------

--
-- Structure de la table `page_has_categorie`
--

CREATE TABLE `page_has_categorie` (
  `id` bigint(20) NOT NULL,
  `id_categorie` bigint(20) DEFAULT NULL,
  `id_page` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `page_has_categorie`
--

INSERT INTO `page_has_categorie` (`id`, `id_categorie`, `id_page`) VALUES
(12, 1, 201),
(13, 4, 201);

-- --------------------------------------------------------

--
-- Structure de la table `page_has_mot_cle`
--

CREATE TABLE `page_has_mot_cle` (
  `ID` bigint(20) NOT NULL,
  `id_mot_cle` bigint(20) DEFAULT NULL,
  `id_page` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `page_has_mot_cle`
--

INSERT INTO `page_has_mot_cle` (`ID`, `id_mot_cle`, `id_page`) VALUES
(11, 11, 201),
(12, 12, 201),
(13, 13, 253);

-- --------------------------------------------------------

--
-- Structure de la table `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '300');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mot_de_passe` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `email`, `login`, `mot_de_passe`, `nom`, `prenom`) VALUES
(1, 'arnaudbakyono@gmail.com', 'kman', 'julie', 'BAKYONO', 'Auguste'),
(2, 'thiombianojulie@gmail.com', 'julie', 'julie', 'Thiombiano', 'Julie');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_msfyn77el0dc521wbqdnwc4s1` (`nom`);

--
-- Index pour la table `mot_cle`
--
ALTER TABLE `mot_cle`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `page`
--
ALTER TABLE `page`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_qnh8oht94nv052o4tw61cts9x` (`id_utilisateur`);

--
-- Index pour la table `page_has_categorie`
--
ALTER TABLE `page_has_categorie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_f2l8q32hey3h3nm7u25rmvyq1` (`id_categorie`),
  ADD KEY `FK_dcayy5n18poy74y02evpj1ly4` (`id_page`);

--
-- Index pour la table `page_has_mot_cle`
--
ALTER TABLE `page_has_mot_cle`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_page_has_mot_cle_id_page` (`id_page`),
  ADD KEY `FK_page_has_mot_cle_id_mot_cle` (`id_mot_cle`);

--
-- Index pour la table `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_35ysk0sh9ruwixrld3nc0weut` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `mot_cle`
--
ALTER TABLE `mot_cle`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `page`
--
ALTER TABLE `page`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=254;

--
-- AUTO_INCREMENT pour la table `page_has_categorie`
--
ALTER TABLE `page_has_categorie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `page_has_mot_cle`
--
ALTER TABLE `page_has_mot_cle`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `page`
--
ALTER TABLE `page`
  ADD CONSTRAINT `FK_qnh8oht94nv052o4tw61cts9x` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `page_has_categorie`
--
ALTER TABLE `page_has_categorie`
  ADD CONSTRAINT `FK_dcayy5n18poy74y02evpj1ly4` FOREIGN KEY (`id_page`) REFERENCES `page` (`id`),
  ADD CONSTRAINT `FK_f2l8q32hey3h3nm7u25rmvyq1` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id`);

--
-- Contraintes pour la table `page_has_mot_cle`
--
ALTER TABLE `page_has_mot_cle`
  ADD CONSTRAINT `FK_page_has_mot_cle_id_mot_cle` FOREIGN KEY (`id_mot_cle`) REFERENCES `mot_cle` (`ID`),
  ADD CONSTRAINT `FK_page_has_mot_cle_id_page` FOREIGN KEY (`id_page`) REFERENCES `page` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
