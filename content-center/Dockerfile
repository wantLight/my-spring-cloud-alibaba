# 易于版本化管理，Dockerfile 本身是一个文本文件，方便存放在代码仓库做版本管理
# 过程可追溯，Dockerfile 的每一行指令代表一个镜像层，根据 Dockerfile 的内容即可很明确地查看镜像的完整构建过程
# 屏蔽构建环境异构，使用 Dockerfile 构建镜像无须考虑构建环境

# 最小化镜像层数 RUN yum install -y make net-tools

# 我想在官方的 CentOS 镜像下安装一些软件，一个建议的 Dockerfile 指令如下：
# FROM centos:7
  #RUN yum install -y automake \
  #                   curl \
  #                   python \
  #                   vim

# CMD和 ENTRYPOINT指令都是容器运行的命令入口


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
# 使用 WORKDIR 来指定容器的工作路径
WORKDIR /app/

#在容器中创建挂载点，可以多个
VOLUME ["/tmp"]

#声明了容器应该打开的端口并没有实际上将它打开
EXPOSE 8079

#定义参数
ARG JAR_FILE

#拷贝本地文件到镜像中
# COPY指令更加透明，仅支持本地文件向容器拷贝
COPY ${JAR_FILE} szsti-word.jar
# CMD/ENTRYPOINT后面跟 json 数组
# ENTRYPOINT：镜像只执行单一的具体程序，并且不希望用户在执行
ENTRYPOINT ["java", "-jar", "szsti-word.jar"]
