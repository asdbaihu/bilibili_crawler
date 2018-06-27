package com.coo;

import com.coo.process.MainProc;
import com.coo.utils.SerializationUtils;
import org.apache.log4j.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger  =  Logger.getLogger(Main.class);
        logger.info("INFO:      ");
        logger.error("ERROR:    ");

        // 加载上一次抓取错误的mids
        String mids = SerializationUtils.txt2str("/logs/unCrawlered.txt");
        if (mids != null) {
            MainProc mainProc = new MainProc();
            mainProc.crawlerFromStr(mids, 10, mainProc);
            // 处理失败数据
            boolean res_unCrawlered = SerializationUtils.str2txt(mainProc.unCrawlered.toString(), "/logs/unCrawlered.txt");
            boolean res_unSaved = SerializationUtils.serialToFile(mainProc.unSaved, "/logs/unSaved.txt");
            System.out.println("res_unCrawlered :: " + res_unCrawlered);
            System.out.println("res_unSaved :: " + res_unSaved);
        }

        /*MainProc mainProc = new MainProc();
        mainProc.crawlerFromBegin(315001, 316000, 40, mainProc);*/
    }
}
