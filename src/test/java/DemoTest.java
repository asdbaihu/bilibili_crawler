import com.coo.bean.UserInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.example.GithubRepo;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.Map;

public class DemoTest {

    private static Site site = Site.me()
            .setRetryTimes(3)
            .setTimeOut(30000)
            .setSleepTime(1500)     //跟据试验，http://space.bilibili.com/ajax/member/GetInfo接口有IP接入限制，估计是60s内上限150次
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

    public static void main(String[] args) {
        OOSpider ooSpider = OOSpider.create(site, new ConsolePageModelPipeline(), UserInfo.class);
        Request request = new Request("http://space.bilibili.com/ajax/member/GetInfo");
        request.setMethod(HttpConstant.Method.POST);
        request.setRequestBody(HttpRequestBody.form(Map.of("mid", 1), "utf-8"));
        ooSpider.addRequest(request);
        ooSpider.thread(1).run();
    }
}
