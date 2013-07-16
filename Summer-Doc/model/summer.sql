-- MySQL dump 10.13  Distrib 5.6.12, for Win64 (x86_64)
--
-- Host: localhost    Database: summer
-- ------------------------------------------------------
-- Server version	5.6.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `stuff`
--

DROP TABLE IF EXISTS `stuff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stuff` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id of stuff',
  `stuff_category_id` bigint(20) unsigned NOT NULL COMMENT 'id of stuff category which this stuff belong to.',
  `code` varchar(255) NOT NULL COMMENT 'unique readable code of stuff',
  `price` double unsigned NOT NULL COMMENT 'price of stuff',
  `life` int(10) unsigned NOT NULL COMMENT 'service life of stuff.',
  `address` varchar(255) NOT NULL,
  `factory` varchar(255) NOT NULL,
  `zxing` varchar(255) NOT NULL DEFAULT '""' COMMENT 'path of stuff''s two-dimension code,empty string if there isn''t two-dimension code exist.',
  `start_time` bigint(20) NOT NULL DEFAULT '0' COMMENT 'time of stuff start to be used.',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=gbk COMMENT='A concrete things that should be check. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuff`
--

LOCK TABLES `stuff` WRITE;
/*!40000 ALTER TABLE `stuff` DISABLE KEYS */;
INSERT INTO `stuff` VALUES (1,2,'电线杆1',1000,100,'高新区雅居园','安全电器工厂','\"\"',1372953600000),(2,1,'变压器2',2000,100,'高新区雅居园','长征电气','“”',1372953600000),(3,1,'变压器1',1000,200,'高新区雅居园','泉城工厂','\"\"',1372953600000),(4,2,'电线杆2',1000,100,'高新区雅居园','思源电气','“”',1372953600000),(5,3,'输配电1',2000,200,'高新区软件园','泉城工厂','“”',1372953600000),(6,3,'输配电2',1000,100,'高新区软件园','思源电气','“”',1372953600000),(7,2,'电线杆3',2000,100,'高新区软件园','泉城工厂','“”',1372953600000),(8,2,'电线杆4',2000,100,'高新区贤文庄','泉城工厂','“”',1372953600000),(9,2,'电线杆5',2000,100,'高新区贤文庄','思源电气','“”',1372953600000),(10,1,'变压器3',1000,100,'高新区贤文庄','思源电气','“”',1372953600000),(11,3,'输配电3',1000,100,'高新区贤文庄','思源电气','“”',1372953600000),(12,3,'输配电4',1000,200,'历城区','泉城工厂','“”',1372953600000),(13,2,'电线杆6',2000,100,'历城区','泉城工厂','“”',1372953600000),(14,1,'变压器4',1000,100,'历城区','泉城工厂','“”',1372953600000),(15,3,'输配电5',1000,100,'历下区','长征电气','“”',1372953600000),(16,3,'输配电6',2000,200,'历下区','安全电器工厂','“”',1372953600000),(17,1,'变压器5',2000,200,'历下区','安全电器工厂','“”',1372953600000);
/*!40000 ALTER TABLE `stuff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stuff_arg`
--

DROP TABLE IF EXISTS `stuff_arg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stuff_arg` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id of stuff_arg',
  `stuff_id` bigint(20) unsigned NOT NULL COMMENT 'stuff''s  id',
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=gbk COMMENT='this arg is close to tasklet_item_arg.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuff_arg`
--

LOCK TABLES `stuff_arg` WRITE;
/*!40000 ALTER TABLE `stuff_arg` DISABLE KEYS */;
INSERT INTO `stuff_arg` VALUES (1,1,'高度','10','正负2'),(2,1,'直径','0.2','正负0.1'),(3,2,'吸湿器湿度','22','正负2'),(4,2,'散热器温度','25','正负5'),(5,2,'绝缘套管厚度','0.3','正负0.1'),(6,3,'吸湿器湿度','22','正负2'),(7,3,'散热器温度','25','正负5'),(8,3,'绝缘套管厚度','0.3','正负0.1'),(9,4,'高度','10','正负2'),(10,4,'直径','0.2','正负0.1'),(11,5,'电压','5','正负1'),(12,6,'电压','5','正负1'),(13,7,'高度','10','正负2'),(14,7,'直径','0.2','正负0.1'),(15,8,'高度','10','正负2'),(16,8,'直径','0.2','正负0.1'),(17,9,'高度','10','正负2'),(18,9,'直径','0.2','正负0.1'),(19,10,'吸湿器湿度','22','正负2'),(20,10,'散热器温度','25','正负5'),(21,10,'绝缘套管厚度','0.3','正负0.1'),(22,11,'电压','5','正负1'),(23,12,'电压','5','正负1'),(24,13,'高度','10','正负1'),(25,13,'直径','0.2','正负0.1'),(26,14,'吸湿器湿度','22','正负2'),(27,14,'散热器温度','25','正负5'),(28,14,'绝缘套管厚度','0.3','正负0.1'),(29,15,'电压','5','正负1'),(30,16,'电压','5','正负1'),(31,17,'吸湿器湿度','22','正负2'),(32,17,'散热器温度','25','正负5'),(33,17,'绝缘套管厚度','0.3','正负0.1');
/*!40000 ALTER TABLE `stuff_arg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stuff_category`
--

DROP TABLE IF EXISTS `stuff_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stuff_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id of this stuff category',
  `template_item_id` bigint(20) unsigned DEFAULT NULL COMMENT 'id of template_item which can be used when create a tasklet item for specific.',
  `name` varchar(255) DEFAULT NULL COMMENT 'name of stuff categoy',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk COMMENT='category of stuff.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuff_category`
--

LOCK TABLES `stuff_category` WRITE;
/*!40000 ALTER TABLE `stuff_category` DISABLE KEYS */;
INSERT INTO `stuff_category` VALUES (1,3,'变压器'),(2,2,'电线杆'),(3,1,'输配电');
/*!40000 ALTER TABLE `stuff_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasklet`
--

DROP TABLE IF EXISTS `tasklet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasklet` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id of tasklet',
  `user_id` bigint(20) unsigned NOT NULL COMMENT 'id of the user who carry out this tasklet.',
  `name` varchar(255) NOT NULL COMMENT 'name of tasklet',
  `cycle` bigint(20) NOT NULL DEFAULT '0' COMMENT 'cycle of this tasklet',
  `account` int(11) NOT NULL DEFAULT '1' COMMENT 'when this tasklet is finish the value of account must be 0',
  `last_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk COMMENT='tasklet table,every tasklet contains some item (tasklet_item /* comment truncated */ /*).*/';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasklet`
