package com.product.server.utils;

import java.util.Random;

/**
 * @author jason
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式： 时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return String.valueOf(System.currentTimeMillis() + number);
    }
}
