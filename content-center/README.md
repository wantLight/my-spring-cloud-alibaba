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