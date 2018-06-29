package com.coo.downloader;

import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * @Author: cooocy
 * @Date: 2018/6/28 8:19
 * @description:
 **/
public class OtherDownloader extends HttpClientDownloader {

    public OtherDownloader() {
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

}
