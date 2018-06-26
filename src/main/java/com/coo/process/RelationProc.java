package com.coo.process;

import com.coo.bean.UserInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;
import com.coo.utils.ParseUtils;

/**
 * @Author: cooocy
 * @Date: 2018/6/26 7:44
 * 获取用户社交关系
 **/
public class RelationProc implements PageProcessor {

    private UserInfo userInfo;

    private Site site = Site.me()
            .setRetryTimes(3)
            .setTimeOut(30000)
            .setSleepTime(1500)
            .setCycleRetryTimes(3)
            .setUseGzip(true);

    @Override
    public void process(Page page) {
        ParseUtils.relation2UserInfo(page.getJson(), userInfo);
    }

    @Override
    public Site getSite() {
        return site;
    }

    protected static void crawlerData(UserInfo userInfo) {
        RelationProc relationProc = new RelationProc();
        relationProc.userInfo = userInfo;
        Spider spider = Spider.create(relationProc);
        Request request = new Request("https://api.bilibili.com/x/relation/stat?vmid=" + relationProc.userInfo.getMid());
        request.setMethod(HttpConstant.Method.GET);
        spider.addRequest(request).run();
    }
}
