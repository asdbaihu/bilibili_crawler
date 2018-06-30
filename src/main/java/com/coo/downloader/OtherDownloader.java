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
                new Proxy("http-dyn.abuyun.com",9020, "H1F7202A731E59PD", "719187705B3D83D6")
        ));
    }

}
