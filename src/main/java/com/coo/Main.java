package com.coo;

import com.coo.process.MainProc;
import com.coo.utils.CrawlerInfo;
import org.apache.log4j.Logger;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Logger logger  =  Logger.getLogger(Main.class);
        logger.info("INFO:      ");
        logger.error("ERROR:    ");
        MainProc mainProc = new MainProc();
        CrawlerInfo.start_time = new Date();
        mainProc.crawlerFromBegin(1301501, 1301701, 1, mainProc);
        CrawlerInfo.end_time = new Date();
        boolean result = CrawlerInfo.writeCrawlerInfo();
        System.out.println(CrawlerInfo.unCrawlered.toString());
        System.out.println(result);
        System.out.println(CrawlerInfo.mid_fail_cuont.get());
    }
}
