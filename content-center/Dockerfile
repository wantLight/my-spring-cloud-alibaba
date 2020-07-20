#基础镜像，如果本地没有，会从远程仓库拉取。
FROM java
# 镜像制作人
MAINTAINER xyzzg/1364932323@qq.com
# 将当前目录下的mall-tiny-docker-file.jar包复制到docker容器的/目录下springboot创建dockerfile
#ADD szsti-word.jar /szsti-word.jar
# 在容器中创建挂载点
#VOLUME ["/tmp"]
# 声明了容器应该打开的端口并没有实际上将它打开
#EXPOSE 8079
# 定义参数
# ARG JAR_FILE
# 拷贝本地文件到镜像中
# COPY ${JAR_FILE} szsti-word.jar
# 将jar包添加到容器中并更名为app.jar
#ADD *.jar szsti-word.jar
# 指定docker容器启动时运行jar包
#ENTRYPOINT ["java", "-jar","/szsti-word.jar"]


#工作目录
WORKDIR /app/

#在容器中创建挂载点，可以多个
VOLUME ["/tmp"]

#声明了容器应该打开的端口并没有实际上将它打开
EXPOSE 8079

#定义参数
ARG JAR_FILE

#拷贝本地文件到镜像中
COPY ${JAR_FILE} szsti-word.jar

ENTRYPOINT ["java", "-jar", "szsti-word.jar"]
