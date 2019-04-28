package com.FileIO;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/4/28
 * \* Time: 19:54
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class StringByteExchange {
    public  static  byte[] strToByteArray(String str) {
        if (str == null) {
            return null;
        }
        byte[] byteArray = str.getBytes();
        return byteArray;
    }
    public static  String byteArrayToStr(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        String str = new String(byteArray);
        return str;
    }

    public static void main(String[] args) {
        String s = "hello";
        s.getBytes();
        System.out.println(byteArrayToStr(s.getBytes()));
    }
}