package com.coo.process;

import com.coo.bean.UserInfo;
import com.coo.dao.UserInfoDao;
import com.coo.utils.CrawlerInfo;
import org.apache.commons.lang3.text.StrBuilder;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.utils.HttpConstant;
import com.coo.utils.ParseUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: cooocy
 * @Date: 2018/6/25 17:25
 **/
public class MainProc implements PageProcessor {

    private Spider spider;

    private Site site = Site.me()
            .setRetryTimes(3)
            .setTimeOut(30000)
            .setSleepTime(1500)
            .setCycleRetryTimes(3)
            .addHeader("Host", "space.bilibili.com")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0")
            .addHeader("Accept", "application/json, text/plain, */*")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("X-Requested-With", "XMLHttpRequest")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .addHeader("Referer", "http://space.bilibili.com/10513807/");

    private UserInfoDao userInfoDao;

    public Site getSite() {
        return site;
    }

    public void process(Page page) {
        Json json = page.getJson();
        // 页面下载成功，但内容不对，将 mid 保存到 unCrawlered 。应该是 b 站的反爬机制导致的。
        if (!json.get().startsWith("{\"status\":true,\"data\":{\"mid\":")) {
            Request request = page.getRequest();
            String url = request.getUrl();
            CrawlerInfo.unCrawlered.append(url.substring(url.indexOf("=") + 1)).append(",");
            return;
        }

        UserInfo userInfo = new UserInfo();
        ParseUtils.base2UserInfo(json, userInfo);
        RelationProc.crawlerData(userInfo);
        ViewProc.crawlerData(userInfo);
        userInfoDao = new UserInfoDao();
        boolean result = userInfoDao.saveUserInfo(userInfo);
        if (result) CrawlerInfo.mid_success_count.incrementAndGet();
        else CrawlerInfo.unSaved.add(userInfo);
    }

    public void crawlerFromBegin(int begin, int end, int threadNum, MainProc mainProc) {
        spider = Spider.create(mainProc);
        for (int i = begin; i <= end; i++) {
            StringBuilder sb = new StringBuilder("http://space.b31231ilibili.com/ajax/member/GetInfo?mid=");
            sb.append(i);
            Request request = new Request(sb.toString());
            request.setMethod(HttpConstant.Method.POST);
            request.setRequestBody(HttpRequestBody.form(Map.of("mid", i), "utf-8"));
            spider.addRequest(request);
            CrawlerInfo.mid_total_count ++;
        }
        crawlerData(threadNum);
    }

    public void crawlerFromStr(String str, int threadNum, MainProc mainProc) {
        spider = Spider.create(mainProc);
        String[] mids = str.split(",");
        for (String mid : mids) {
            StrBuilder sb = new StrBuilder("http://space.bilibili.com/ajax/member/GetInfo?mid=");
            sb.append(mid);
            Request request = new Request(sb.toString());
            request.setMethod(HttpConstant.Method.POST);
            request.setRequestBody(HttpRequestBody.form(Map.of("mid", mid), "utf-8"));
            spider.addRequest(request);
            CrawlerInfo.mid_total_count ++;
        }
        crawlerData(threadNum);
    }

    private void crawlerData(int threadNum) {
        HttpClientDownloader httpClientDownloader = new MyDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                new Proxy("118.31.220.3",8080)
                ,new Proxy("118.190.95.26",9001)
                ,new Proxy("119.10.67.144",808)
                ,new Proxy("61.135.217.7",80)
                ,new Proxy("122.114.31.177",808)
                ,new Proxy("118.190.95.35",9001)
                ,new Proxy("118.190.95.43",9001)
                ,new Proxy("101.236.21.22",8866)
                ,new Proxy("139.129.99.9",3128)
                ,new Proxy("101.236.22.141",8866)
        ));
        spider.setDownloader(httpClientDownloader);
        spider.thread(threadNum).run();
    }
}
