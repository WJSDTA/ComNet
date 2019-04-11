package com.netstack;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/21
 * \* Time: 21:36
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class PhyLayer implements Runnable {
    private String name;
    private Message message;
    private BlockingQueue<Message> queue;
    public Message s;
    public String from;
   // private LinkedBlockingDeque<Message> cache;
    LinkedBlockingDeque<String> cache = new LinkedBlockingDeque<String>(1024);
    public PhyLayer() {
    }

    public PhyLayer(BlockingQueue<Message> queue) {
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

    public void setQueue(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    ComWR comWR = new ComWR(cache);
    @Override
    public void run() {


        while (true){

            synchronized (queue){

                if (!cache.isEmpty()&&cache.peek()!=null){
                    try {
                        message = new Message("PhyLayer", "MacLayer", cache.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        queue.put(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!queue.isEmpty()&&queue.peek()!=null){
                    try {
                        from =queue.peek().getTo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(from==this.getName()){
                        try {
                            s =   queue.take();
                            System.out.println("I am PhyLayer ,my info is:"+s.getInfo());
                            comWR.send("I am PhyLayer ,my info is:"+s.getInfo());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }

            }

        }
    }
}