package com.netstack;

import com.FileIO.FileSend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/21
 * \* Time: 21:37
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class NetWorkDemo {
    public static void main(String[] args) {
        LinkedBlockingDeque<Message> queue = new LinkedBlockingDeque<Message>(1024);
        LinkedBlockingDeque<String> cache = new LinkedBlockingDeque<String>(1024);
        PhyLayer phyLayer = new PhyLayer(queue);
        MacLayer macLayer = new MacLayer(queue);
        NetLayer netLayer = new NetLayer(queue);
        TransportLayer transportLayer = new TransportLayer(queue);
        ApplicationLayer applicationLayer = new ApplicationLayer(queue);
        FileSend  fileSend = new FileSend();
        phyLayer.setName("PhyLayer");
        macLayer.setName("MacLayer");
        netLayer.setName("NetLayer");
        transportLayer.setName("TransportLayer");
        applicationLayer.setName("ApplicationLayer");
        applicationLayer.setBuffer(cache);
        fileSend.setCache(cache);
        new Thread(phyLayer).start();
        new Thread(macLayer).start();
        new Thread(netLayer).start();
        new Thread(transportLayer).start();
        new Thread(applicationLayer).start();
      //  new Thread(fileSend).start();







/*
        while (true){
            Scanner scanner = new Scanner(System.in);

            try {
                cache.put(scanner.nextLine());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }
}