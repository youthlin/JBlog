/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 100113
Source Host           : localhost:3306
Source Database       : jblog

Target Server Type    : MYSQL
Target Server Version : 100113
File Encoding         : 65001

Date: 2016-09-14 13:58:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`post_count`  bigint(20) NULL DEFAULT NULL ,
`status`  tinyint(4) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Records of t_category
-- ----------------------------
BEGIN;
INSERT INTO `t_category` VALUES ('1', '默认分类', '7', '0'), ('2', '默认相册', '0', '1'), ('3', '分类一', '2', '0'), ('4', '分类二', '0', '2'), ('5', '分类三', '0', '0');
COMMIT;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`content`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`deleted`  tinyint(1) NULL DEFAULT NULL ,
`publish_date`  datetime NULL DEFAULT NULL ,
`author_id`  bigint(20) NULL DEFAULT NULL ,
`post_id`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`author_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`post_id`) REFERENCES `t_post` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK9rnsdkhm16nmouekdcj8fokcj` (`author_id`) USING BTREE ,
INDEX `FKsa3hl9a6mu30dct1jnn2bwvem` (`post_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=45

;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
BEGIN;
INSERT INTO `t_comment` VALUES ('1', '添加一则评论', '0', '2016-09-14 00:12:15', '1', '1'), ('2', '添加评论\r\n<strong>粗体字</strong>', '0', '2016-09-14 00:48:44', '1', '6'), ('3', '<u>发表评论</u>', '0', '2016-09-14 00:50:14', '1', '6'), ('4', '<i>comment</i>', '0', '2016-09-14 00:53:26', '1', '5'), ('5', 'comment again', '0', '2016-09-14 00:57:50', '1', '5'), ('6', 'comment 3', '0', '2016-09-14 00:59:51', '1', '5'), ('7', 'comment 4', '0', '2016-09-14 01:00:36', '1', '5'), ('8', 'comment 5', '0', '2016-09-14 08:08:44', '1', '5'), ('9', 'comment 6', '0', '2016-09-14 08:12:32', '1', '5'), ('10', 'comment 7', '0', '2016-09-14 08:19:46', '1', '5'), ('11', 'comment 8', '0', '2016-09-14 08:32:46', '1', '5'), ('12', 'comment 9', '0', '2016-09-14 08:34:48', '1', '5'), ('13', 'comment 10', '0', '2016-09-14 08:37:07', '1', '5'), ('14', 'comment 11', '0', '2016-09-14 08:38:19', '1', '5'), ('15', 'comment 12', '0', '2016-09-14 08:41:44', '1', '5'), ('16', 'comment 13', '0', '2016-09-14 08:42:36', '1', '5'), ('17', 'comment 14', '0', '2016-09-14 08:44:48', '1', '5'), ('18', 'comment 15', '0', '2016-09-14 08:48:42', '1', '5'), ('19', 'comment 16', '0', '2016-09-14 08:52:49', '1', '5'), ('20', 'comment 17', '0', '2016-09-14 08:53:03', '1', '5'), ('21', 'comment 18', '0', '2016-09-14 08:53:12', '1', '5'), ('22', 'comment 19', '0', '2016-09-14 08:57:21', '1', '5'), ('23', 'comment 20', '0', '2016-09-14 09:05:32', '1', '5'), ('24', '21', '0', '2016-09-14 09:06:38', '1', '5'), ('25', 'add comment 添加评论', '0', '2016-09-14 09:23:37', '1', '4'), ('26', '文章id=2的评论', '0', '2016-09-14 09:25:58', '1', '2'), ('27', 'add', '0', '2016-09-14 09:27:23', '1', '1'), ('28', '222222222', '0', '2016-09-14 09:28:04', '1', '2'), ('30', '333', '0', '2016-09-14 09:34:20', '1', '2'), ('31', '新评论', '0', '2016-09-14 09:35:13', '1', '7'), ('32', 'abc发表评论', '0', '2016-09-14 09:40:05', '2', '7'), ('33', 'abcabcabc', '0', '2016-09-14 09:41:40', '2', '7'), ('34', 'aaaaaaaaaa', '0', '2016-09-14 09:48:35', '2', '7'), ('35', 'bbbbbbb', '0', '2016-09-14 09:50:33', '2', '7'), ('36', 'ccccccc', '0', '2016-09-14 09:52:18', '2', '7'), ('37', 'ddd', '0', '2016-09-14 09:54:01', '2', '7'), ('38', 'eeee', '0', '2016-09-14 09:55:45', '2', '7'), ('39', 'ffff', '0', '2016-09-14 09:58:05', '2', '7'), ('40', 'ggg', '0', '2016-09-14 09:58:39', '2', '7'), ('41', 'comment', '0', '2016-09-14 10:08:20', '1', '9'), ('42', 'another commnt', '0', '2016-09-14 10:08:55', '2', '9'), ('43', '发表评论', '0', '2016-09-14 11:27:12', '1', '7'), ('44', '111111', '0', '2016-09-14 13:27:46', '1', '1');
COMMIT;

-- ----------------------------
-- Table structure for t_links
-- ----------------------------
DROP TABLE IF EXISTS `t_links`;
CREATE TABLE `t_links` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`priority`  int(11) NULL DEFAULT NULL ,
`status`  tinyint(4) NULL DEFAULT NULL ,
`url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of t_links
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_post
-- ----------------------------
DROP TABLE IF EXISTS `t_post`;
CREATE TABLE `t_post` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`allow_comment`  tinyint(1) NULL DEFAULT NULL ,
`comment_count`  bigint(20) NULL DEFAULT NULL ,
`content`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`hint`  bigint(20) NULL DEFAULT NULL ,
`publish_date`  datetime NULL DEFAULT NULL ,
`status`  tinyint(4) NULL DEFAULT NULL ,
`title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`type`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`author_id`  bigint(20) NULL DEFAULT NULL ,
`category_id`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`category_id`) REFERENCES `t_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`author_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FKhiebhl0u5emgafdi049p5kcli` (`author_id`) USING BTREE ,
INDEX `FKftr7uv1b843enfjj894afru0f` (`category_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=11

;

-- ----------------------------
-- Records of t_post
-- ----------------------------
BEGIN;
INSERT INTO `t_post` VALUES ('1', '1', '3', 'JBlog是一个Java EE写的个人博客系统，主要功能有\r\n<ol>\r\n<li>用户登录注册</li>\r\n<li>分类管理</li>\r\n<li>文章管理</li>\r\n<li>评论管理</li>\r\n<li>通知管理</li>\r\n<li>……以及其他功能</li>\r\n</ol>', '10', '2016-09-14 00:04:14', '0', 'JBlog博客系统基本完成', 'text', '1', '1'), ('2', '1', '3', '在后台分类管理中新增一个分类，然后再该分类下新增一篇文章。\r\n<blockquote>\r\n文章内容支持<code>HTML</code>语法标记。\r\n</blockquote>\r\n<img src=\'http://gravatar.duoshuo.com/avatar/f72aae5435e6cee718a6870ccd054d36?s=44&d=mm&r=g\'/>\r\n上面是 img 标签~', '10', '2016-09-14 00:18:48', '0', '新分类的文章', 'text', '1', '3'), ('3', '0', '0', '本文设置为不接受评论。', '7', '2016-09-14 00:19:51', '0', '不可评论的文章', 'text', '1', '3'), ('4', '1', '1', '多谢两篇测试分页。', '7', '2016-09-14 00:33:26', '0', '第四篇文章', 'text', '1', '1'), ('5', '1', '21', '内容内容\r\n由于 IDEA 会把生成的包重命名，比如这里的 EJBDemo 工程生成的是 EJBDemo_ejb_exploded.rar. 为了不出错并和教材兼容，方便起见，我们还是重命名一下改回来。打开 Project Structure(Ctrl + Alt + Shift + S), 定位到 Artifacts 把 Output Directory 从 ...\\EJBDemo_ejb_exploded.rar 改为 ...\\EJBDemo 并点击 OK. 如图四所示。\r\n\r\n//来源: http://youthlin.com/20161265.html', '72', '2016-09-14 00:39:52', '0', '第五篇文章', 'text', '1', '1'), ('6', '1', '1', '建 EJB 工程 可能是由于现在 EJB 已经落伍了，网上都找不到最近的文章，我们教材也是以 Eclipse 为例演示的。但是作为一个已经放弃 Eclipse 转投 IDEA 的社会主义好青年，只好亲自试验一番啦。 开发环境 JDK8 + WildFly10 + IDEA2016.1.2 JDK多个是可以共存的，但 JAVA_HOME 变量只能设置一个。WildFly 是压缩包，和 JBoss7.1 也是可以共存的，运行哪个就是哪个。IDEA 作为最智能的 Java IDE 就不解释了。 新建EJB 工程 如图一，打开 IDEA, 新建工程，左侧栏选择 Java Enterprise, 右侧找到 EJB: Enterprise Java Beans, 同时设置好 JDK 版本，应用服务器 WildFly 注意下方勾选 Create ejb-jar.xml 并且 Libraries 选择 Download 这样 IDEA 会自动下载所需的 javax.ejb-api.jar 并且设置好 classpath. 01新建工程 下一步填好保存位置，点击完成，等待下载库文件完毕，IDEA 会自动打开工程。这里我选择名称为 EJBDemo. 如图二。 02工程目录 新建会话 Bean 以实现远程接口的无状态会话 Bean 为例，我们新建一个 Stateless Session Bean. 创建远程接口 如图三所示，新建一个 SayHello 接口。 03新建接口 并修改代码如下： 无状态会话 Bean 接口Java 1 2 3 4 5 6 7 8 9 10 11 12 package com.youthlin.javaee.ejb.stateless;   import javax.ejb.Remote;   /** * Created by lin on 2016-05-19-019. * 无状态会话 Bean 接口 */ @Remote public interface SayHello {     String sayHello(String name); } 注意使用 @Remote 标注，表明这是一个远程接口，我们在客户端使用 EJB 需要这个接口文件，因此远程的意思是说相对于客户端来说，他是远程的。 创建实现类 新建一个 SayHelloImpl 实现类。代码如下： 接口实现类Java 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 package com.youthlin.javaee.ejb.stateless.impl;   import com.youthlin.javaee.ejb.stateless.SayHello;   import javax.ejb.Stateless;   /** * Created by lin on 2016-05-19-019. * 接口实现类 */ @Stateless public class SayHelloImpl implements SayHello {     @Override     public String sayHello(String name) {         return \"Hello, \" + name;     } } 标注 @Stateless 表明这是一个无状态的会话 Bean. 这个实现类对客户端是透明的，客户端仅需要拥有刚刚建立的接口即可，不需要关心具体实现。 部署 EJB 由于 IDEA 会把生成的包重命名，比如这里的 EJBDemo 工程生成的是 EJBDemo_ejb_exploded.rar. 为了不出错并和教材兼容，方便起见，我们还是重命名一下改回来。打开 Project Structure(Ctrl + Alt + Shift + S), 定位到 Artifacts 把 Output Directory 从 ...\\EJBDemo_ejb_exploded.rar 改为 ...\\EJBDemo 并点击 OK. 如图四所示。 04部署设置 之后就可以点击工程右上角的 Run 或 Debug 按钮，以便把工程部署到 Jboss10 上了。不出意外的话，肯定就是成功啦，如图五所示。 05部署成功 如果想在 JBoss 管理后台查看或移除部署的 EJB 可以打开 http://localhost:9990/ 不过你首先得添加一个管理用户，运行 %JBoss_Home%/bin/add-user.bat 按提示操作即可。 客户端调用 因为我们用的是实现远程接口的，因此客户端可以在任意能连接我们 Jboss 服务器的地方工作。比如另一个工程的普通 Java 程序，或 Web 应用。这里为了方便起见，我只是新建一个 Module 测试一下。 复制远程接口文件 新建 Module 后，把远程接口文件拷贝过来。直接在原地 Ctrl + C, 然后再新的 Module 的 src 文件夹上 Ctrl + V 即可，选择保持原有路径。不过因为新建的 Module 是普通 Java 工程，我们发现 @Remote 报错。 在新建的 Module 里新建 lib 文件夹，拷贝 JBoss_Home%/bin/client/jboss-client.jar 到 lib 文件夹中。并打开 Project Structure 设置依赖添加这个 lib 文件夹，如图六。\r\n\r\n//来源: http://youthlin.com/20161265.html', '7', '2016-09-14 00:42:52', '0', '第六篇文章', 'text', '1', '1'), ('7', '1', '11', '新内容', '30', '2016-09-14 09:34:47', '0', '新文章', 'text', '1', '1'), ('8', '1', '0', '<p>校内各相关单位：</p>\r\n             \r\n<p>&emsp;&emsp;为庆祝学校建校70周年，传承学校历史，展示我校在校园建设、人才培养、科技创新和实验室建设等方面所取得的成就，学校决定在校庆期间，面向广大返校校友、嘉宾开展“校庆开放日”活动。现将有关事项通知如下：</p>\r\n<p>&emsp;&emsp;一、开放时间</p>\r\n<p>&emsp;&emsp;9月15--17日，每日9--16点。</p>\r\n<p>&emsp;&emsp;二、开放范围</p>\r\n<p>&emsp;&emsp;全校范围内图书馆、校史馆、科学馆、博物馆、各类综合展室、各实验室、技术研究中心等。</p>\r\n<p>&emsp;&emsp;三、有关要求</p>\r\n<p>&emsp;&emsp;1.时值中秋期间，各单位做好人员安排工作，确保专人值守。</p>\r\n<p>&emsp;&emsp;2.各单位要加强对相关工作人员的培训，确保热情周到。</p>\r\n<p>&emsp;&emsp;3.各单位要做好开放日期间开放地点的卫生保洁、设备调试等工作，确保舒适整洁。</p>\r\n<p>&emsp;&emsp;4.各单位要加强安全意识，做好防火、防盗等工作，确保校园安全。</p>\r\n<p>&emsp;&emsp;联系人：胡芳&emsp;李艳涛&emsp;&emsp; 电话：81334418</p>\r\n<p>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</p>\r\n<p>&nbsp;</p>\r\n<p>&nbsp;</p>\r\n<p>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;校庆筹备工作办公室</p>\r\n<p>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 2016年9月13日</p>\r\n<p></p>', '5', '2016-09-14 10:45:33', '0', '分类二的文章', 'text', '1', '1'), ('9', '1', '2', '<p style=\"text-align: justify;\">The HornetQ codebase was donated to the Apache ActiveMQ project, and the HornetQ community joined to build a next-generation messaging broker. ActiveMQ Artemis includes many new features, and also retains protocol compatibility with the HornetQ broker. WildFly 10 includes this new exciting project as its JMS broker, and due to the protocol compatibility, it fully replaces the HornetQ project. So as part of this simple demo we will see how we can connect to the ActiveMQ broker present inside the WildFLy10 via Standalone java based client code using standard JMS APIs.</p>\r\n<p style=\"text-align: justify;\">Find more about “Apache ActiveMQ Artemis” in <a href=\"http://hornetq.blogspot.in/2015/06/hornetq-apache-donation-and-apache.html\" target=\"_blank\">http://hornetq.blogspot.in/2015/06/hornetq-apache-donation-and-apache.html</a><br />\r\nAND<br />\r\n<a href=\"http://activemq.apache.org/artemis/\" target=\"_blank\">http://activemq.apache.org/artemis/</a></p>\r\n<p style=\"text-align: justify;\">This article is basically clone of <a href=\"http://middlewaremagic.com/jboss/?p=2724\" target=\"_blank\">http://middlewaremagic.com/jboss/?p=2724</a> with just few WildFly 10.x specific changes related to WildFly JMS configuration, However the client code will remain same.</p>\r\n<p style=\"text-align: justify;\"><strong>In this example we will be learning the following things:</strong></p>\r\n<p>1. How to create a simple JMS Queue in WildFly 10.x<br />\r\n2. How to create a user in ApplicationRealm and assign it to &#8220;guest&#8221; role.<br />\r\n3. How to create the InitialContext on the standalone client side.<br />\r\n4. What all jars are needed on the client side in order to lookup the JNDI resources deployed on WildFly.<br />\r\n5. Some common issues which you might encounter while running sample java code are explained in the other article: <a href=\"http://middlewaremagic.com/jboss/?p=2724\" target=\"_blank\">http://middlewaremagic.com/jboss/?p=2724</a></p>', '6', '2016-09-14 10:07:50', '2', 'New Post', 'text', '1', '4'), ('10', '1', '0', '内容', '2', '2016-09-14 13:29:26', '0', '草稿', 'text', '1', '1');
COMMIT;

-- ----------------------------
-- Table structure for t_settings
-- ----------------------------
DROP TABLE IF EXISTS `t_settings`;
CREATE TABLE `t_settings` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`s_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`s_value`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of t_settings
-- ----------------------------
BEGIN;
INSERT INTO `t_settings` VALUES ('1', 'comment_count_per_page', '5'), ('2', 'text_count_per_page', '5');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`email`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`gravatar`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`s_key`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`passwd`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`status`  tinyint(4) NULL DEFAULT NULL ,
`username`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `UK_jhib4legehrm4yscx9t3lirqi` (`username`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('1', 'root@youthlin.com', null, null, '5671a7239a20ab14738c49ffca6829dc', '0', 'lin'), ('2', 'root@youthlin.com', null, null, 'a46e2084969cdc95e584de33de915cf2', '1', 'abc');
COMMIT;

-- ----------------------------
-- Table structure for t_user_t_post
-- ----------------------------
DROP TABLE IF EXISTS `t_user_t_post`;
CREATE TABLE `t_user_t_post` (
`likedUser_id`  bigint(20) NOT NULL ,
`likedPost_id`  bigint(20) NOT NULL ,
FOREIGN KEY (`likedUser_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`likedPost_id`) REFERENCES `t_post` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FKbxlx93umxam2796ag179ad610` (`likedPost_id`) USING BTREE ,
INDEX `FK2xlw3dtlev6xl9yy4ybvww9gw` (`likedUser_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of t_user_t_post
-- ----------------------------
BEGIN;
INSERT INTO `t_user_t_post` VALUES ('2', '8'), ('1', '7'), ('1', '10');
COMMIT;

-- ----------------------------
-- Auto increment value for t_category
-- ----------------------------
ALTER TABLE `t_category` AUTO_INCREMENT=6;

-- ----------------------------
-- Auto increment value for t_comment
-- ----------------------------
ALTER TABLE `t_comment` AUTO_INCREMENT=45;

-- ----------------------------
-- Auto increment value for t_links
-- ----------------------------
ALTER TABLE `t_links` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for t_post
-- ----------------------------
ALTER TABLE `t_post` AUTO_INCREMENT=11;

-- ----------------------------
-- Auto increment value for t_settings
-- ----------------------------
ALTER TABLE `t_settings` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for t_user
-- ----------------------------
ALTER TABLE `t_user` AUTO_INCREMENT=3;
SET FOREIGN_KEY_CHECKS=1;
