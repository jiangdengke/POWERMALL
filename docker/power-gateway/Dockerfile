# 基础镜像
FROM openjdk:8-jdk-alpine

# 复制主机文件至镜像
COPY ./app.jar /mall/app.jar

# 容器启动执行命令
ENTRYPOINT ["java","-jar","/mall/app.jar"]

# 声明运行时容器提供服务端口，仅仅是声明
EXPOSE 8888
