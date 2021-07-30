-- --------------------------------------------------------
-- 主機:                           127.0.0.1
-- 伺服器版本:                        5.7.24-log - MySQL Community Server (GPL)
-- 伺服器操作系統:                      Win64
-- HeidiSQL 版本:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 傾印 ssm_crud 的資料庫結構
CREATE DATABASE IF NOT EXISTS `ssm_crud` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ssm_crud`;

-- 傾印  表格 ssm_crud.department 結構
CREATE TABLE IF NOT EXISTS `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在傾印表格  ssm_crud.department 的資料：~1 rows (約數)
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`id`, `name`) VALUES
	(1, '生管課'),
	(2, '會計課');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;

-- 傾印  表格 ssm_crud.employee 結構
CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `gender` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `dept_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- 正在傾印表格  ssm_crud.employee 的資料：~7 rows (約數)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`id`, `name`, `gender`, `email`, `dept_id`) VALUES
	(1, 'tom', 1, 'tom@yahoo.com.tw', 1),
	(2, 'tom', 1, 'tom@yahoo.com.tw', 1),
	(3, 'tom', 1, 'tom@yahoo.com.tw', 1),
	(4, 'tom', 1, 'tom@yahoo.com.tw', 1),
	(5, 'tom', 1, 'tom@yahoo.com.tw', 1),
	(6, 'tom', 1, 'tom@yahoo.com.tw', 1),
	(7, 'tom', 1, 'tom@yahoo.com.tw', 1),
	(8, 'lin', 1, 'lin@yahoo.com.tw', 1),
	(9, 'ggg', 1, 'gggg', 1),
	(16, 'bbbbbbbbb', 1, 'bbbbbbbb', 1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
