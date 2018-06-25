package com.coo.bean;

import java.util.Date;

/**
 * @Author: cooocy
 * @Date: 2018/6/25 17:33
 **/
public class UserInfo {

    // =========================================================================
    // http://space.bilibili.com/ajax/member/GetInfo?mid=1
    // {"status":true,"data":{"mid":1,"name":"bishi","sex":"\u7537","rank":10000,"face":"http:\/\/i2.hdslb.com\/bfs\/face\/34c5b30a990c7ce4a809626d8153fa7895ec7b63.gif","regtime":1245823614,"spacesta":0,"birthday":"09-19","sign":"","level_info":{"current_level":4},"official_verify":{"type":-1,"desc":"","suffix":""},"vip":{"vipType":1,"vipStatus":0},"toutu":"bfs\/space\/768cc4fd97618cf589d23c2711a1d1a729f42235.png","toutuId":1,"theme":"default","theme_preview":"","coins":0,"im9_sign":"f2d814399e93b822431eeb838118c455","fans_badge":false}}
    // =========================================================================
    private int mid;
    private String name;
    private String sex;
    private String face;
    private Date regtime;
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
    private int following;  // 关注
    private int follower;   // 粉丝
    private int black;  // 黑名单

    // =========================================================================
    // https://api.bilibili.com/x/space/upstat?mid=1&jsonp=jsonp
    // {"code":0,"message":"0","ttl":1,"data":{"archive":{"view":1076798},"article":{"view":0}}}
    // =========================================================================
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

    public int getBlack() {
        return black;
    }

    public void setBlack(int black) {
        this.black = black;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", face='" + face + '\'' +
                ", regtime=" + regtime +
                ", birthday=" + birthday +
                ", sign='" + sign + '\'' +
                ", current_level='" + current_level + '\'' +
                ", vipType=" + vipType +
                ", vipStatus=" + vipStatus +
                ", coins=" + coins +
                ", whisper=" + whisper +
                ", following=" + following +
                ", follower=" + follower +
                ", black=" + black +
                ", archive_view=" + archive_view +
                ", article_view=" + article_view +
                '}';
    }
}
