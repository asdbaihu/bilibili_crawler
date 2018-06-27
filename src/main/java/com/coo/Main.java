package com.coo;

import com.coo.process.MainProc;
import com.coo.utils.CrawlerInfo;
import com.coo.utils.SerializationUtils;
import org.apache.log4j.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger  =  Logger.getLogger(Main.class);
        logger.info("INFO:      ");
        logger.error("ERROR:    ");
        MainProc mainProc = new MainProc();
        mainProc.crawlerFromBegin(400001, 401000, 40, mainProc);
        // 序列化失败数据
        SerializationUtils.str2txt(CrawlerInfo.unCrawlered.toString(), "/logs/unCrawlered.txt");
        SerializationUtils.serialToFile(CrawlerInfo.unSaved, "/logs/unSaved.txt");
        // 输出全局信息
        CrawlerInfo.writeCrawlerInfo();
    }
}
