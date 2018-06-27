package com.coo.utils;

import com.coo.bean.UserInfo;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: cooocy
 * @Date: 2018/6/27 19:56
 * @description: 记录抓取过程中的全局信息
 **/
public class CrawlerInfo {

    private static Logger logger = Logger.getLogger(CrawlerInfo.class);

    public static Date start_time;
    public static Date end_time;
    public static int mid_total_count;                      // 待爬取的 mid 总数
    public static AtomicInteger mid_success_count;
    public static StringBuffer unCrawlered;                 // 抓取失败或页面不对的 mid
    public static List<UserInfo> unSaved;                   // 保存数据库失败的 unserInfo 实例

    static {
        mid_success_count = new AtomicInteger();
        unCrawlered = new StringBuffer();
        unSaved = new LinkedList<>();
    }

    public static void writeCrawlerInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = end_time.getTime() - start_time.getTime();
        float hour = time / (1000 * 60 * 60);
        StringBuilder result = new StringBuilder("抓取完成！！\n\t开始时间：");
        result.append(sdf.format(start_time)).append("\n\tmid总数：");
        result.append(mid_total_count).append("\n\t成功数量：").append(mid_success_count.get());
        result.append("\n\t失败数量：").append(unCrawlered.toString().split(",").length + unSaved.size());
        result.append("\n\t完成时间：").append(sdf.format(end_time)).append("\n\t总费时：");
        result.append(hour).append(" 小时");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("/logs/crawlerInfo.txt");
            fos.write(result.toString().getBytes());
        } catch (FileNotFoundException e) {
            logger.error("ERROR: ", e);
        } catch (IOException e) {
            logger.error("ERROR: ", e);
        }
    }
}
