package com.coo;

import com.coo.process.MainProc;
import org.apache.log4j.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger  =  Logger.getLogger(Main.class);
        logger.info("INFO:      ");
        logger.error("ERROR:    ");
        MainProc mainProc = new MainProc();
        mainProc.crawlerData(1, 1, 1);
    }
}