--

LOCK TABLES `tasklet` WRITE;
/*!40000 ALTER TABLE `tasklet` DISABLE KEYS */;
INSERT INTO `tasklet` VALUES (1,10001,'高新区雅居园',2592000000,10,1373040000000),(2,10001,'高新区软件园',2592000000,20,1373040000000),(3,10001,'高新区贤文庄',2592000000,10,1373040000000),(4,10002,'历城区',2592000000,5,1373040000000),(5,10003,'历下区',2592000000,10,1373040000000);
/*!40000 ALTER TABLE `tasklet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasklet_item`
--

DROP TABLE IF EXISTS `tasklet_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasklet_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id of item',
  `tasklet_id` bigint(20) unsigned NOT NULL COMMENT 'id of tasklet which this item belong to.',
  `stuff_id` bigint(20) unsigned NOT NULL COMMENT 'id of stuff which this item check for.',
  `name` varchar(255) NOT NULL COMMENT 'name of this tasklet item',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=gbk COMMENT='item of tasklet,A item is used to check a specific stuff and /* comment truncated */ /*  contains a lot of item arg that every arg is a name-value pair.*/';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasklet_item`
--

LOCK TABLES `tasklet_item` WRITE;
/*!40000 ALTER TABLE `tasklet_item` DISABLE KEYS */;
INSERT INTO `tasklet_item` VALUES (1,1,1,'电线杆1'),(2,1,2,'变压器2'),(3,1,3,'变压器1'),(4,1,4,'电线杆2'),(5,2,5,'输配电1'),(6,2,6,'输配电2'),(7,2,7,'电线杆3'),(8,3,8,'电线杆4'),(9,3,9,'电线杆5'),(10,3,10,'变压器3'),(11,3,11,'输配电3'),(12,4,12,'输配电4'),(13,4,13,'电线杆6'),(14,4,14,'变压器4'),(15,5,15,'输配电5'),(16,5,16,'输配电6'),(17,5,17,'变压器5');
/*!40000 ALTER TABLE `tasklet_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasklet_item_arg`
--

DROP TABLE IF EXISTS `tasklet_item_arg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasklet_item_arg` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id of tasklet_item_arg',
  `tasklet_item_id` bigint(20) unsigned NOT NULL COMMENT 'id of tasklet_item which this arg belongs to',
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=gbk COMMENT='A arg contain a name-value pair.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasklet_item_arg`
--

LOCK TABLES `tasklet_item_arg` WRITE;
/*!40000 ALTER TABLE `tasklet_item_arg` DISABLE KEYS */;
INSERT INTO `tasklet_item_arg` VALUES (1,1,'高度','10','正负2'),(2,1,'直径','0.2','正负0.1'),(3,2,'吸湿器湿度','22','正负2'),(4,2,'散热器温度','25','正负5'),(5,2,'绝缘套管厚度','0.3','正负0.1'),(6,3,'吸湿器湿度','22','正负2'),(7,3,'散热器温度','25','正负5'),(8,3,'绝缘套管厚度','0.3','正负0.1'),(9,4,'高度','0.2','正负2'),(10,4,'直径','5','正负0.1'),(11,5,'电压','5','正负1'),(12,6,'电压','10','正负1'),(13,7,'高度','10','正负2'),(14,7,'直径','0.2','正负0.1'),(15,8,'高度','10','正负2'),(16,8,'直径','0.2','正负0.1'),(17,9,'高度','10','正负2'),(18,9,'直径','0.2','正负0.1'),(19,10,'吸湿器湿度','22','正负2'),(20,10,'散热器温度','25','正负5'),(21,10,'绝缘套管厚度','0.3','正负0.1'),(22,11,'电压','5','正负1'),(23,12,'电压','5','正负1'),(24,13,'高度','10','正负2'),(25,13,'直径·','0.2','正负0.1'),(26,14,'吸湿器湿度','22','正负2'),(27,14,'散热器温度','25','正负5'),(28,14,'绝缘套管厚度','0.3','正负0.1'),(29,15,'电压','5','正负1'),(30,16,'电压','5','正负1'),(31,17,'吸湿器湿度','22','正负2'),(32,17,'散热器温度','25','正负5'),(33,17,'绝缘套管厚度','0.3','正负0.1');
/*!40000 ALTER TABLE `tasklet_item_arg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `template`
--

DROP TABLE IF EXISTS `template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id of template',
  `name` varchar(255) NOT NULL COMMENT 'name of template',
  `create_time` bigint(20) NOT NULL COMMENT 'create time of template',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gbk COMMENT='tasklet''s template';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template`
--

LOCK TABLES `template` WRITE;
/*!40000 ALTER TABLE `template` DISABLE KEYS */;
INSERT INTO `template` VALUES (1,'2变2杆',1356624000000),(2,'1输1变',1356624000000),(3,'2输',1356624000000),(4,'2输1杆',1356624000000),(5,'2杆1变1输',1356624000000),(6,'1杆1变1输',1356624000000),(7,'1变2输',1356624000000),(8,'2变2杆',1356624000000);
/*!40000 ALTER TABLE `template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `template_has_template_item`
--

DROP TABLE IF EXISTS `template_has_template_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template_has_template_item` (
  `id` bigint(20) NOT NULL,
  `template_id` bigint(20) unsigned NOT NULL,
  `template_item_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='template and template_item is n to m relation.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template_has_template_item`
--

LOCK TABLES `template_has_template_item` WRITE;
/*!40000 ALTER TABLE `template_has_template_item` DISABLE KEYS */;
INSERT INTO `template_has_template_item` VALUES (1,1,3),(2,1,3),(3,1,2),(4,1,2),(5,2,1),(6,2,3),(7,3,1),(8,3,1),(9,4,1),(10,4,1),(11,4,2),(12,5,2),(13,5,2),(14,5,3),(15,5,1),(16,6,2),(17,6,3),(18,6,1),(19,7,3),(20,7,1),(21,7,1),(22,8,3),(23,8,3),(24,8,2),(25,8,2);
/*!40000 ALTER TABLE `template_has_template_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `template_item`
--

DROP TABLE IF EXISTS `template_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id of template_item',
  `name` varchar(255) NOT NULL DEFAULT '""' COMMENT 'name of template_item',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk COMMENT='template item,every item contains a lot of arg and each item /* comment truncated */ /* is template of a specific stuff category.*/';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template_item`
