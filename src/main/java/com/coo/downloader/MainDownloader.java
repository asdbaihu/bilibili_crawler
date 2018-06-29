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
                new Proxy("101.236.19.165",8866)
                ,new Proxy("118.190.95.43",9001)
                ,new Proxy("139.129.99.9",3128)
                ,new Proxy("119.10.67.144",808)
        ));
    }

    @Override
    public Page download(Request request, Task task) {
        Page page = super.download(request, task);
        Site site = task.getSite();
        // 页面下载成功，但是状态码不对，也不算成功
        if (page.isDownloadSuccess() && !site.getAcceptStatCode().contains(page.getStatusCode())) {
            String url = request.getUrl();
            CrawlerInfo.unCrawlered.append(url.substring(url.indexOf("=") + 1)).append(",");
        }
        return page;
    }


    @Override
    protected void onError(Request request) {
        String url = request.getUrl();
        CrawlerInfo.mid_fail_cuont.incrementAndGet();
        CrawlerInfo.unCrawlered.append(url.substring(url.indexOf("=") + 1)).append(",");
    }
}
