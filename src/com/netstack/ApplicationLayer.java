package com.netstack;

import com.FileIO.FileRecv;
import com.config.Config;
import com.config.GetConfig;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static java.lang.System.out;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/22
 * \* Time: 9:57
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ApplicationLayer implements Runnable{
    private String name;
    private Message message =new Message();
    private LinkedBlockingDeque<Message> queue;
    private LinkedBlockingDeque<Message> queue1=new LinkedBlockingDeque<>();
    private static LinkedBlockingDeque<String> cache=new LinkedBlockingDeque<>();
    private static LinkedBlockingDeque<String> buffer = new LinkedBlockingDeque<String>(1024);
    public Message s;
    public String from;
    public Message ss = new Message();
    String sss="" ;

    Message s1;
    public ApplicationLayer() {
    }

    public ApplicationLayer(LinkedBlockingDeque<Message> queue) {
        this.queue = queue;
    }
    public static LinkedBlockingDeque<String> getBuffer() {
        return buffer;
    }

    public void setBuffer(LinkedBlockingDeque<String> buffer) {
        this.buffer = buffer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public BlockingQueue<Message> getQueue() {
        return queue;
    }

    public void setQueue(LinkedBlockingDeque<Message> queue) {
        this.queue = queue;
    }

    public static LinkedBlockingDeque<String> getCache() {
        return cache;
    }

    public void  setCache(LinkedBlockingDeque<String> cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        while (true){

            synchronized (queue){

                    if (!queue.isEmpty()&&queue.getFirst()!=null){

                        try {
                            from =queue.getFirst().getTo();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(from==this.getName()){
                            try {
                                s =   queue.take();
                                out.println("I am ApplicationLayer ,my info is:"+s.getInfo());//从这打印底层传来的数据
                                //out.println();
                                GetConfig getConfig = new GetConfig();
                                //FileRecv.Recv(getConfig.getFilePath(),s.getInfo());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            /*
                            if (s.getFrom()=="TransportLayer") {
                                message = new Message();
                                message.setFrom("ApplicationLayer");
                                message.setTo("TransportLayer");
                                message.setInfo("I am ApplicationLayer ,my info is:" + s.getInfo());

                                if((ss.getInfo()==message.getInfo()&&ss.getFrom()==message.getFrom()&&ss.getTo()==message.getTo()))
                                {
                                    continue;
                                }
                                else {
                                    try {
                                        queue.put(message);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                ss = message;
                            }*/

                    }
                }

                 if(!buffer.isEmpty()&&buffer.getFirst()!=null){  //这边测试的是从主类里写数据到队列，然后发送给各个模块
                     message = new Message();
                     message.setFrom("ApplicationLayer");
                     message.setTo("TransportLayer");
                     try {
                         message.setInfo(buffer.take());
                         queue.put(message);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }

            }
        }
    }
}