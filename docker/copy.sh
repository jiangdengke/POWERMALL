#!/bin/bash

# 设置工作目录
DIR="/home/jdk/workspace/power-mall/POWERMALL"
# 如果需要使用 Windows 路径,可以取消下面一行的注释并修改为适当的路径
# DIR="/mnt/c/Users/jiangdk/Desktop/workspace/code/power-mall/POWERMALL"

# 复制各服务的 app.jar 文件到 Docker 目录
cp "$DIR/mall-cms/cms-service/target/app.jar" "$DIR/docker/cms-service/app.jar"
cp "$DIR/mall-oms/oms-service/target/app.jar" "$DIR/docker/oms-service/app.jar"
cp "$DIR/mall-pms/pms-service/target/app.jar" "$DIR/docker/pms-service/app.jar"
cp "$DIR/mall-search/search-service/target/app.jar" "$DIR/docker/search-service/app.jar"
cp "$DIR/mall-ums/ums-service/target/app.jar" "$DIR/docker/ums-service/app.jar"
cp "$DIR/power-gateway/target/app.jar" "$DIR/docker/power-gateway/app.jar"
cp "$DIR/power-oss/oss-service/target/app.jar" "$DIR/docker/oss-service/app.jar"

echo "所有 app.jar 文件已成功复制到 Docker 目录。"