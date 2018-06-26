package com.coo.utils;

import com.coo.bean.UserInfo;
import us.codecraft.webmagic.selector.Json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: cooocy
 * @Date: 2018/6/25 20:25
 **/
public class ParseUtils {

    /**
     * 解析基本用户信息
     * @param json
     * @param userInfo
     */
    public static void base2UserInfo(Json json, UserInfo userInfo) {
        userInfo.setMid(Integer.parseInt(json.jsonPath("data.mid").toString()));
        userInfo.setName(json.jsonPath("data.name").toString());
        userInfo.setSex(json.jsonPath("data.sex").toString());
        userInfo.setFace(json.jsonPath("data.face").toString());
        userInfo.setRegtime(timeStamp2Date(json.jsonPath("data.regtime").toString()));
        userInfo.setBirthday(strDate2Date(json.jsonPath("data.birthday").toString(), "MM-dd"));
        userInfo.setSign(json.jsonPath("data.sign").toString());
        userInfo.setCurrent_level(json.jsonPath("data.level_info.current_level").toString());
        userInfo.setVipType(Integer.parseInt(json.jsonPath("data.vip.vipType").toString()));
        userInfo.setVipStatus(Integer.parseInt(json.jsonPath("data.vip.vipStatus").toString()));
        userInfo.setCoins(Integer.parseInt(json.jsonPath("data.coins").toString()));
    }

    public static void relation2UserInfo(Json json, UserInfo userInfo) {
        if (json.jsonPath("code").toString().equals("0")) {
            userInfo.setWhisper(Integer.parseInt(json.jsonPath("data.whisper").toString()));
            userInfo.setFollower(Integer.parseInt(json.jsonPath("data.follower").toString()));
            userInfo.setFollowing(Integer.parseInt(json.jsonPath("data.following").toString()));
            userInfo.setBlack(Integer.parseInt(json.jsonPath("data.black").toString()));
        } else {
            userInfo.setWhisper(-1);
            userInfo.setFollower(-1);
            userInfo.setFollowing(-1);
            userInfo.setBlack(-1);
        }
    }

    public static void view2UserInfo(Json json, UserInfo userInfo) {
        if (json.jsonPath("code").toString().equals("0")) {
            userInfo.setArchive_view(Integer.parseInt(json.jsonPath("data.archive.view").toString()));
            userInfo.setArticle_view(Integer.parseInt(json.jsonPath("data.article.view").toString()));
        } else {
            userInfo.setArchive_view(-1);
            userInfo.setArticle_view(-1);
        }
    }

    public static Date strDate2Date(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date timeStamp2Date(String timeStamp) {
        return new Date(Long.parseLong(timeStamp));
    }
}
