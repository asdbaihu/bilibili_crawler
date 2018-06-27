package com.coo.utils;

import org.apache.commons.lang3.text.StrBuilder;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: cooocy
 * @Date: 2018/6/27 15:34
 **/
public class SerializationUtils {

    final private static Logger logger = Logger.getLogger(SerializationUtils.class);
    final private static String USER_DIR = System.getProperty("user.dir");


    public static boolean str2txt(String str, String path) {
        File file = new File(USER_DIR + path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
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
        File file = new File(USER_DIR + path);
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

    public static <T> boolean serialToFile(List<T> list, String path) {
        if (list.size() == 0) return true;
        T[] array = (T[]) list.toArray();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File(USER_DIR + path)));
            oos.writeObject(array);
            return true;
        } catch (IOException e) {
            logger.error("ERROR:    ", e);
            return false;
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    logger.error("ERROR:    ", e);
                }
            }
        }
    }

    public static <T> List<T> deserialFromFile(String path) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File(USER_DIR + path)));
            T[] array = null;
            try {
                array = (T[]) ois.readObject();
            } catch (ClassNotFoundException e) {
                logger.error("ERROR:    ", e);
            }
            List<T> ts = Arrays.asList(array);
            return ts;
        } catch (IOException e) {
            logger.error("ERROR:    ", e);
            return null;
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    logger.error("ERROR:    ", e);
                }
            }
        }
    }
}
