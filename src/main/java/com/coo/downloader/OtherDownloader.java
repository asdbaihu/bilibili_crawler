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
        setProxyProvider(SimpleProxyProvider.from(
                new Proxy("101.236.19.165",8866)
                ,new Proxy("118.190.95.43",9001)
                ,new Proxy("139.129.99.9",3128)
                ,new Proxy("119.10.67.144",808)
        ));
    }

}
