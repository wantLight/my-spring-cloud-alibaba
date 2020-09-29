grep、find、sed、awk、top、ps、netstat 

## grep (查找文件里符合条件的字符串)

系统报警显示了时间，但是日志文件太大无法直接 cat 查看。
grep -n '2019-10-24 00:01:11' *.log

ps命令将某个进程显示出来
grep命令是查找
中间的|是管道命令 是指ps命令与grep同时执行
ps -ef|grep xx

## find （在指定目录下查找文件）

将目前目录及其子目录下所有最近 20 天内更新过的文件列出
find . -ctime -20

查找以xx开头的文件
find /devl/mysql/data_test -name 'hxzysjzl*'

## sed （依照脚本的指令来处理、编辑文本文件）

将 /etc/passwd 的内容列出并且列印行号，同时将第 2~5 行删除
nl /etc/passwd | sed '2,5d'

数据的搜寻并替换
sed 's/要被取代的字串/新的字串/g'

## awk (文本分析/格式化工具)

每行按空格或TAB分割，输出文本中的1、4项
awk '{print $1,$4}' log.txt

grep 更适合单纯的查找或匹配文本
sed 更适合编辑匹配到的文本
awk 更适合格式化文本，对文本进行较复杂格式处理

## netstat (显示网络状态)

查看当前所有tcp端口
netstat -ntlp

1.查找请求数前20个IP（常用于查找攻来源）：
netstat -anlp|grep 80|grep tcp|awk '{print $5}'|awk -F: '{print $1}'|sort|uniq -c|sort -nr|head -n20
netstat -ant |awk '/:80/{split($5,ip,”:”);++A[ip[1]]}END{for(i in A) print A[i],i}' |sort -rn|head -n20
2.用tcpdump嗅探80端口的访问看看谁最高
tcpdump -i eth0 -tnn dst port 80 -c 1000 | awk -F”.” '{print $1″.”$2″.”$3″.”$4}' | sort | uniq -c | sort -nr |head -20
3.查找较多time_wait连接
netstat -n|grep TIME_WAIT|awk '{print $5}'|sort|uniq -c|sort -rn|head -n20
4.找查较多的SYN连接
netstat -an | grep SYN | awk '{print $5}' | awk -F: '{print $1}' | sort | uniq -c | sort -nr | more
5.根据端口列进程
netstat -ntlp | grep 80 | awk '{print $7}' | cut -d/ -f1


## Spring的启动流程
1. web应用部署在web容器中，web容器提供其一个全局的上下文环境，这个上下文就是ServletContext，其为后面的spring IoC容器提供宿主环境；
2. web容器启动时，会触发容器初始化事件。创建ContextLoaderListener,回调contextInitialized方法
3. 创建并初始化spring容器。
4. 创建WebApplicationContext对象（解析web.xml或者原生Servlet3.0的注解）。
5. refresh容器，读取XML配置，创建beans。
6. spring容器引用保存到ServletContext和ContextLoaderListener中。
这样每个servlet 就持有自己的上下文，即拥有自己独立的bean空间，同时各个servlet共享相同的bean

## Tomcat原理解析
Tomcat是一个JSP/Servlet容器。
1、用户点击网页内容，请求被发送到本机端口8080，被在那里监听的Coyote HTTP/1.1 Connector获得。
 2、Connector把该请求交给它所在的Service的Engine来处理，并等待Engine的回应。 
 3、Engine获得请求localhost/test/index.jsp，匹配所有的虚拟主机Host。 
 4、Engine匹配到名为localhost的Host（即使匹配不到也把请求交给该Host处理，因为该Host被定义为该Engine的默认主机），
 名为localhost的Host获得请求/test/index.jsp，匹配它所拥有的所有的Context。Host匹配到路径为/test的Context（如果匹配不到就把该请求交给路径名为
 “ ”的Context去处理）。 
 5、path=“/test”的Context获得请求/index.jsp，在它的mapping table中寻找出对应的Servlet。Context匹配到URL PATTERN为*.jsp的Servlet,
 对应于JspServlet类。 
 6、构造HttpServletRequest对象和HttpServletResponse对象，作为参数调用JspServlet的doGet（）或doPost（）.执行业务逻辑、数据存储等程序。 
 7、Context把执行完之后的HttpServletResponse对象返回给Host。
 8、Host把HttpServletResponse对象返回给Engine。 
 9、Engine把HttpServletResponse对象返回Connector。 
 10、Connector把HttpServletResponse对象返回给客户Browser。