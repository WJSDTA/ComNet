package com.netstack;

import com.config.GetConfig;

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
    GetConfig config = new GetConfig();
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
    public String MacDataEXC(String message){
        String mx =  message.substring(0,5)+String.format("%05d",config.getAddress())+message.substring(5,message.length());

        return mx;
    }
    public boolean MacDe(String message){
        if(message.substring(0,5).equals(String.format("%05d",config.getAddress()))){
            return true;
        }
        else
        {
            return false;
        }
    }
    @Override
    public void run() {
        int x =0;
        while (true){
            synchronized (queue){
                boolean flag =true;
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
                               //数据
                            if(MacDe(s.getInfo())){
                                System.out.println("hello");
                                message.setInfo(s.getInfo().substring(10,s.getInfo().length()));
                            }
                            else {
                                flag =false;
                            }
                        }
                        if (s.getFrom()=="NetLayer"){
                            message.setTo("PhyLayer");
                            message.setInfo(MacDataEXC(s.getInfo()));
                            System.out.println(MacDataEXC(s.getInfo()));
                        }
                        try {
                            if(!flag){}
                            else {
                                queue.put(message);
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

        }
    }
}