--

LOCK TABLES `template_item` WRITE;
/*!40000 ALTER TABLE `template_item` DISABLE KEYS */;
INSERT INTO `template_item` VALUES (1,'输配电'),(2,'电线杆'),(3,'变压器');
/*!40000 ALTER TABLE `template_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `template_item_arg`
--

DROP TABLE IF EXISTS `template_item_arg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template_item_arg` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id of this arg',
  `template_item_id` bigint(20) unsigned NOT NULL COMMENT 'id of template_item which this arg belong to',
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk COMMENT='this arg is close to tasklet_item_arg,each arg also contains /* comment truncated */ /* a name-value pair*/';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template_item_arg`
--

LOCK TABLES `template_item_arg` WRITE;
/*!40000 ALTER TABLE `template_item_arg` DISABLE KEYS */;
INSERT INTO `template_item_arg` VALUES (1,2,'高度','10'),(2,2,'直径','0.2'),(3,3,'吸湿器湿度','20-25'),(4,3,'散热器温度','25-27'),(5,3,'绝缘套管厚度','0.2-0.5'),(6,1,'电压','5');
/*!40000 ALTER TABLE `template_item_arg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id of user',
  `name` varchar(255) NOT NULL DEFAULT '""' COMMENT 'name of user',
  `password` varchar(255) NOT NULL DEFAULT '""' COMMENT 'password of user,i think it should encrypted by RSA or other encryption algorithm before stored.',
  `type` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'type of user,0 stands for tour operator and 1 stands for administrator',
  `permission` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'permission of user,This is the integer that every bit has a meaning.Default 0 represents no permissions.',
  `tellphone` varchar(255) DEFAULT NULL COMMENT 'tellphone of user.',
  `address` varchar(255) DEFAULT NULL COMMENT 'address of user.',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10009 DEFAULT CHARSET=gbk COMMENT='This is user table, it contains two type user,one is tour op /* comment truncated */ /*erator,the other is administrator.Every column in this table, except for "tellphone" and "address",is not null!*/';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (10000,'张三','123456',1,7,'18769783279','历下区1550'),(10001,'李四','123456',0,0,'18769785096','长清区333'),(10002,'王二','123456',1,7,'18769783271','历城区222'),(10003,'小明','123456',1,7,'18769783272','历下区432'),(10004,'小红','123456',1,7,'18769783273','泉城路0009'),(10006,'小虎','123456',1,7,'18769782773','舜华路0403'),(10007,'小涛','123456',0,0,'18769783278','经十路6584'),(10008,'小海','123456',0,0,'18769783333','纬2路');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-16 16:32:03
