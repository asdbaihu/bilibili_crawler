package com.coo.utils;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: cooocy
 * @Date: 2018/6/27 15:34
 **/
public class SerializationUtils {

    final private static Logger logger = Logger.getLogger(SerializationUtils.class);

    /**
     *
     * @param str
     * @param path
     * @param append 是否追加到文件末尾
     * @return
     */
    public static boolean str2txt(String str, String path, boolean append) {
        File file = new File(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, append);
            fos.write(str.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            logger.error("ERROR:    ", e);
            return false;
        } catch (IOException e) {
            logger.error("ERROR:    ", e);
            return false;
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                logger.error("ERROR:    ", e);
            }
        }
    }

    public static String txt2str(String path) {
        File file = new File(path);
        FileInputStream fis = null;
        byte[] bytes = null;
        try {
            fis = new FileInputStream(file);
            bytes = fis.readAllBytes();
            return new String(bytes);
        } catch (FileNotFoundException e) {
            logger.error("ERROR:    ", e);
            return null;
        } catch (IOException e) {
            logger.error("ERROR:    ", e);
            return null;
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                logger.error("ERROR:    ", e);
            }
        }
    }
}
