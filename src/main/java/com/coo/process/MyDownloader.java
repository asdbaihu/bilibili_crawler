package com.coo.process;

import com.coo.utils.CrawlerInfo;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

/**
 * @Author: cooocy
 * @Date: 2018/6/27 19:49
 * @Description: 自定义下载器，添加逻辑
 **/
public class MyDownloader extends HttpClientDownloader {

    @Override
    protected void onError(Request request) {
        String url = request.getUrl();
        CrawlerInfo.unCrawlered.append(url.substring(url.indexOf("=") + 1));
    }
}
