package com.netstack;

import com.config.GetConfig;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/9/16
 * \* Time: 22:01
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MacLayerC implements Runnable{
    private String name;
    private Message message;
    private LinkedBlockingDeque<Message> queue;
    private LinkedBlockingDeque<Message> dataCache = new LinkedBlockingDeque<>();

    public LinkedBlockingDeque<Message> getIntoCache() {
        return intoCache;
    }

    public void setIntoCache(LinkedBlockingDeque<Message> intoCache) {
        this.intoCache = intoCache;
    }

    public LinkedBlockingDeque<Message> getOutCache() {
        return outCache;
    }

    public void setOutCache(LinkedBlockingDeque<Message> outCache) {
        this.outCache = outCache;
    }

    private LinkedBlockingDeque<Message> intoCache = new LinkedBlockingDeque<>();
    private LinkedBlockingDeque<Message> outCache = new LinkedBlockingDeque<>();



    public Message s;
    public Message s1;
    public String from;
    String Ms;
    GetConfig config = new GetConfig();
    public MacLayerC() {
    }

    public MacLayerC(LinkedBlockingDeque<Message> queue) {
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
    public void cacheIO(LinkedBlockingDeque<Message> Cache){

    }
    @Override
    public void run() {
        int x =0;
        while (true){
            synchronized (queue){
                //都没有用了，
                boolean flag =true;
                if(!dataCache.isEmpty()&&dataCache.getFirst()!=null&&queue.getFirst().getFrom()==this.getName()){
                    try {

                        queue.put(dataCache.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!queue.isEmpty()&&queue.getFirst()!=null){
                   // message = new Message();
                    try {
                        from =queue.getFirst().getTo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(from==this.getName()){
                      /*  try {
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
                        }*/
                        try {
                            dataCache.put(queue.take());
                            //System.out.println("dataCache.take()");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

                //此代码待检验正确与否
                synchronized (intoCache){//不是queue，应为交互队列
                    if(!dataCache.isEmpty()&&dataCache.getFirst()!=null&&dataCache.getFirst().getTo()==this.getName()){//把数据传到 数据处理的进程
                        try {
                            System.out.println("into");
                            Message tmp = dataCache.take();
                            //System.out.println(tmp.getInfo());
                            intoCache.put(tmp);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            synchronized (outCache){//不是queue，应为交互队列
                if(!outCache.isEmpty()&&outCache.getFirst()!=null){//把数据从处理的进程取出来

                    try {
                        dataCache.put(outCache.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}