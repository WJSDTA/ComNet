package com.netstack;

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
        phyLayer.setName("PhyLayer");
        macLayer.setName("MacLayer");
        netLayer.setName("NetLayer");
        transportLayer.setName("TransportLayer");
        applicationLayer.setName("ApplicationLayer");
        applicationLayer.setBuffer(cache);
        new Thread(phyLayer).start();
        new Thread(macLayer).start();
        new Thread(netLayer).start();
        new Thread(transportLayer).start();
        new Thread(applicationLayer).start();
        try {
            //读取文件(字节流)
            InputStream in = new FileInputStream("a.txt");
            //写入相应的文件
          //  OutputStream out = new FileOutputStream("b.txt");
            //读取数据
            //一次性取多少字节
            byte[] bytes = new byte[2048];
            //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
            int n = -1;
            //循环取出数据
            while ((n = in.read(bytes,0,bytes.length)) != -1) {
                //转换成字符串
                String str = new String(bytes,0,n,"GBK");
                System.out.println(str);
                //写入相关文件

                System.out.println( String.format("%05d", n));
                cache.put(String.format("%05d", n)+str);
                //out.write(str.getBytes(), 0, n);
            }
           // String str = new String(bytes,0,n,"GBK");
            //关闭流
            in.close();
            //out.close();
        }catch (Exception e){
            e.printStackTrace();
        }







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