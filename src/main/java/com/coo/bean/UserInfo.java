package com.coo.bean;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.Formatter;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.Date;

@TargetUrl({"http://space.bilibili.com/ajax/member/GetInfo?mid=1"
                    ,"https://api.bilibili.com/x/relation/stat?vmid=1&jsonp=jsonp"
                    ,"https://api.bilibili.com/x/space/upstat?mid=1&jsonp=jsonp"})
public class UserInfo {

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000)
                , new ConsolePageModelPipeline(), UserInfo.class)
                .addUrl("http://space.bilibili.com/ajax/member/GetInfo?mid=1").thread(1).run();
    }

    // =========================================================================
    // https://space.bilibili.com/ajax/member/GetInfo
    // =========================================================================
    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "mid")
    private int mid;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "name")
    private String name;

    private String sex;

    private String face;

    private Date regtime;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "birthday")
    @Formatter("yyyy-MM-dd HH:mm")
    private Date birthday;

    private String sign;

    private String current_level;

    private int vipType;

    private int vipStatus;

    private int coins;


    // =========================================================================
    // https://api.bilibili.com/x/relation/stat?vmid=1&jsonp=jsonp
    // {"code":0,"message":"0","ttl":1,"data":{"mid":1,"following":0,"whisper":0,"black":0,"follower":24579}}
    // =========================================================================
    private int whisper;    // 耳语

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "following")
    private int following;  // 关注

    private int follower;   // 粉丝

    // =========================================================================
    // https://api.bilibili.com/x/space/upstat?mid=1&jsonp=jsonp
    // {"code":0,"message":"0","ttl":1,"data":{"archive":{"view":archive},"article":{"view":0}}}
    // =========================================================================
    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "archive.view")
    private int archive_view;   // 视频播放数

    private int article_view;   // 文章阅读量


    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCurrent_level() {
        return current_level;
    }

    public void setCurrent_level(String current_level) {
        this.current_level = current_level;
    }

    public int getVipType() {
        return vipType;
    }

    public void setVipType(int vipType) {
        this.vipType = vipType;
    }

    public int getVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(int vipStatus) {
        this.vipStatus = vipStatus;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getWhisper() {
        return whisper;
    }

    public void setWhisper(int whisper) {
        this.whisper = whisper;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getArchive_view() {
        return archive_view;
    }

    public void setArchive_view(int archive_view) {
        this.archive_view = archive_view;
    }

    public int getArticle_view() {
        return article_view;
    }

    public void setArticle_view(int article_view) {
        this.article_view = article_view;
    }

}
