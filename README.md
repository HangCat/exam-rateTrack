项目简介

# 功能介绍：
## 系统主要包含两个页面

此页面可以直接访问，也有邮件会直接发送此模版，收到邮件所渲染出来的页面与此页面一致
http://localhost:8080/email-page/123
![img.png](img.png)


此页面是dashboard页面，主要记录了上面的页面访问记录，包括了页面的访问情况，页面中按钮的点击情况
http://localhost:8080/dashboard
![img_1.png](img_1.png)

## 系统定时任务
系统初始化了一些用户信息

1. 定时发邮件或短信
2. 定时判断用户是否访问了发出去的链接，若没有访问，则把用户推到发送消息的定时任务中去

# 系统启动流程
1. 打开IDE，运行 com.finpoints.framework.ratetrack.RateTrackApplication.main
2. 在项目目录下 mvn clean package -Dmaven.test.skip=true打包，在target目录下，找到[rateTrack-0.0.1-SNAPSHOT.jar]文件，在文件目录下执行Java -jar rateTrack-0.0.1-SNAPSHOT.jar命令启动项目
## 启动后验证
分别访问页面：
http://localhost:8080/dashboard
http://localhost:8080/email-page/123

# 系统技术架构介绍
系统基于springboot来实现
前端页面通过 thymeleaf 模版引擎来加载
数据采用H2内存数据库来保存
ORM框架采用的spring data JPA

实体对象UML类图关系如下
![img_2.png](img_2.png)

