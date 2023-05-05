-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbprogetto
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articoli`
--

DROP TABLE IF EXISTS `articoli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articoli` (
  `id` int NOT NULL,
  `idCreatore` int NOT NULL,
  `nome` varchar(50) NOT NULL,
  `descrizione` varchar(255) DEFAULT NULL,
  `immagine` varchar(255) NOT NULL,
  `idAsta` int DEFAULT NULL,
  `prezzo` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articoli`
--

LOCK TABLES `articoli` WRITE;
/*!40000 ALTER TABLE `articoli` DISABLE KEYS */;
INSERT INTO `articoli` VALUES (10199,40789,'telecomando','Super fighissimamente figo','telecomando',NULL,42),(10567,79565,'radiotelegrafo','Super fighissimamente figo','radiotelegrafo',63636,15),(11406,35531,'mestolo','Super fighissimamente figo','mestolo',NULL,47),(11757,11813,'astrolabio','Super fighissimamente figo','astrolabio',NULL,12),(11764,61822,'peluche','Super fighissimamente figo','peluche',25597,15),(12263,74608,'scrittoio','Super fighissimamente figo','scrittoio',34045,20),(12413,74608,'semaforo','Super fighissimamente figo','semaforo',NULL,48),(12532,74608,'Cose con la Q','Super fighissimamente figo','Cose con la Q',26805,48),(12562,40789,'calzascarpe','Super fighissimamente figo','calzascarpe',32203,43),(12823,99257,'tapparella','Super fighissimamente figo','tapparella',85746,14),(13400,46310,'gomitolo','Super fighissimamente figo','gomitolo',86113,32),(13840,50718,'corazza','Super fighissimamente figo','corazza',34869,27),(14500,84729,'spoletta','Super fighissimamente figo','spoletta',NULL,34),(15167,98527,'beccuccio','Super fighissimamente figo','beccuccio',NULL,28),(16197,79565,'cristallizzatore','Super fighissimamente figo','cristallizzatore',14299,20),(16327,40789,'scialuppa','Super fighissimamente figo','scialuppa',89806,36),(16607,50718,'borotalco','Super fighissimamente figo','borotalco',22155,42),(17108,11813,'vassoio','Super fighissimamente figo','vassoio',NULL,44),(17865,40789,'staffa','Super fighissimamente figo','staffa',27469,32),(18023,79565,'bermuda','Super fighissimamente figo','bermuda',NULL,10),(18224,74608,'finestra','Super fighissimamente figo','finestra',NULL,19),(18514,32202,'ribaltina','Super fighissimamente figo','ribaltina',69793,50),(18621,50718,'gazebo','Super fighissimamente figo','gazebo',34869,12),(19986,99257,'frusta','Super fighissimamente figo','frusta',57248,30),(20256,93185,'vangatrice','Super fighissimamente figo','vangatrice',66327,11),(21036,48207,'goletta','Super fighissimamente figo','goletta',71044,38),(21173,61822,'bancomat','Super fighissimamente figo','bancomat',NULL,33),(21213,51314,'unguento','Super fighissimamente figo','unguento',NULL,44),(21958,84729,'baule','Super fighissimamente figo','baule',NULL,27),(22050,50718,'locomotrice','Super fighissimamente figo','locomotrice',22155,29),(22201,84729,'terrario','Super fighissimamente figo','terrario',NULL,36),(23018,79565,'sofa','Super fighissimamente figo','sofa',35060,24),(23225,99257,'spettrometro','Super fighissimamente figo','spettrometro',28388,38),(23661,46310,'asciugamani','Super fighissimamente figo','asciugamani',18800,33),(24563,99257,'bici','Super fighissimamente figo','bici',NULL,26),(24846,74608,'gonfalone','Super fighissimamente figo','gonfalone',34045,13),(24904,32202,'vaporiera','Super fighissimamente figo','vaporiera',94057,43),(25192,98527,'croce','Super fighissimamente figo','croce',51322,49),(25333,99257,'resistore','Super fighissimamente figo','resistore',51703,26),(25658,93185,'colla','Super fighissimamente figo','colla',NULL,44),(26326,93185,'girabacchino','Super fighissimamente figo','girabacchino',NULL,43),(26535,74608,'fustellatrice','Super fighissimamente figo','fustellatrice',NULL,30),(27103,35531,'mastice','Super fighissimamente figo','mastice',43321,43),(27406,84729,'bussolotto','Super fighissimamente figo','bussolotto',23887,34),(27435,74608,'granata','Super fighissimamente figo','granata',NULL,38),(27824,74608,'cronografo','Super fighissimamente figo','cronografo',34045,41),(28253,32202,'bicchiere','Super fighissimamente figo','bicchiere',50359,14),(31566,40789,'autorespiratore','Super fighissimamente figo','autorespiratore',NULL,44),(31813,93185,'camper','Super fighissimamente figo','camper',NULL,13),(32013,32202,'vetro','Super fighissimamente figo','vetro',50359,39),(32150,98527,'pantofola','Super fighissimamente figo','pantofola',NULL,26),(32218,74608,'maracas','Super fighissimamente figo','maracas',NULL,39),(32367,35531,'elettrocardiografo','Super fighissimamente figo','elettrocardiografo',16903,50),(32495,61822,'ringhiera','Super fighissimamente figo','ringhiera',NULL,49),(32544,99257,'spinterogeno','Super fighissimamente figo','spinterogeno',NULL,29),(32711,50718,'frizione','Super fighissimamente figo','frizione',22155,17),(33319,11813,'distributore','Super fighissimamente figo','distributore',46199,21),(33550,93185,'spinterometro','Super fighissimamente figo','spinterometro',89912,11),(33591,84729,'cera','Super fighissimamente figo','cera',NULL,26),(33677,46310,'inserto','Super fighissimamente figo','inserto',NULL,12),(34024,61822,'ghigliottina','Super fighissimamente figo','ghigliottina',NULL,11),(34208,50718,'catena','Super fighissimamente figo','catena',22155,15),(34356,11813,'planimetro','Super fighissimamente figo','planimetro',NULL,47),(35574,46310,'solenoide','Super fighissimamente figo','solenoide',NULL,17),(35697,99257,'caffettiera','Super fighissimamente figo','caffettiera',85746,40),(37908,48207,'golfino','Super fighissimamente figo','golfino',70906,31),(38063,79565,'capitello','Super fighissimamente figo','capitello',10766,17),(38384,74608,'ruotino','Super fighissimamente figo','ruotino',NULL,24),(38451,74608,'talismano','Super fighissimamente figo','talismano',NULL,49),(38856,32202,'armadio','Super fighissimamente figo','armadio',25047,13),(38900,99257,'baldacchino','Super fighissimamente figo','baldacchino',NULL,10),(39405,46310,'bilancia','Super fighissimamente figo','bilancia',18800,16),(39902,93185,'piegatrice','Super fighissimamente figo','piegatrice',NULL,32),(41704,46310,'conchiglia','Super fighissimamente figo','conchiglia',78089,18),(42106,48207,'ultraleggero','Super fighissimamente figo','ultraleggero',NULL,18),(42284,32202,'lingottiera','Super fighissimamente figo','lingottiera',69793,13),(42491,99257,'portafrutta','Super fighissimamente figo','portafrutta',85746,35),(42584,35531,'portacenere','Super fighissimamente figo','portacenere',88693,44),(43029,79565,'graffatrice','Super fighissimamente figo','graffatrice',35060,30),(43283,84729,'idrovolante','Super fighissimamente figo','idrovolante',23887,17),(44095,51314,'sellino','Super fighissimamente figo','sellino',NULL,43),(44291,46310,'giunto','Super fighissimamente figo','giunto',NULL,22),(44553,46310,'satellitare','Super fighissimamente figo','satellitare',86113,32),(45938,61822,'punto','Super fighissimamente figo','punto',25597,39),(46513,40789,'puff','Super fighissimamente figo','puff',32203,18),(46596,40789,'giocattolo','Super fighissimamente figo','giocattolo',45935,30),(46761,40789,'abaco','Super fighissimamente figo','abaco',89806,44),(46934,50718,'formaggiera','Super fighissimamente figo','formaggiera',22155,15),(47019,32202,'moschetto','Super fighissimamente figo','moschetto',50359,49),(47038,84729,'reoscopio','Super fighissimamente figo','reoscopio',NULL,31),(47168,35531,'contagiri','Super fighissimamente figo','contagiri',NULL,12),(47293,40789,'tavolozza','Super fighissimamente figo','tavolozza',NULL,38),(47757,32202,'archivio','Super fighissimamente figo','archivio',94057,23),(48041,32202,'cembalo','Super fighissimamente figo','cembalo',70862,40),(48150,40789,'escavatrice','Super fighissimamente figo','escavatrice',89806,27),(48184,48207,'secchiellone','Super fighissimamente figo','secchiellone',70906,14),(48687,99257,'frantumatore','Super fighissimamente figo','frantumatore',61373,25),(49478,11813,'pedalo','Super fighissimamente figo','pedalo',NULL,36),(49965,98527,'tutore','Super fighissimamente figo','tutore',NULL,19),(50740,35531,'camice','Super fighissimamente figo','camice',67715,13),(52261,32202,'portamatite','Super fighissimamente figo','portamatite',35284,10),(53630,51314,'carburatore','Super fighissimamente figo','carburatore',NULL,35),(54020,74608,'pastrano','Super fighissimamente figo','pastrano',NULL,38),(54626,48207,'carta','Super fighissimamente figo','carta',70906,16),(54918,99257,'ceppo','Super fighissimamente figo','ceppo',28388,49),(55613,79565,'robot','Super fighissimamente figo','robot',NULL,40),(55646,99257,'acquerello','Super fighissimamente figo','acquerello',NULL,14),(56247,35531,'levapunti','Super fighissimamente figo','levapunti',68871,38),(56510,93185,'fioriera','Super fighissimamente figo','fioriera',39799,30),(57315,99257,'tromba','Super fighissimamente figo','tromba',NULL,33),(58599,51314,'levachiodi','Super fighissimamente figo','levachiodi',93695,26),(58921,35531,'catarifrangente','Super fighissimamente figo','catarifrangente',16903,14),(59756,79565,'tubolare','Super fighissimamente figo','tubolare',35060,35),(59836,32202,'gemelli','Super fighissimamente figo','gemelli',70862,10),(60318,84729,'zufolo','Super fighissimamente figo','zufolo',NULL,23),(60903,61822,'modem','Super fighissimamente figo','modem',NULL,49),(61513,84729,'forcone','Super fighissimamente figo','forcone',NULL,47),(61732,93185,'trivella','Super fighissimamente figo','trivella',81833,30),(61837,61822,'termostato','Super fighissimamente figo','termostato',NULL,23),(62163,35531,'microprocessore','Super fighissimamente figo','microprocessore',43321,26),(63192,51314,'parete','Super fighissimamente figo','parete',NULL,49),(63264,99257,'adesivo','Super fighissimamente figo','adesivo',61373,16),(63540,51314,'scimitarra','Super fighissimamente figo','scimitarra',NULL,33),(63614,35531,'paniere','Super fighissimamente figo','paniere',68871,48),(63814,99257,'atlante','Super fighissimamente figo','atlante',85746,29),(64547,50718,'carrozza','Super fighissimamente figo','carrozza',NULL,50),(64634,99257,'vogatore','Super fighissimamente figo','vogatore',NULL,43),(65809,93185,'lattiera','Super fighissimamente figo','lattiera',81833,36),(65823,11813,'vaschetta','Super fighissimamente figo','vaschetta',17333,28),(66299,84729,'bara','Super fighissimamente figo','bara',NULL,15),(66652,35531,'gigantografia','Super fighissimamente figo','gigantografia',NULL,42),(66900,79565,'nivometro','Super fighissimamente figo','nivometro',14299,44),(66969,40789,'canoa','Super fighissimamente figo','canoa',32203,22),(67471,93185,'boa','Super fighissimamente figo','boa',NULL,23),(67548,99257,'treppiede','Super fighissimamente figo','treppiede',51703,26),(68169,32202,'museruola','Super fighissimamente figo','museruola',70862,46),(68211,32202,'idrante','Super fighissimamente figo','idrante',94057,18),(68685,40789,'contrabbasso','Super fighissimamente figo','contrabbasso',NULL,16),(69685,51314,'lamina','Super fighissimamente figo','lamina',NULL,45),(70210,61822,'dinamometro','Super fighissimamente figo','dinamometro',25597,41),(70305,99257,'obolo','Super fighissimamente figo','obolo',NULL,10),(72349,48207,'lacca','Super fighissimamente figo','lacca',NULL,44),(73356,79565,'ottavino','Super fighissimamente figo','ottavino',63636,25),(74906,40789,'mollettone','Super fighissimamente figo','mollettone',89806,15),(75183,98527,'ossigenatore','Super fighissimamente figo','ossigenatore',NULL,23),(75495,48207,'condizionatore','Super fighissimamente figo','condizionatore',71044,25),(75499,99257,'valvola','Super fighissimamente figo','valvola',NULL,39),(76008,93185,'bici','Super fighissimamente figo','bici',81833,20),(76382,99257,'semaforo','Super fighissimamente figo','semaforo',NULL,50),(76516,99257,'trappola','Super fighissimamente figo','trappola',57248,40),(77627,32202,'giroscopio','Super fighissimamente figo','giroscopio',25047,10),(77935,51314,'boomerang','Super fighissimamente figo','boomerang',67970,37),(78722,35531,'zamberlucco','Super fighissimamente figo','zamberlucco',67715,21),(79168,93185,'diffusore','Super fighissimamente figo','diffusore',81833,30),(79339,50718,'moneta','Super fighissimamente figo','moneta',55724,24),(79508,50718,'lamiera','Super fighissimamente figo','lamiera',55724,19),(79553,79565,'balaustra','Super fighissimamente figo','balaustra',10766,39),(80345,32202,'spazzatura','Super fighissimamente figo','spazzatura',NULL,44),(80469,84729,'zoom','Super fighissimamente figo','zoom',NULL,37),(80964,84729,'radiogoniometro','Super fighissimamente figo','radiogoniometro',23887,40),(81565,98527,'puntaspilli','Super fighissimamente figo','puntaspilli',NULL,14),(82285,99257,'ultramicrometro','Super fighissimamente figo','ultramicrometro',85746,17),(82343,40789,'infradito','Super fighissimamente figo','infradito',45935,44),(82533,74608,'igrografo','Super fighissimamente figo','igrografo',34045,10),(82822,35531,'impastatrice','Super fighissimamente figo','impastatrice',88693,27),(82900,51314,'gualdrappa','Super fighissimamente figo','gualdrappa',NULL,49),(83233,48207,'dentiera','Super fighissimamente figo','dentiera',91469,49),(83892,50718,'reattore','Super fighissimamente figo','reattore',NULL,43),(84075,79565,'trousse','Super fighissimamente figo','trousse',63636,34),(84128,61822,'pialla','Super fighissimamente figo','pialla',NULL,33),(86513,40789,'fotocellula','Super fighissimamente figo','fotocellula',92803,49),(86653,79565,'zaino','Super fighissimamente figo','zaino',35060,33),(86710,61822,'mulinello','Super fighissimamente figo','mulinello',25597,49),(87140,32202,'macchinario','Super fighissimamente figo','macchinario',50359,27),(87338,93185,'ramazza','Super fighissimamente figo','ramazza',66327,35),(87696,74608,'cronometro','Super fighissimamente figo','cronometro',NULL,42),(88310,46310,'bus','Super fighissimamente figo','bus',78089,18),(88491,46310,'detersivo','Super fighissimamente figo','detersivo',NULL,18),(88820,35531,'modem','Super fighissimamente figo','modem',NULL,27),(89138,98527,'corsetto','Super fighissimamente figo','corsetto',NULL,16),(89202,99257,'pirografo','Super fighissimamente figo','pirografo',NULL,22),(89383,40789,'graticola','Super fighissimamente figo','graticola',NULL,20),(89402,93185,'mantellina','Super fighissimamente figo','mantellina',NULL,28),(89822,98527,'fucina','Super fighissimamente figo','fucina',51322,29),(90241,84729,'registro','Super fighissimamente figo','registro',23887,38),(90530,79565,'spillone','Super fighissimamente figo','spillone',NULL,19),(90654,74608,'taccuino','Super fighissimamente figo','taccuino',NULL,43),(90696,98527,'patente','Super fighissimamente figo','patente',NULL,30),(91745,32202,'elettroscopio','Super fighissimamente figo','elettroscopio',25047,13),(91789,40789,'router','Super fighissimamente figo','router',27469,47),(92735,32202,'lucchetto','Super fighissimamente figo','lucchetto',94057,28),(93062,93185,'fucina','Super fighissimamente figo','fucina',NULL,13),(93396,93185,'portagioielli','Super fighissimamente figo','portagioielli',NULL,15),(94660,40789,'boccia','Super fighissimamente figo','boccia',NULL,11),(96761,93185,'littorina','Super fighissimamente figo','littorina',NULL,25),(96948,50718,'lavastoviglie','Super fighissimamente figo','lavastoviglie',43279,14),(97265,35531,'album','Super fighissimamente figo','album',68871,25),(97299,40789,'magnetofono','Super fighissimamente figo','magnetofono',NULL,25),(97306,40789,'astronave','Super fighissimamente figo','astronave',32203,10),(98027,35531,'profondimetro','Super fighissimamente figo','profondimetro',NULL,18);
/*!40000 ALTER TABLE `articoli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aste`
--

DROP TABLE IF EXISTS `aste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aste` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idCreatore` int NOT NULL,
  `prezzoIniziale` int NOT NULL,
  `rialzoMinimo` int NOT NULL,
  `dataTermine` datetime NOT NULL,
  `closed` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94058 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aste`
--

LOCK TABLES `aste` WRITE;
/*!40000 ALTER TABLE `aste` DISABLE KEYS */;
INSERT INTO `aste` VALUES (10766,79565,56,35,'2025-09-06 09:15:15',_binary '\0'),(14299,79565,64,12,'2023-02-04 03:06:16',_binary ''),(16903,35531,64,40,'2023-01-17 18:17:54',_binary ''),(17333,11813,28,29,'2023-11-08 06:13:44',_binary '\0'),(18800,46310,49,47,'2022-05-05 02:26:59',_binary ''),(22155,50718,118,42,'2022-12-05 01:11:30',_binary ''),(23887,84729,129,29,'2025-06-03 11:57:53',_binary '\0'),(25047,32202,36,38,'2024-05-06 01:56:23',_binary '\0'),(25597,61822,144,25,'2023-04-17 21:10:34',_binary ''),(26805,74608,48,46,'2024-06-18 07:05:15',_binary '\0'),(27469,40789,79,49,'2022-08-24 00:10:56',_binary ''),(28388,99257,87,11,'2025-10-05 07:10:37',_binary '\0'),(32203,40789,93,11,'2023-11-21 12:04:55',_binary '\0'),(34045,74608,84,47,'2025-04-11 05:44:00',_binary '\0'),(34869,50718,39,48,'2024-08-09 04:58:34',_binary '\0'),(35060,79565,122,42,'2025-07-07 07:54:51',_binary '\0'),(35284,32202,10,41,'2023-05-01 06:54:09',_binary ''),(39799,93185,30,36,'2022-05-21 15:24:59',_binary ''),(43279,50718,14,35,'2023-09-15 15:28:51',_binary '\0'),(43321,35531,69,41,'2025-10-25 13:52:28',_binary '\0'),(45935,40789,74,31,'2025-08-07 16:05:29',_binary '\0'),(46199,11813,21,27,'2024-02-24 06:07:09',_binary '\0'),(50359,32202,129,12,'2022-06-04 09:54:05',_binary ''),(51322,98527,78,38,'2022-02-12 02:11:39',_binary ''),(51703,99257,52,31,'2023-12-10 17:45:20',_binary '\0'),(55724,50718,43,26,'2025-09-21 22:21:05',_binary '\0'),(57248,99257,70,31,'2023-12-18 14:42:53',_binary '\0'),(61373,99257,41,16,'2025-08-24 23:53:38',_binary '\0'),(63636,79565,74,30,'2025-12-14 11:46:03',_binary '\0'),(66327,93185,46,47,'2023-08-26 23:25:54',_binary '\0'),(67715,35531,34,44,'2022-11-26 20:59:56',_binary ''),(67970,51314,37,21,'2022-04-06 10:14:46',_binary ''),(68871,35531,111,16,'2023-05-22 10:04:26',_binary '\0'),(69793,32202,63,20,'2025-02-05 17:55:58',_binary '\0'),(70862,32202,96,50,'2024-05-22 16:18:11',_binary '\0'),(70906,48207,61,19,'2024-05-26 01:58:57',_binary '\0'),(71044,48207,63,27,'2022-09-08 16:20:07',_binary ''),(78089,46310,36,36,'2022-08-15 08:09:30',_binary ''),(81833,93185,116,39,'2025-12-27 07:38:29',_binary '\0'),(85746,99257,135,19,'2022-01-28 09:29:39',_binary ''),(86113,46310,64,10,'2024-06-07 06:03:37',_binary '\0'),(88693,35531,71,40,'2022-01-28 00:32:34',_binary ''),(89806,40789,122,17,'2023-05-09 04:31:29',_binary '\0'),(89912,93185,11,50,'2025-05-26 06:34:28',_binary '\0'),(91469,48207,49,41,'2022-03-07 08:06:52',_binary ''),(92803,40789,49,45,'2024-02-27 11:07:12',_binary '\0'),(93695,51314,26,37,'2023-07-06 16:22:19',_binary '\0'),(94057,32202,112,18,'2022-06-09 14:03:27',_binary '');
/*!40000 ALTER TABLE `aste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `asteaperte`
--

DROP TABLE IF EXISTS `asteaperte`;
/*!50001 DROP VIEW IF EXISTS `asteaperte`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `asteaperte` AS SELECT 
 1 AS `idAsta`,
 1 AS `prezzoIniziale`,
 1 AS `rialzoMinimo`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `astechiuse`
--

DROP TABLE IF EXISTS `astechiuse`;
/*!50001 DROP VIEW IF EXISTS `astechiuse`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `astechiuse` AS SELECT 
 1 AS `idAsta`,
 1 AS `idVincitore`,
 1 AS `prezzoFinale`,
 1 AS `indSpedizione`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `messaggi`
--

DROP TABLE IF EXISTS `messaggi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messaggi` (
  `id` varchar(30) NOT NULL,
  `lingua` varchar(15) NOT NULL,
  `messaggio` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messaggi`
--

LOCK TABLES `messaggi` WRITE;
/*!40000 ALTER TABLE `messaggi` DISABLE KEYS */;
/*!40000 ALTER TABLE `messaggi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offerte`
--

DROP TABLE IF EXISTS `offerte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offerte` (
  `idCreatore` int NOT NULL,
  `idAsta` int NOT NULL,
  `prezzoOfferto` int NOT NULL,
  `dataOfferta` datetime NOT NULL,
  PRIMARY KEY (`idCreatore`,`idAsta`,`dataOfferta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offerte`
--

LOCK TABLES `offerte` WRITE;
/*!40000 ALTER TABLE `offerte` DISABLE KEYS */;
INSERT INTO `offerte` VALUES (11813,10766,583,'2023-05-04 12:21:41'),(11813,14299,90,'2023-05-04 12:17:33'),(11813,18800,191,'2023-05-04 12:18:15'),(11813,18800,239,'2023-05-04 12:18:36'),(11813,23887,270,'2023-05-04 12:22:29'),(11813,39799,530,'2023-05-04 12:20:27'),(11813,43321,1004,'2023-05-04 12:25:16'),(11813,50359,230,'2023-05-04 12:21:13'),(11813,51703,419,'2023-05-04 12:23:03'),(11813,63636,114,'2023-05-04 12:17:25'),(11813,63636,201,'2023-05-04 12:19:48'),(11813,68871,357,'2023-05-04 12:24:50'),(11813,68871,394,'2023-05-04 12:26:37'),(11813,86113,29,'2023-05-04 12:19:02'),(11813,88693,374,'2023-05-04 12:17:37'),(11813,88693,559,'2023-05-04 12:19:00'),(11813,88693,1046,'2023-05-04 12:22:49'),(11813,93695,838,'2023-05-04 12:25:08'),(32202,10766,817,'2023-05-04 12:26:47'),(32202,18800,141,'2023-05-04 12:17:49'),(32202,22155,136,'2023-05-04 12:20:10'),(32202,22155,427,'2023-05-04 12:23:14'),(32202,23887,366,'2023-05-04 12:26:21'),(32202,25597,363,'2023-05-04 12:22:15'),(32202,27469,514,'2023-05-04 12:25:49'),(32202,28388,71,'2023-05-04 12:17:17'),(32202,35284,214,'2023-05-04 12:21:05'),(32202,35284,623,'2023-05-04 12:26:51'),(32202,43321,772,'2023-05-04 12:24:06'),(32202,46199,549,'2023-05-04 12:26:09'),(32202,51703,717,'2023-05-04 12:26:27'),(32202,57248,202,'2023-05-04 12:18:25'),(32202,57248,569,'2023-05-04 12:26:49'),(32202,61373,29,'2023-05-04 12:18:32'),(32202,67970,359,'2023-05-04 12:22:57'),(32202,68871,119,'2023-05-04 12:19:34'),(32202,70862,304,'2023-05-04 12:23:22'),(32202,86113,260,'2023-05-04 12:24:34'),(32202,91469,322,'2023-05-04 12:22:01'),(32202,92803,216,'2023-05-04 12:18:46'),(35531,18800,325,'2023-05-04 12:18:52'),(35531,22155,626,'2023-05-04 12:26:13'),(35531,26805,282,'2023-05-04 12:19:50'),(35531,46199,217,'2023-05-04 12:23:01'),(35531,57248,311,'2023-05-04 12:22:23'),(35531,57248,529,'2023-05-04 12:24:12'),(35531,63636,492,'2023-05-04 12:25:51'),(35531,67715,132,'2023-05-04 12:22:51'),(35531,71044,508,'2023-05-04 12:24:42'),(35531,91469,214,'2023-05-04 12:19:54'),(35531,93695,513,'2023-05-04 12:21:53'),(40789,10766,364,'2023-05-04 12:20:04'),(40789,28388,242,'2023-05-04 12:19:04'),(40789,35060,532,'2023-05-04 12:24:58'),(40789,35284,327,'2023-05-04 12:21:55'),(40789,39799,113,'2023-05-04 12:17:03'),(40789,43321,98,'2023-05-04 12:17:43'),(40789,46199,118,'2023-05-04 12:18:13'),(40789,51703,666,'2023-05-04 12:25:47'),(40789,63636,399,'2023-05-04 12:23:07'),(40789,66327,639,'2023-05-04 12:26:45'),(40789,67970,323,'2023-05-04 12:22:47'),(40789,67970,447,'2023-05-04 12:26:17'),(40789,69793,355,'2023-05-04 12:20:33'),(40789,70862,67,'2023-05-04 12:19:56'),(40789,70906,412,'2023-05-04 12:23:34'),(40789,78089,190,'2023-05-04 12:23:36'),(40789,88693,737,'2023-05-04 12:19:18'),(40789,89806,403,'2023-05-04 12:24:56'),(40789,89912,77,'2023-05-04 12:18:38'),(40789,91469,439,'2023-05-04 12:23:54'),(40789,94057,181,'2023-05-04 12:19:14'),(46310,16903,390,'2023-05-04 12:21:57'),(46310,18800,704,'2023-05-04 12:23:30'),(46310,23887,77,'2023-05-04 12:18:09'),(46310,25047,213,'2023-05-04 12:17:15'),(46310,27469,418,'2023-05-04 12:24:32'),(46310,28388,384,'2023-05-04 12:20:51'),(46310,28388,429,'2023-05-04 12:21:25'),(46310,28388,540,'2023-05-04 12:21:45'),(46310,32203,165,'2023-05-04 12:18:54'),(46310,34869,445,'2023-05-04 12:24:28'),(46310,51703,327,'2023-05-04 12:21:33'),(46310,55724,90,'2023-05-04 12:17:51'),(46310,63636,364,'2023-05-04 12:22:55'),(46310,70906,187,'2023-05-04 12:20:00'),(46310,70906,490,'2023-05-04 12:25:41'),(46310,71044,397,'2023-05-04 12:24:02'),(46310,88693,270,'2023-05-04 12:17:29'),(46310,89806,308,'2023-05-04 12:23:38'),(46310,89912,139,'2023-05-04 12:20:31'),(46310,93695,326,'2023-05-04 12:19:24'),(46310,94057,227,'2023-05-04 12:21:39'),(48207,10766,751,'2023-05-04 12:25:35'),(48207,25597,424,'2023-05-04 12:24:18'),(48207,26805,128,'2023-05-04 12:17:09'),(48207,26805,375,'2023-05-04 12:26:01'),(48207,27469,207,'2023-05-04 12:18:40'),(48207,27469,277,'2023-05-04 12:21:47'),(48207,32203,432,'2023-05-04 12:23:26'),(48207,32203,448,'2023-05-04 12:24:22'),(48207,34045,126,'2023-05-04 12:18:34'),(48207,35060,218,'2023-05-04 12:21:07'),(48207,39799,814,'2023-05-04 12:25:25'),(48207,46199,488,'2023-05-04 12:25:10'),(48207,50359,66,'2023-05-04 12:18:48'),(48207,50359,171,'2023-05-04 12:21:03'),(48207,50359,357,'2023-05-04 12:25:04'),(48207,61373,244,'2023-05-04 12:23:40'),(48207,78089,66,'2023-05-04 12:18:42'),(48207,88693,104,'2023-05-04 12:16:49'),(48207,88693,451,'2023-05-04 12:18:23'),(48207,91469,99,'2023-05-04 12:18:11'),(48207,91469,486,'2023-05-04 12:25:02'),(48207,93695,728,'2023-05-04 12:23:46'),(48207,94057,96,'2023-05-04 12:18:19'),(50718,17333,88,'2023-05-04 12:20:43'),(50718,28388,746,'2023-05-04 12:26:11'),(50718,32203,332,'2023-05-04 12:22:13'),(50718,34045,194,'2023-05-04 12:26:03'),(50718,39799,306,'2023-05-04 12:17:27'),(50718,43279,51,'2023-05-04 12:18:50'),(50718,46199,385,'2023-05-04 12:24:10'),(50718,51322,77,'2023-05-04 12:20:14'),(50718,55724,213,'2023-05-04 12:17:55'),(50718,55724,346,'2023-05-04 12:25:57'),(50718,66327,416,'2023-05-04 12:25:27'),(50718,85746,179,'2023-05-04 12:18:44'),(50718,86113,131,'2023-05-04 12:23:09'),(50718,88693,184,'2023-05-04 12:17:01'),(50718,88693,672,'2023-05-04 12:19:16'),(50718,89806,86,'2023-05-04 12:19:10'),(50718,89806,507,'2023-05-04 12:25:39'),(51314,16903,347,'2023-05-04 12:20:53'),(51314,16903,607,'2023-05-04 12:25:55'),(51314,18800,583,'2023-05-04 12:20:06'),(51314,18800,830,'2023-05-04 12:24:20'),(51314,18800,890,'2023-05-04 12:24:40'),(51314,23887,121,'2023-05-04 12:18:56'),(51314,25047,79,'2023-05-04 12:16:57'),(51314,25047,540,'2023-05-04 12:23:42'),(51314,34869,122,'2023-05-04 12:19:40'),(51314,34869,311,'2023-05-04 12:20:29'),(51314,34869,361,'2023-05-04 12:20:55'),(51314,35060,845,'2023-05-04 12:26:31'),(51314,35284,407,'2023-05-04 12:22:43'),(51314,35284,517,'2023-05-04 12:26:19'),(51314,39799,216,'2023-05-04 12:17:11'),(51314,43321,263,'2023-05-04 12:19:26'),(51314,43321,1143,'2023-05-04 12:25:19'),(51314,50359,344,'2023-05-04 12:22:41'),(51314,51703,479,'2023-05-04 12:24:16'),(51314,55724,276,'2023-05-04 12:18:28'),(51314,61373,70,'2023-05-04 12:20:02'),(51314,61373,101,'2023-05-04 12:21:21'),(51314,67970,240,'2023-05-04 12:22:39'),(51314,68871,172,'2023-05-04 12:21:37'),(51314,68871,261,'2023-05-04 12:23:24'),(51314,70906,301,'2023-05-04 12:22:37'),(51314,71044,298,'2023-05-04 12:23:20'),(51314,85746,406,'2023-05-04 12:25:12'),(51314,86113,162,'2023-05-04 12:23:52'),(51314,92803,398,'2023-05-04 12:22:31'),(61822,10766,234,'2023-05-04 12:16:55'),(61822,10766,431,'2023-05-04 12:20:23'),(61822,10766,698,'2023-05-04 12:24:30'),(61822,16903,156,'2023-05-04 12:20:21'),(61822,22155,319,'2023-05-04 12:22:27'),(61822,25597,208,'2023-05-04 12:19:38'),(61822,32203,206,'2023-05-04 12:19:20'),(61822,32203,244,'2023-05-04 12:21:01'),(61822,39799,437,'2023-05-04 12:20:08'),(61822,45935,55,'2023-05-04 12:20:35'),(61822,45935,177,'2023-05-04 12:22:05'),(61822,51322,219,'2023-05-04 12:23:11'),(61822,55724,180,'2023-05-04 12:17:53'),(61822,63636,155,'2023-05-04 12:18:21'),(61822,66327,137,'2023-05-04 12:17:23'),(61822,70862,407,'2023-05-04 12:26:39'),(61822,70906,109,'2023-05-04 12:17:41'),(61822,70906,602,'2023-05-04 12:25:43'),(61822,78089,228,'2023-05-04 12:23:44'),(61822,88693,857,'2023-05-04 12:22:07'),(61822,92803,495,'2023-05-04 12:22:53'),(74608,10766,466,'2023-05-04 12:21:15'),(74608,28388,640,'2023-05-04 12:23:48'),(74608,28388,682,'2023-05-04 12:24:08'),(74608,35060,714,'2023-05-04 12:25:53'),(74608,39799,578,'2023-05-04 12:21:11'),(74608,43321,875,'2023-05-04 12:24:48'),(74608,46199,168,'2023-05-04 12:22:19'),(74608,69793,295,'2023-05-04 12:20:12'),(74608,70906,464,'2023-05-04 12:24:38'),(74608,81833,95,'2023-05-04 12:21:29'),(74608,85746,70,'2023-05-04 12:17:05'),(74608,85746,252,'2023-05-04 12:23:28'),(74608,94057,61,'2023-05-04 12:16:51'),(79565,10766,107,'2023-05-04 12:16:53'),(79565,14299,107,'2023-05-04 12:21:27'),(79565,16903,99,'2023-05-04 12:19:08'),(79565,16903,293,'2023-05-04 12:20:39'),(79565,26805,209,'2023-05-04 12:18:58'),(79565,32203,539,'2023-05-04 12:24:24'),(79565,34869,260,'2023-05-04 12:20:19'),(79565,35060,133,'2023-05-04 12:19:42'),(79565,35060,311,'2023-05-04 12:21:09'),(79565,35060,441,'2023-05-04 12:22:09'),(79565,43279,173,'2023-05-04 12:20:45'),(79565,43321,830,'2023-05-04 12:24:46'),(79565,51322,144,'2023-05-04 12:21:35'),(79565,51703,173,'2023-05-04 12:17:59'),(79565,55724,306,'2023-05-04 12:22:25'),(79565,68871,70,'2023-05-04 12:19:12'),(79565,69793,250,'2023-05-04 12:19:44'),(79565,70862,166,'2023-05-04 12:20:37'),(79565,71044,669,'2023-05-04 12:25:45'),(79565,78089,135,'2023-05-04 12:19:28'),(79565,86113,277,'2023-05-04 12:24:52'),(79565,89806,671,'2023-05-04 12:26:15'),(79565,92803,45,'2023-05-04 12:18:07'),(79565,93695,438,'2023-05-04 12:19:58'),(84729,16903,503,'2023-05-04 12:22:17'),(84729,25047,598,'2023-05-04 12:26:25'),(84729,25597,108,'2023-05-04 12:17:35'),(84729,25597,280,'2023-05-04 12:20:41'),(84729,27469,124,'2023-05-04 12:17:39'),(84729,28388,179,'2023-05-04 12:18:03'),(84729,35060,595,'2023-05-04 12:25:33'),(84729,35060,653,'2023-05-04 12:25:37'),(84729,35284,459,'2023-05-04 12:25:21'),(84729,39799,43,'2023-05-04 12:16:59'),(84729,43279,119,'2023-05-04 12:19:30'),(84729,43321,670,'2023-05-04 12:23:16'),(84729,50359,26,'2023-05-04 12:18:05'),(84729,55724,379,'2023-05-04 12:26:35'),(84729,57248,451,'2023-05-04 12:23:32'),(84729,66327,345,'2023-05-04 12:25:06'),(84729,67970,542,'2023-05-04 12:26:41'),(84729,69793,48,'2023-05-04 12:17:07'),(84729,69793,164,'2023-05-04 12:17:57'),(84729,81833,212,'2023-05-04 12:24:54'),(84729,85746,353,'2023-05-04 12:24:14'),(84729,89806,197,'2023-05-04 12:21:19'),(84729,89806,432,'2023-05-04 12:25:00'),(84729,92803,272,'2023-05-04 12:20:16'),(84729,92803,716,'2023-05-04 12:24:26'),(84729,92803,798,'2023-05-04 12:26:43'),(84729,93695,235,'2023-05-04 12:17:47'),(93185,10766,640,'2023-05-04 12:22:21'),(93185,22155,504,'2023-05-04 12:23:58'),(93185,22155,704,'2023-05-04 12:26:33'),(93185,25047,317,'2023-05-04 12:20:25'),(93185,25597,474,'2023-05-04 12:25:23'),(93185,34045,70,'2023-05-04 12:18:17'),(93185,35284,116,'2023-05-04 12:17:19'),(93185,39799,696,'2023-05-04 12:21:17'),(93185,43321,396,'2023-05-04 12:19:52'),(93185,46199,305,'2023-05-04 12:24:04'),(93185,50359,298,'2023-05-04 12:22:33'),(93185,57248,350,'2023-05-04 12:22:59'),(93185,69793,382,'2023-05-04 12:24:44'),(93185,71044,587,'2023-05-04 12:25:14'),(93185,89806,365,'2023-05-04 12:23:50'),(93185,89806,562,'2023-05-04 12:26:05'),(93185,93695,117,'2023-05-04 12:17:45'),(98527,16903,720,'2023-05-04 12:25:59'),(98527,25047,410,'2023-05-04 12:21:49'),(98527,25597,390,'2023-05-04 12:24:00'),(98527,34045,294,'2023-05-04 12:26:29'),(98527,35060,490,'2023-05-04 12:22:45'),(98527,43279,287,'2023-05-04 12:24:36'),(98527,43321,148,'2023-05-04 12:19:06'),(98527,43321,531,'2023-05-04 12:21:43'),(98527,51322,263,'2023-05-04 12:26:23'),(98527,51703,274,'2023-05-04 12:19:46'),(98527,51703,603,'2023-05-04 12:25:29'),(98527,67970,46,'2023-05-04 12:20:49'),(98527,71044,113,'2023-05-04 12:18:01'),(98527,71044,239,'2023-05-04 12:22:35'),(98527,86113,295,'2023-05-04 12:25:31'),(98527,89912,221,'2023-05-04 12:23:56'),(98527,92803,122,'2023-05-04 12:18:30'),(99257,17333,182,'2023-05-04 12:23:05'),(99257,18800,440,'2023-05-04 12:19:22'),(99257,22155,189,'2023-05-04 12:22:03'),(99257,23887,206,'2023-05-04 12:19:32'),(99257,25047,368,'2023-05-04 12:20:57'),(99257,28388,274,'2023-05-04 12:20:47'),(99257,32203,95,'2023-05-04 12:17:31'),(99257,51703,75,'2023-05-04 12:17:21'),(99257,57248,83,'2023-05-04 12:17:13'),(99257,61373,170,'2023-05-04 12:21:23'),(99257,63636,283,'2023-05-04 12:21:31'),(99257,66327,259,'2023-05-04 12:19:36'),(99257,66327,494,'2023-05-04 12:26:07'),(99257,67970,150,'2023-05-04 12:20:59'),(99257,88693,939,'2023-05-04 12:22:11'),(99257,89806,264,'2023-05-04 12:21:51'),(99257,92803,572,'2023-05-04 12:23:18'),(99257,93695,630,'2023-05-04 12:21:59');
/*!40000 ALTER TABLE `offerte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utenti` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `passWord` varchar(100) NOT NULL,
  `indirizzoSpedizione` varchar(255) DEFAULT NULL,
  `lingua` varchar(15) DEFAULT 'it_IT',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti`
--

LOCK TABLES `utenti` WRITE;
/*!40000 ALTER TABLE `utenti` DISABLE KEYS */;
INSERT INTO `utenti` VALUES (11813,'lorenzoLuciani','password','Via ForneroVII, 18','it_IT'),(32202,'micheleFornetti','password','Largo AlighieriVII, 23','ja_JP'),(35531,'giovanniAlighieri','password','Parco AlighieriV, 13','ja_JP'),(40789,'vincenzoAlighieri','password','Via LuchiniVII, 72','en_US'),(46310,'lorenzoMonti','password','Piazza GuarnieriII, 69','it_IT'),(48207,'federicoAlighieri','password','Piazza GuarnieriIII, 35','de_BE'),(50718,'lucaMonti','password','Viale MicheliII, 68','en_US'),(51314,'federicoMicheli','password','Via AngiolieriIV, 91','ja_JP'),(61822,'lucaAlighieri','password','Largo FasciniII, 1','it_IT'),(74608,'chiaraAlighieri','password','Parco LucianiIV, 49','de_BE'),(79565,'lorenzoLuchini','password','Via AlighieriVIII, 49','de_BE'),(84729,'federicoMicheli','password','Via AngiolieriVII, 21','ja_JP'),(93185,'davideFornero','password','Piazza FornettiVII, 93','en_US'),(98527,'federicoFornetti','password','Piazza FornettiIII, 95','ja_JP'),(99257,'davideLuciani','password','Parco GuarnieriV, 84','en_US');
/*!40000 ALTER TABLE `utenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `asteaperte`
--

/*!50001 DROP VIEW IF EXISTS `asteaperte`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`carlo`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `asteaperte` (`idAsta`,`prezzoIniziale`,`rialzoMinimo`) AS select `aste`.`id` AS `id`,`aste`.`prezzoIniziale` AS `prezzoIniziale`,`aste`.`rialzoMinimo` AS `rialzoMinimo` from `aste` where (`aste`.`closed` = false) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `astechiuse`
--

/*!50001 DROP VIEW IF EXISTS `astechiuse`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`carlo`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `astechiuse` (`idAsta`,`idVincitore`,`prezzoFinale`,`indSpedizione`) AS select `a`.`id` AS `id`,`o`.`idCreatore` AS `idCreatore`,`o`.`prezzoOfferto` AS `prezzoOfferto`,`u`.`indirizzoSpedizione` AS `indirizzoSpedizione` from ((`aste` `a` join `offerte` `o` on((`a`.`id` = `o`.`idAsta`))) join `utenti` `u` on((`o`.`idCreatore` = `u`.`id`))) where (`a`.`closed` = true) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-05  9:40:15
