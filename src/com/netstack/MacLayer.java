package com.netstack;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/22
 * \* Time: 9:53
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MacLayer implements Runnable{
    private String name;
    private Message message;
    private LinkedBlockingDeque<Message> queue;
    public Message s;
    public Message s1;
    public String from;
    String Ms;
    public MacLayer() {
    }

    public MacLayer(LinkedBlockingDeque<Message> queue) {
        this.queue = queue;
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

    @Override
    public void run() {
        int x =0;
        while (true){
            synchronized (queue){
                if (!queue.isEmpty()&&queue.getFirst()!=null){
                    message = new Message();
                    try {
                        from =queue.getFirst().getTo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(from==this.getName()){
                        try {
                            s =   queue.take();
                            Ms =s.getFrom();
                            message.setFrom("MacLayer");//先设置从哪来

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (Ms=="PhyLayer"){    //根据从哪来判断到哪去
                            message.setTo("NetLayer");
                            message.setInfo(s.getInfo());   //数据
                        }
                        if (s.getFrom()=="NetLayer"){
                            message.setTo("PhyLayer");
                            message.setInfo(s.getInfo()+" from:NetLayer to PhyLayer ");
                        }
                        try {
                            queue.put(message);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

        }
    }
}