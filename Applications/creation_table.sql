-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Dim 12 Janvier 2014 à 15:15
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;

-- --------------------------------------------------------

--
-- Structure de la table `journal`
--

CREATE TABLE IF NOT EXISTS `journal` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `id_journal` int(11) NOT NULL,
  `nom_journal` varchar(100) NOT NULL,
  `Titre_article` varchar(255) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Description` text,
  `Lien` varchar(500) NOT NULL,
  `Auteur` varchar(100) DEFAULT NULL,
  `Item` varchar(100) DEFAULT NULL,
  `Article` text,
  `NP` varchar(8000) DEFAULT NULL,
  `HotTopic` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `likes`
--

CREATE TABLE IF NOT EXISTS `likes` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `id_journal` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `listejournaux`
--

CREATE TABLE IF NOT EXISTS `listejournaux` (
  `Id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `listejournaux`
--

INSERT INTO `listejournaux` (`Id`, `nom`) VALUES
(1, '20 Minutes'),
(2, 'Equipe'),
(3, 'Humanité'),
(4, 'Figaro'),
(5, 'le Monde'),
(6, 'les Echos'),
(7, 'Libération'),
(8, 'le New-York Times'),
(9, 'Rue 89');

-- --------------------------------------------------------

--
-- Structure de la table `prov`
--

CREATE TABLE IF NOT EXISTS `prov` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `id_journal` varchar(100) NOT NULL,
  `nom_journal` varchar(100) NOT NULL,
  `Titre_article` varchar(255) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Description` text,
  `Lien` varchar(500) NOT NULL,
  `Auteur` varchar(100) DEFAULT NULL,
  `Item` varchar(100) DEFAULT NULL,
  `Article` text,
  `NP` varchar(8000) DEFAULT NULL,
  `HotTopic` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `abonnement` varchar(10) DEFAULT NULL,
  `mots_likes` text NOT NULL,
  `freq_mots_likes` text NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`ID`, `login`, `password`, `abonnement`, `mots_likes`, `freq_mots_likes`) VALUES
(1, 'Bob', 'admin', NULL, '', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
