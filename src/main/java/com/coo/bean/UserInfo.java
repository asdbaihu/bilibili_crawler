package com.coo.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: cooocy
 * @Date: 2018/6/25 17:33
 **/
public class UserInfo implements Serializable {

    // =========================================================================
    // http://space.bilibili.com/ajax/member/GetInfo?mid=1
    // {"status":true,"data":{"mid":1,"name":"bishi","sex":"\u7537","rank":10000,"face":"http:\/\/i2.hdslb
    // =========================================================================
    private int mid;
    private String name;
    private String sex;
    private String face;
    private Date reg_time;
    private Date birthday;
    private String sign;
    private int current_level;
    private int vip_type;
    private int vip_status;
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

    public java.sql.Date getReg_time() {
        return reg_time == null ? null : new java.sql.Date(reg_time.getTime());
    }

    public void setReg_time(Date reg_time) {
        this.reg_time = reg_time;
    }

    public java.sql.Date getBirthday() {
        return birthday == null ? null : new java.sql.Date(birthday.getTime());
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

    public int getCurrent_level() {
        return current_level;
    }

    public void setCurrent_level(int current_level) {
        this.current_level = current_level;
    }

    public int getVip_type() {
        return vip_type;
    }

    public void setVip_type(int vip_type) {
        this.vip_type = vip_type;
    }

    public int getVip_status() {
        return vip_status;
    }

    public void setVip_status(int vip_status) {
        this.vip_status = vip_status;
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
                ", reg_time=" + reg_time +
                ", birthday=" + birthday +
                ", sign='" + sign + '\'' +
                ", current_level=" + current_level +
                ", vip_type=" + vip_type +
                ", vip_status=" + vip_status +
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
