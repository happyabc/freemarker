/*
Navicat MySQL Data Transfer

Source Server         : localhost(123456)
Source Server Version : 100206
Source Host           : localhost:3306
Source Database       : transfer

Target Server Type    : MYSQL
Target Server Version : 100206
File Encoding         : 65001

Date: 2017-07-25 14:06:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('1', '不明真相的美国人被UFO惊呆了 其实是长征7号', '据美国《洛杉矶时报》报道，当地时间周三晚(北京时间周四)，在美国中西部的犹他州、内华达州、加利福利亚州，数千人被划过夜空的神秘火球吓到');
INSERT INTO `tb_article` VALUES ('2', '法国巴黎圣母院为教堂恐袭案遇害神父举行大弥撒', '而据美国战略司令部证实，其实这是中国长征七号火箭重新进入大气层，刚好经过加利福利亚附近。');
INSERT INTO `tb_article` VALUES ('3', '日东京知事候选人小池百合子回击石原：浓妆可以', '然而昨晚的美国人民可不明真相，有些人甚至怀疑这些火球是飞机解体，还有些人猜测是流星雨。');
INSERT INTO `tb_article` VALUES ('4', '日资慰安妇基金在首尔成立 韩国示威者闯入抗议', '美国战略司令部发言人表示，到目前为止还没有任何受损报告，他说类似物体通常在大气中就会消失，这也解释了为何出现一道道光痕，这一切都并未造成什么威胁。');
INSERT INTO `tb_article` VALUES ('5', '中日关系正处十字路口日应寻求减少与华冲突', '中国长征七号火箭6月25日在海南文昌航天发射中心首次发射，并成功升空进入轨道。有学者指出长征七号第二级火箭一直在地球低轨运行，一个月后重新进入大气层。');
SET FOREIGN_KEY_CHECKS=1;
