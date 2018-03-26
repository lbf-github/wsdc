
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES UTF8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP TABLE IF EXISTS `cy_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cy_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `resKey` varchar(50) DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  `resUrl` varchar(200) DEFAULT NULL,
  `level` int(4) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `ishide` int(3) DEFAULT '0',
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `cy_resources` WRITE;
/*!40000 ALTER TABLE `cy_resources` DISABLE KEYS */;
INSERT INTO `cy_resources` VALUES (1,'系统基础管理',0,'system','0','system',1,'fa-desktop',0,'系统基础管理');
INSERT INTO `cy_resources` VALUES (2,'用户管理',1,'account','1','/user/list.shtml',2,NULL,0,NULL);
INSERT INTO `cy_resources` VALUES (3,'角色管理',1,'role','1','/role/list.shtml',7,'fa-list',0,'组管理');
INSERT INTO `cy_resources` VALUES (4,'菜单管理',1,'cy_resources','1','/resources/list.shtml',12,'fa-list-alt',0,'菜单管理');
INSERT INTO `cy_resources` VALUES (5,'新增用户',2,'account_add','2','/user/addUI.shtml',3,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addAccount&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (6,'修改用户',2,'account_edit','2','/user/editUI.shtml',4,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editAccount&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (7,'删除用户',2,'account_delete','2','/user/deleteById.shtml',5,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delAccount&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (8,'新增角色',3,'role_add','2','/role/addUI.shtml',8,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addRole&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (9,'修改角色',3,'role_edit','2','/role/editUI.shtml',9,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editRole&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (10,'删除角色',3,'role_delete','2','/role/delete.shtml',10,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delRole&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (11,'分配权限',3,'role_perss','2','/resources/permissions.shtml',11,'无',0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;permissions&quot;&nbsp;class=&quot;btn&nbsp;btn&nbsp;btn-grey&nbsp;marR10&quot;&gt;分配权限&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (12,'登陆信息管理',0,'cy_login','0','cy_login',18,'fa-calendar',0,'登陆信息管理');
INSERT INTO `cy_resources` VALUES (13,'用户登录记录',12,'cy_log_list','1','/userlogin/listUI.shtml',19,NULL,0,'用户登录记录');
INSERT INTO `cy_resources` VALUES (14,'操作日志管理',0,'cy_log','0','cy_log',20,'fa-picture-o',0,'操作日志管理');
INSERT INTO `cy_resources` VALUES (15,'日志查询',14,'cy_log','1','/log/list.shtml',21,NULL,0,NULL);
INSERT INTO `cy_resources` VALUES (16,'新增菜单资源',4,'cy_resources_add','2','/resources/addUI.shtml',13,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (17,'修改菜单资源',4,'cy_resources_edit','2','/resources/editUI.shtml',0,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (18,'删除菜单资源',4,'cy_resources_delete','2','/resources/delete.shtml',15,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (19,'系统监控管理',0,'monitor','0','monitor',16,'fa-tag',0,'系统监控管理');
INSERT INTO `cy_resources` VALUES (20,'实时监控',19,'sysmonitor','1','/monitor/monitor.shtml',17,NULL,0,'实时监控');
INSERT INTO `cy_resources` VALUES (21,'分配权限',2,'permissions','2','/resources/permissions.shtml',6,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;permissions&quot;&nbsp;class=&quot;btn&nbsp;btn&nbsp;btn-grey&nbsp;marR10&quot;&gt;分配权限&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (22,'前台用户管理',0,'custInfo','0','custInfo',22,'fa-calendar',0,'前台用户管理');
INSERT INTO `cy_resources` VALUES (23,'用户信息列表',22,'custInfo_list','1','/cust/list.shtml',23,NULL,0,'用户信息列表');
INSERT INTO `cy_resources` VALUES (24,'新增前台用户',23,'custInfo_add','2','/cust/addUI.shtml',24,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (25,'编辑前台用户',23,'custInfo_edit','2','/cust/editUI.shtml',25,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (26,'删除前台用户',23,'custInfo_delete','2','/cust/delete.shtml',26,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (27,'广告管理',60,'ad','1','/ad/list.shtml',28,NULL,0,'广告管理');
INSERT INTO `cy_resources` VALUES (28,'新增广告',27,'ad_add','2','/ad/addUI.shtml',29,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (29,'数据字典管理',55,'dataDic','1','/dataDic/list.shtml',30,NULL,0,'数据字典管理');
INSERT INTO `cy_resources` VALUES (30,'新增数据字典',29,'dataDic_add','2','/dataDic/addUI.shtml',31,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (31,'修改数据字典',29,'dataDic_edit','2','/dataDic/editUI.shtml',32,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (32,'删除数据字典',29,'dataDic_delete','2','/resources/delete.shtml',33,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (33,'账户信息列表',22,'custAccount_list','1','/custAccount/list.shtml',34,NULL,0,'账户信息列表');
INSERT INTO `cy_resources` VALUES (34,'新增账户信息',33,'custAccount_add','2','/custAccount/addUI.shtml',35,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (35,'编辑账户信息',33,'custAccount_edit','2','/custAccount/editUI.shtml',36,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (36,'删除账户信息',33,'custAccount_delete','2','/custAccount/delete.shtml',37,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (37,'删除广告',27,'ad_delete','2','/ad/delete.shtml',39,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (38,'编辑广告',27,'ad_edit','2','/ad/edit.shtml',38,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (39,'商品管理',0,'item','0','item',40,NULL,0,'商品管理');
INSERT INTO `cy_resources` VALUES (40,'商品分类列表',39,'item_classify','1','/classify/list.shtml',41,NULL,0,'商品分类');
INSERT INTO `cy_resources` VALUES (41,'新增商品分类',40,'item_classify_add','2','/classify/addUI.shtml',42,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (42,'编辑商品分类',40,'item_classify_edit','2','/classify/editUI.shtml',43,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (43,'删除商品分类',40,'item_classify_delete','2','/classify/delete.shtml',44,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (44,'商品活动列表',60,'item_activity','1','/activity/list.shtml',45,NULL,0,'商品活动列表');
INSERT INTO `cy_resources` VALUES (45,'新增商品活动',44,'item_activity_add','2','/activity/addUI.shtml',46,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (46,'编辑商品活动',44,'item_activity_edit','2','/activity/editUI.shtml',47,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (47,'删除商品活动',44,'item_activity_delete','2','/activity/delete.shtml',48,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (48,'订单列表',39,'item_order_list','1','/order/list.shtml',53,NULL,0,'订单列表');
INSERT INTO `cy_resources` VALUES (49,'商品信息列表',39,'item_list','1','/item/list.shtml',49,NULL,0,'商品信息列表');
INSERT INTO `cy_resources` VALUES (50,'新增商品',1,'item_add','2','/item/addUI.shtml',50,NULL,1,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (51,'编辑商品',49,'item_edit','2','/item/editUI.shtml',51,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (52,'删除商品',49,'item_delete','2','/item/delete.shtml',52,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (53,'购物车列表',39,'cart_list','1','/cart/list.shtml',54,NULL,0,'购物车列表');
INSERT INTO `cy_resources` VALUES (54,'图片上传',1,'img_upload','2','/qiniuUpload/upload.shtml',55,NULL,1,'&lt;button?type=&quot;button&quot;?id=&quot;upLoad&quot;?class=&quot;btn?btn-primary?marR10&quot;&gt;上传&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (55,'配置管理',0,'config','0','config',56,NULL,0,'配置管理');
INSERT INTO `cy_resources` VALUES (56,'关键词管理',55,'keyword','1','/keyword/list.shtml',57,NULL,0,'配置管理');
INSERT INTO `cy_resources` VALUES (57,'新增关键词',56,'keyword_add','2','keyword/addUI.shtml',58,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (58,'编辑关键词',56,'keyword_edit','2','keyword/editUI.shtml',59,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (59,'删除关键词',56,'keyword_delete','2','keyword/deleteUI.shtml',60,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (60,'活动管理',0,'activity','0','activity',61,NULL,0,'活动管理');
INSERT INTO `cy_resources` VALUES (61,'搜索记录列表',55,'searchRecord_list','1','/searchRecord/list.shtml',62,NULL,0,'搜索记录列表');
INSERT INTO `cy_resources` VALUES (62,'商品图片管理',39,'item_addpic','2','/item/addPicUI.shtml',63,NULL,1,'商品图片管理');
INSERT INTO `cy_resources` VALUES (63,'商品图片修改',39,'item_updatePic','2','/item/updatePicUI.shtml',64,NULL,1,'商品图片修改');
INSERT INTO `cy_resources` VALUES (64,'健康管理',0,'dietitian','0','dietitian',65,NULL,0,'营养师管理');
INSERT INTO `cy_resources` VALUES (65,'用户健康信息',64,'userHealth_list','1','/userHealth/list.shtml',66,NULL,0,'用户健康信息管理');
INSERT INTO `cy_resources` VALUES (66,'编辑健康信息',64,'userHealth_edit','2','/userHealth/edit.shtml',67,NULL,1,'编辑健康信息');
INSERT INTO `cy_resources` VALUES (67,'健康问答',64,'topic_question_list','1','/topicQuestion/list.shtml',68,NULL,0,'topic_question_list');
INSERT INTO `cy_resources` VALUES (68,'团购列表',39,'groupbuy_list','1','/group/list.shtml',69,NULL,0,'团购列表');
INSERT INTO `cy_resources` VALUES (69,'健康社区问题新增',67,'topic_question_add','2','/topicQuestion/addUI.shtml',70,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (70,'健康社区问题编辑',67,'topic_question_edit','2','/topicQuestion/editUI.shtml',71,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (71,'健康社区问题删除',67,'topic_question_delete','2','/topicQuestion/delete.shtml',72,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (72,'商品包列表',39,'item_package','1','/package/list.shtml',73,NULL,0,'商品包列表');
INSERT INTO `cy_resources` VALUES (73,'新增商品包',72,'item_package_add','2','/package/addUI.html',74,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (74,'编辑商品包',72,'item_package_edit','2','/package/editUI.shtml',75,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (75,'删除商品包',72,'item_package_delete','2','/package/deleteUI.html',76,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (76,'服务申请列表',39,'apply_list','0','/apply/list.shtml',77,NULL,1,'服务申请列表');
INSERT INTO `cy_resources` VALUES (77,'系统消息管理',1,'msg_list','1','/msg/list.shtml',78,NULL,0,'系统消息管理');
INSERT INTO `cy_resources` VALUES (78,'新增系统消息',77,'msg_add','2','/msg/addUI.shtml',79,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (79,'编辑系统消息',77,'msg_edit','2','/msg/editUI.shtml',80,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (80,'删除系统消息',77,'msg_delete','2','/msg/delete.shtml',81,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (81,'推广统计',0,'rider_manager','0','rider_manager',82,NULL,0,'骑手管理');
INSERT INTO `cy_resources` VALUES (82,'站点信息管理',81,'station_list','1','/station/list.shtml',83,NULL,0,'站点信息列表');
INSERT INTO `cy_resources` VALUES (83,'新增站点信息',82,'station_add','2','/station/addUI.shtml',84,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (84,'编辑站点信息',82,'station_edit','2','/station/editUI.shtml',85,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (85,'删除站点信息',82,'station_delete','2','/station/delete.shtml',86,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (86,'骑手信息',81,'rider_list','1','/rider/list.shtml',87,NULL,0,'骑手信息');
INSERT INTO `cy_resources` VALUES (87,'新增骑手信息',86,'rider_add','2','/rider/addUI.shtml',88,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (88,'编辑骑手信息',86,'rider_edit','2','/rider/editUI.shtml',89,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (89,'删除骑手信息',86,'rider_delete','2','/rider/delete.shtml',90,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (90,'产品信息列表',39,'item_product_list','1','/itemProduct/list.shtml',91,NULL,0,'产品信息列表');
INSERT INTO `cy_resources` VALUES (91,'新增产品',90,'item_product_add','2','/itemProduct/addUI.shtml',92,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (92,'编辑产品',90,'item_product_edit','2','/itemProduct/editUI.shtml',93,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (93,'站点分类',81,'station_classify_list','1','/stationClassify/list.shtml',94,NULL,0,'站点分类');
INSERT INTO `cy_resources` VALUES (94,'新增站点分类',93,'station_classify_add','2','/stationClassify/addUI.shtml',95,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (95,'编辑站点分类',93,'station_classify_edit','2','/stationClassify/editUI.shtml',96,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (96,'删除站点分类',93,'station_classify_delete','2','/stationClassify/delete.shtml',97,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (97,'头条管理',55,'headline_list','1','/headline/list.shtml',98,NULL,0,'头条管理');
INSERT INTO `cy_resources` VALUES (98,'新增头条',97,'headline_add ','2','/headline/addUI.shtml',99,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (99,'编辑头条',97,'headline_edit','2','/headline/editUI.shtml',100,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (100,'删除头条',97,'headline_delete','2','/headline/delete.shtml',101,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (101,'经典案例管理',60,'seoCases','1','/seoCases/list.shtml',102,NULL,0,'经典案例管理');
INSERT INTO `cy_resources` VALUES (102,'新增经典案例',101,'seoCases_add','2','/seoCases/addUI.shtml',103,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (103,'编辑经典案例',101,'seoCases_edit','2','/seoCases/editUI.shtml',104,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (104,'删除经典案例',101,'seoCases_delete','2','/seoCases/delete.shtml',105,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (105,'系统菜单备份',4,'resources_backup','2','/resources/backup.shtml',106,NULL,1,'&lt;button&nbsp;id=&quot;backup&quot;&nbsp;type=&quot;button&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;备份&lt;/button&gt;');
INSERT INTO `cy_resources` VALUES (106,'系统菜单还原',4,'resources_recover','2','/resources/recover.shtml',107,NULL,1,'&lt;button&nbsp;id=&quot;recover&quot;&nbsp;type=&quot;button&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;还原&lt;/button&gt;');
/*!40000 ALTER TABLE `cy_resources` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

