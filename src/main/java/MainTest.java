import com.coo.bean.UserInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.utils.HttpConstant;
import utils.ParseUtils;

import java.util.Map;

/**
 * @Author: cooocy
 * @Date: 2018/6/25 17:25
 **/
public class MainTest implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    Site site = Site.me()
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

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider spider = Spider.create(new MainTest());
        Request request = new Request("http://space.bilibili.com/ajax/member/GetInfo");
        request.setMethod(HttpConstant.Method.POST);
        request.setRequestBody(HttpRequestBody.form(Map.of("mid", 3553720), "utf-8"));
        spider.addRequest(request);
        spider.thread(1).run();
    }
}
