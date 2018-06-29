package com.coo.downloader;

import com.coo.utils.CrawlerInfo;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * @Author: cooocy
 * @Date: 2018/6/27 19:49
 * @Description: 自定义下载器，添加逻辑
 **/
public class MainDownloader extends HttpClientDownloader {

    public MainDownloader() {
        // setProxyProvider(SimpleProxyProvider.from(
        //         new Proxy("118.31.220.3",8080)
        //         ,new Proxy("171.221.202.181",63000)
        //         ,new Proxy("101.236.18.101",8866)
        //         ,new Proxy("101.236.60.225",8866)
        //         ,new Proxy("125.118.144.252",6666)
        //         ,new Proxy("101.236.19.165",8866)
        //         ,new Proxy("118.190.95.26",9001)
        //         ,new Proxy("118.190.95.43",9001)
        // ));
    }

    @Override
    protected void onError(Request request) {
        String url = request.getUrl();
        CrawlerInfo.mid_fail_cuont.incrementAndGet();
        System.out.println("1111111111");

        CrawlerInfo.unCrawlered.append(url.substring(url.indexOf("=") + 1) + ",");
    }
}
