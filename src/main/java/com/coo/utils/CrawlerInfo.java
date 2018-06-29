package com.coo.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: cooocy
 * @Date: 2018/6/27 19:56
 * @description: 记录抓取过程中的全局信息
 **/
public class CrawlerInfo {

    public static Date start_time;
    public static Date end_time;
    public static int mid_total_count;                      // 待爬取的 mid 总数
    public static AtomicInteger mid_success_count;
    public static AtomicInteger mid_fail_cuont;
    public static StringBuffer unCrawlered;           // 抓取失败或页面不对或保存失败的 mid

    // 文件输出路径
    final private static String USER_DIR = System.getProperty("user.dir");
    private static String writePath = USER_DIR + "/crawlerInfo/" + new Date().getTime();

    static {
        mid_success_count = new AtomicInteger();
        mid_fail_cuont = new AtomicInteger();
        unCrawlered = new StringBuffer();
    }

    /**
     * 写出本次爬取的统计信息
     * @return
     */
    public static boolean writeCrawlerInfo() {
        File file = new File(writePath);
        if (!file.exists()) file.mkdirs();
        return writeUnCrawlered() && writeCrawlerRep();
    }

    private static boolean writeUnCrawlered() {
        return SerializationUtils.str2txt(CrawlerInfo.unCrawlered.toString(), writePath + "/unCrawlered.txt", false);
    }

    private static boolean writeCrawlerRep() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = end_time.getTime() - start_time.getTime();
        float hour = time / (1000 * 60 * 60);
        StringBuilder result = new StringBuilder("抓取完成！！\r\n开始时间：");
        result.append(sdf.format(start_time)).append("\r\nmid总数：");
        result.append(mid_total_count).append("\r\n成功数量：").append(mid_success_count.get());
        result.append("\r\n失败数量：").append(unCrawlered.toString().split(",").length);
        result.append("\r\n完成时间：").append(sdf.format(end_time)).append("\r\n总费时：");
        result.append(hour).append(" 小时");
        return SerializationUtils.str2txt(result.toString(), writePath + "/crawlerRep.txt", false);
    }
}
