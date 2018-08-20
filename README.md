## spring boot项目框架

#### 简介

  基于springboot+ mybatis搭建项目框架

#### 建表语句

```
CREATE TABLE user_info
(
	id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(64),
    age int,
    gmt_create DATETIME,
    gmt_modified DATETIME
);

INSERT user_info(name, age, gmt_create, gmt_modified) values("zhangsan", 16, "2016-11-30 10:46:50", "2016-11-30 10:47:50");
```

#### 部署方式

  1. 建表,并修改数据库连接和数据库用户名密码
  2. mvn eclipse:clean eclipse:eclipse
  3. mvn clean install -Dmaven.test.skip=true -U
  4. 启动tomcat直接运行(或直接运行MainApp.java启动)
  5. 访问: http://127.0.0.1:8080/login?userName=zhangsan
  6. 访问: http://127.0.0.1:8080/swagger-ui.html

