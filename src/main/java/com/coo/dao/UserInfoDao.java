package com.coo.dao;

import com.coo.bean.UserInfo;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: cooocy
 * @Date: 2018/6/26 14:46
 **/
public class UserInfoDao {

    public static Logger logger = Logger.getLogger(UserInfoDao.class);

    public boolean saveUserInfo(UserInfo userInfo) {
        String sql = "INSERT INTO tbl_user (mid, name, sex, face, sign, current_level, vip_type, vip_status, coins" +
                ", whisper, following, follower, black, archive_view, article_view, reg_time, birthday, create_time) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        boolean result = false;
        Connection connection = null;
        try {
            connection = HikariCPDataSource.getConnection();
            if (connection == null) return result;
            connection.setAutoCommit(true);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userInfo.getMid());
            preparedStatement.setString(2, userInfo.getName());
            preparedStatement.setString(3, userInfo.getSex());
            preparedStatement.setString(4, userInfo.getFace());
            preparedStatement.setString(5, userInfo.getSign());
            preparedStatement.setInt(6, userInfo.getCurrent_level());
            preparedStatement.setInt(7, userInfo.getVip_type());
            preparedStatement.setInt(8, userInfo.getVip_status());
            preparedStatement.setInt(9, userInfo.getCoins());
            preparedStatement.setInt(10, userInfo.getWhisper());
            preparedStatement.setInt(11, userInfo.getFollowing());
            preparedStatement.setInt(12, userInfo.getFollower());
            preparedStatement.setInt(13, userInfo.getBlack());
            preparedStatement.setInt(14, userInfo.getArchive_view());
            preparedStatement.setInt(15, userInfo.getArticle_view());
            preparedStatement.setDate(16, userInfo.getReg_time());
            preparedStatement.setDate(17, userInfo.getBirthday());
            preparedStatement.setTimestamp(18, new Timestamp(new Date().getTime()));
            preparedStatement.execute();
            result = true;
        } catch (SQLException e) {
            logger.error("ERROR:    ", e);
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                logger.error("ERROR:    ", e);
            }
            return result;
        }
    }
}
