package com.bookproject.utils;

import java.util.Random;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/
public class KeyUtil {

    public static String genUniqueKey() {

        Random random = new Random();

        Integer number=random.nextInt(90000)+10000;

        return String.valueOf(number);
    }

}
