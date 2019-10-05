package com.netstack;

import com.config.GetConfig;
import com.router.ManageRouterTable;

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
public class NetLayer implements Runnable{
    private String name;
    private Message message;
    private BlockingQueue<Message> queue;
    public Message s;
    public String from;
    private LinkedBlockingDeque<Message> cache;
    public NetLayer() {
    }
    public ManageRouterTable manageRouterTable = new ManageRouterTable();
    GetConfig config = new GetConfig();
    public NetLayer(BlockingQueue<Message> queue) {
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

    public LinkedBlockingDeque<Message> getCache() {
        return cache;
    }

    public void setCache(LinkedBlockingDeque<Message> cache) {
        this.cache = cache;
    }
    public String dateEXC(String message){
        /*System.out.println(String.valueOf(Integer.valueOf(message.substring(0,5))));
        System.out.println(String.valueOf(config.getAddress()));*/
        //System.out.println(message);
        String next_hop =  manageRouterTable.searchHop(String.valueOf(Integer.valueOf(message.substring(0,5))) ,String.valueOf(Integer.valueOf(message.substring(6,10))));//此处有逻辑问题
        System.out.println(next_hop);
        next_hop = String.format("%05d",Integer.valueOf(next_hop));
        // String mx = next_hop +message.substring(0,5)+String.format("%05d",config.getAddress())+message.substring(5,message.length());
        String mx = next_hop +message;

        return mx;
    }
    public String dateFromEXC(String message){
        // System.out.println(String.valueOf(Integer.valueOf(message.substring(0,5))));
         //System.out.println(String.valueOf(config.getAddress()));
        String next_hop =  manageRouterTable.searchHop(String.valueOf(Integer.valueOf(message.substring(0,5))) ,String.valueOf(config.getAddress()));

       //   System.out.println(next_hop);
        next_hop = String.format("%05d",Integer.valueOf(next_hop));
        String mx = next_hop +message.substring(0,5)+String.format("%05d",config.getAddress())+message.substring(5,message.length());

        return mx;

    }
    public int NetDataEXC(String message){
        if(message.substring(0,5).equals(String.format("%05d",config.getAddress()))){
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public void run() {
        while (true){
            synchronized (queue){
                if (!queue.isEmpty()&&queue.peek()!=null){
                    message = new Message();
                    try {
                        from =queue.peek().getTo();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(from==this.getName()){
                        try {
                            s =   queue.take();
                            message.setFrom(this.name);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (s.getFrom()=="MacLayer"){
                            if(NetDataEXC(s.getInfo())==1){
                                message.setTo("TransportLayer");
                                message.setInfo(s.getInfo().substring(10,s.getInfo().length()));
                            }
                            else {
                                message.setTo("MacLayer");
                                //message.setInfo(dateEXC(s.getInfo().substring(0,5)+s.getInfo().substring(10,s.getInfo().length())));
                                message.setInfo(dateEXC(s.getInfo()));

                            }

                        }
                        if (s.getFrom()=="TransportLayer"){
                            message.setTo("MacLayer");
                            message.setInfo(dateFromEXC(s.getInfo()));
                           // System.out.println(message.getInfo());
                            //System.out.println(dateEXC(s.getInfo()));
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