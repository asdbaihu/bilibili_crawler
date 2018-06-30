package com.coo.downloader;

import com.coo.utils.CrawlerInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
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
        setProxyProvider(SimpleProxyProvider.from(
                new Proxy("http-dyn.abuyun.com",9020, "H1F7202A731E59PD", "719187705B3D83D6")
        ));
    }

    @Override
    public Page download(Request request, Task task) {
        Page page = super.download(request, task);
        Site site = task.getSite();
        // page is download successfully,but the status code is not right.
        if (page.isDownloadSuccess() && !site.getAcceptStatCode().contains(page.getStatusCode())) {
            String url = request.getUrl();
            CrawlerInfo.unCrawlered.append(url.substring(url.indexOf("=") + 1)).append(",");
        }
        return page;
    }


    @Override
    protected void onError(Request request) {
        String url = request.getUrl();
        CrawlerInfo.unCrawlered.append(url.substring(url.indexOf("=") + 1)).append(",");
    }
}
