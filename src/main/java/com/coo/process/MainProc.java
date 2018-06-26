package com.coo.process;

import com.coo.bean.UserInfo;
import com.coo.dao.UserInfoDao;
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

    private Site site = Site.me()
            .setRetryTimes(3)
            .setTimeOut(30000)
            .setSleepTime(1500)
            .setCycleRetryTimes(3)
            .setUseGzip(true)
            .addHeader("Host", "space.bilibili.com")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0")
            .addHeader("Accept", "application/json, text/plain, */*")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("X-Requested-With", "XMLHttpRequest")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .addHeader("Referer", "http://space.bilibili.com/10513807/");

    private UserInfoDao userInfoDao;
    private List<UserInfo> unSaved = new LinkedList<>();

    public List getUnSave() {
        return unSaved;
    }

    public void process(Page page) {
        Json baseJson = page.getJson();
        if (!baseJson.jsonPath("status").toString().equals("true")) return;
        UserInfo userInfo = new UserInfo();
        ParseUtils.base2UserInfo(baseJson, userInfo);
        RelationProc.crawlerData(userInfo);
        ViewProc.crawlerData(userInfo);
        userInfoDao = new UserInfoDao();
        boolean result = userInfoDao.saveUserInfo(userInfo);
        if (!result) unSaved.add(userInfo);
    }

    public Site getSite() {
        return site;
    }

    public void crawlerData(int begin, int end, int threadNum, MainProc mainProc) {
        Spider spider = Spider.create(mainProc);
        for (int i = begin; i <= end; i++) {
            Request request = new Request("http://space.bilibili.com/ajax/member/GetInfo");
            request.setMethod(HttpConstant.Method.POST);
            request.setRequestBody(HttpRequestBody.form(Map.of("mid", i), "utf-8"));
            spider.addRequest(request);
        }
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                new Proxy("60.216.177.152",8118)
                ,new Proxy("119.10.67.144",808)
                ,new Proxy("118.190.95.26",901)
                ,new Proxy("125.118.144.252",6666)
                ,new Proxy("219.129.169.246",63000)
        ));
        spider.setDownloader(httpClientDownloader);
        spider.thread(threadNum).run();
    }
}
