package com.netstack;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/7/10
 * \* Time: 11:55
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class SocketR {
    private LinkedBlockingDeque<String> cache = ApplicationLayer.getBuffer();
    public void send(int address ,String message) throws InterruptedException {
        System.out.println(address);
        cache.put(String.format("%05d", address)+message);
       // System.out.println(address);
         Thread.sleep(3000);
    }

}