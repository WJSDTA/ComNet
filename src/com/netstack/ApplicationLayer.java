package com.netstack;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

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
    private LinkedBlockingDeque<String> cache=new LinkedBlockingDeque<>();
    public Message s;
    public String from;
    public Message ss = new Message();

    Message s1;
    public ApplicationLayer() {
    }

    public ApplicationLayer(LinkedBlockingDeque<Message> queue) {
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
                                System.out.println("I am ApplicationLayer ,my info is:"+s.getInfo());

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
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
                            }



                    }

                }


            }


        }
    }
}