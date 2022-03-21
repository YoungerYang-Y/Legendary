-- 官方SQL地址： https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql

-- oauth_dev.oauth_access_token definition

CREATE TABLE `oauth_access_token`
(
    `token_id`          varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'access_token进过MD5加密后存储',
    `token`             blob COMMENT 'OAuth2AccessToken对象序列化后的二进制数据',
    `authentication_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'username（如果有），client_id，scope通过MD5加密生成的',
    `user_name`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名称，若没有则等于client_id',
    `client_id`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户端唯一标识',
    `authentication`    blob COMMENT 'OAuth2Authentication对象序列化后的二进制数据',
    `refresh_token`     varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'refresh_token的值MD5加密后存储',
    `create_time`       datetime                                                DEFAULT NULL,
    PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- oauth_dev.oauth_approvals definition

CREATE TABLE `oauth_approvals`
(
    `userId`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `clientId`       varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `scope`          varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `status`         varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL,
    `expiresAt`      timestamp NULL DEFAULT NULL,
    `lastModifiedAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- oauth_dev.oauth_client_details definition

CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端唯一标识',
    `resource_ids`            varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '资源的编号，相当于要访问的资源服务器编号',
    `client_secret`           varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '客户端安全码，此处不能是明文，需要加密',
    `scope`                   varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '客户端授权范围,可选值包括read,write,trust;',
    `authorized_grant_types`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '客户端授权类型,可选值包括authorization_code,password,refresh_token,implicit,client_credentials',
    `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '服务器回调地址',
    `authorities`             varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '客户端所拥有的Spring Security的权限值',
    `access_token_validity`   int                                                      DEFAULT NULL COMMENT 'access_token的有效时间值(单位:秒)',
    `refresh_token_validity`  int                                                      DEFAULT NULL,
    `additional_information`  varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预留的字段,但若设置值,必须是JSON格式的数据',
    `autoapprove`             varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '设置用户是否自动Approval操作, 默认值为 ‘false’, 可选值包括 ‘true’,‘false’, ‘read’,‘write’. 该字段只适用于grant_type="authorization_code"的情况',
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- oauth_dev.oauth_client_token definition

CREATE TABLE `oauth_client_token`
(
    `token_id`          varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '从服务端获取到的access_token值',
    `token`             blob COMMENT 'OAuth2Token对象序列化后的二进制数据',
    `authentication_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'username（如果有），client_id，scope通过MD5加密生成的',
    `user_name`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录时的用户',
    `client_id`         varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `create_time`       datetime                                                DEFAULT NULL,
    PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='在客户端系统中存储从服务端获取的token数据';


-- oauth_dev.oauth_code definition

CREATE TABLE `oauth_code`
(
    `code`           varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '服务端系统生成的code的值',
    `authentication` blob COMMENT 'AuthenticationRequestHolder对象序列化后的二进制数据',
    `create_time`    datetime                                                DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='主要操作oauth_code表的对象是JdbcAuthorizationCodeServices.java.';


-- oauth_dev.oauth_refresh_token definition

CREATE TABLE `oauth_refresh_token`
(
    `token_id`       varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'refresh_token值MD5加密后存储',
    `token`          blob COMMENT 'OAuth2RefreshToken对象序列化后二进制数据',
    `authentication` blob COMMENT 'OAuth2Authentication对象序列化后存储',
    `create_time`    datetime                                                DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;