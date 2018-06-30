这是一个简单的基于webMagic的爬虫项目，用来获取b站用户信息。

### 基本功能

- 获取用户数据，包括基本信息以及粉丝、关注、播放量、阅读量等。

- 记录了抓取过程中的统计数据。包括总页面数、成功数、失败页面链接以及抓取时间。

- 从指定配置文件中的页面链接抓取。

- 可以自由配置代理池。

### 技术选型
- `webMagic 0.7.3`
  - 这是国人制作的一个非常优秀的java爬虫，欢迎大家去支持原作者。https://github.com/code4craft/webmagic
- `HikariCP` 数据库连接池
- `postgresSQL`数据库

### 文件介绍
| 文件名                           | 作用             |
| :---------------------------- | :------------- |
| `bean.UserInfo.java`          | 存储用户信息的`POJO`类 |
| `dao.HikariCPDataSource.java` | 数据库连接池         |
| `dao.UserInfoDao.java`        | 保存用户到数据库的持久层类  |
| `downloader.*.java`           | 自定义下载器，处理失败时逻辑 |
| `process.*.java`              | 下载成功后的数据装配     |
| `utils.*.java`                | 相关工具类          |



