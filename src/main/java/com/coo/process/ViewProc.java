package com.coo.process;

import com.coo.bean.UserInfo;
import com.coo.downloader.OtherDownloader;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;
import com.coo.utils.ParseUtils;

/**
 * @Author: cooocy
 * @Date: 2018/6/26 8:03
 **/
public class ViewProc implements PageProcessor {

    private UserInfo userInfo;

    private Site site = Site.me()
            .setRetryTimes(3)
            .setTimeOut(30000)
            .setSleepTime(1500)
            .setCycleRetryTimes(3);

    @Override
    public void process(Page page) {
        ParseUtils.view2UserInfo(page.getJson(), userInfo);
    }

    @Override
    public Site getSite() {
        return site;
    }

    protected static void crawlerData(UserInfo userInfo) {
        ViewProc viewProc = new ViewProc();
        viewProc.userInfo = userInfo;
        Spider spider = Spider.create(viewProc);
        spider.setDownloader(new OtherDownloader());
        Request request = new Request("https://api.bilibili.com/x/space/upstat?mid=" + viewProc.userInfo.getMid());
        request.setMethod(HttpConstant.Method.GET);
        spider.addRequest(request).run();
    }
}
