package com.coo.utils;

import com.coo.bean.UserInfo;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.selector.Json;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: cooocy
 * @Date: 2018/6/25 20:25
 **/
public class ParseUtils {

    public static Logger logger = Logger.getLogger(ParseUtils.class);

    /**
     * 解析基本用户信息
     * @param json
     * @param userInfo
     */
    public static void base2UserInfo(Json json, UserInfo userInfo) {
        userInfo.setMid(str2Int(json.jsonPath("data.mid").toString()));
        userInfo.setName(json.jsonPath("data.name").toString());
        userInfo.setSex(json.jsonPath("data.sex").toString());
        userInfo.setFace(json.jsonPath("data.face").toString());
        String json_get = json.get();
        if (json_get.contains("regtime"))
            userInfo.setReg_time(timeStamp2Date(json.jsonPath("data.regtime").toString()));
        else
            userInfo.setReg_time(null);
        if (json_get.contains("birthday"))
            userInfo.setBirthday(strDate2Date(json.jsonPath("data.birthday").toString(), "MM-dd"));
        else
            userInfo.setBirthday(null);
        userInfo.setSign(json.jsonPath("data.sign").toString());
        userInfo.setCurrent_level(str2Int(json.jsonPath("data.level_info.current_level").toString()));
        userInfo.setVip_type(str2Int(json.jsonPath("data.vip.vipType").toString()));
        userInfo.setVip_status(str2Int(json.jsonPath("data.vip.vipStatus").toString()));
        userInfo.setCoins(str2Int(json.jsonPath("data.coins").toString()));
    }

    public static void relation2UserInfo(Json json, UserInfo userInfo) {
        if (json.jsonPath("code").toString().equals("0")) {
            userInfo.setWhisper(str2Int(json.jsonPath("data.whisper").toString()));
            userInfo.setFollower(str2Int(json.jsonPath("data.follower").toString()));
            userInfo.setFollowing(str2Int(json.jsonPath("data.following").toString()));
            userInfo.setBlack(str2Int(json.jsonPath("data.black").toString()));
        } else {
            userInfo.setWhisper(-1);
            userInfo.setFollower(-1);
            userInfo.setFollowing(-1);
            userInfo.setBlack(-1);
        }
    }

    public static void view2UserInfo(Json json, UserInfo userInfo) {
        if (json.jsonPath("code").toString().equals("0")) {
            userInfo.setArchive_view(str2Int(json.jsonPath("data.archive.view").toString()));
            userInfo.setArticle_view(str2Int(json.jsonPath("data.article.view").toString()));
        } else {
            userInfo.setArchive_view(-1);
            userInfo.setArticle_view(-1);
        }
    }

    public static Date strDate2Date(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            logger.error("ERROR:    ", e);
            return null;
        }
    }

    /**
     *
     * @param timeStamp 秒值
     * @return
     */
    public static Date timeStamp2Date(String timeStamp) {
        try {
            Date date = new Date(Long.parseLong(timeStamp) * 1000);
            return date;
        } catch (Exception e) {
            logger.error("ERROR:    ", e);
            return null;
        }
    }

    public static int str2Int(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            logger.error("ERROR:    ", e);
            return -1;
        }
    }
}
