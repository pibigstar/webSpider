# 爬虫框架设计

 > 爬虫生命周期：下载、处理、管理和持久化等功能。 spider控制组件，让它们可以互相交互，流程化的执行。

## 组件
**Download**
负责从互联网上下载页面，以便后续处理。
**PageProcess**
负责解析页面，抽取信息，以及发现新的辅助URL。
**SchedulManager**
负责管理待抓取的URL，以及一些去重的工作。(jdk队列或者Redis)
**ResultOutput**
ResultOutput负责抽取结果的处理，包括计算、持久化到文件、数据库等。
 
 
 ## 使用

```java
public static void main(String[] args) {
    Spider.create(new GithubProcessor())
            .addUrl("https://github.com/junicore")
            //使用Redis来管理URL队列
            .setScheduler(new RedisScheduler("localhost"))
            //将结果以json方式保存到文件
            .addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
            //开启5个线程同时执行
            .thread(5)
            //启动爬虫
            .run();
}
```
> 一般来说，对于编写一个爬虫，PageProcessor是需要编写的部分，而Spider则是创建和控制爬虫的入口。定制PageProcessr来编写一个爬虫，并通过Spider来启动。
