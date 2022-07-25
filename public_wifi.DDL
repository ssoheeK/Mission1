-- public_wifi.location_history definition

CREATE TABLE `location_history` (
    `id` int NOT NULL AUTO_INCREMENT,
    `x` decimal(10,7) NOT NULL COMMENT 'X좌표',
    `y` decimal(10,7) NOT NULL COMMENT 'Y좌표',
    `inquiry_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '조회일자',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='위치 히스토리 목록';


-- public_wifi.wifi_info definition

CREATE TABLE `wifi_info` (
     `id` int NOT NULL AUTO_INCREMENT,
     `mgr_no` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '관리번호',
     `wrdofc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '자치구',
     `main_nm` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '와이파이명',
     `adres1` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '도로명주소',
     `adres2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '상세주소',
     `instl_floor` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '설치위치(층)',
     `instl_ty` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '설치유형',
     `instl_mby` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '설치기관',
     `svc_se` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '서비스구분',
     `cmcwr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '망종류',
     `cnstc_year` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '설치년도',
     `inout_door` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '실내외구분',
     `remars3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'wifi접속환경',
     `lat` decimal(10,7) NOT NULL COMMENT 'X좌표',
     `lnt` decimal(10,7) NOT NULL COMMENT 'Y좌표',
     `work_dttm` datetime(1) DEFAULT NULL COMMENT '작업일자',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17796 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='공공 와이파이 정보';