package com.coo.process;

import com.coo.bean.UserInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.utils.HttpConstant;
import com.coo.utils.ParseUtils;

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

    public void process(Page page) {
        Json baseJson = page.getJson();
        if (!baseJson.jsonPath("status").toString().equals("true")) return;
        UserInfo userInfo = new UserInfo();
        ParseUtils.base2UserInfo(baseJson, userInfo);
        RelationProc.crawlerData(userInfo);
        ViewProc.crawlerData(userInfo);
        System.out.println(userInfo);
    }

    public Site getSite() {
        return site;
    }

    public  void crawlerData(int begin, int end, int threadNum) {
        Spider spider = Spider.create(new MainProc());
        for (int i = begin; i <= end; i++) {
            Request request = new Request("http://space.bilibili.com/ajax/member/GetInfo");
            request.setMethod(HttpConstant.Method.POST);
            request.setRequestBody(HttpRequestBody.form(Map.of("mid", 20165629), "utf-8"));
            spider.addRequest(request);
        }
        spider.thread(threadNum).run();
    }
}
