package com.netstack;

import com.config.GetConfig;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/9/17
 * \* Time: 10:12
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MacLayerCal implements Runnable{
    private LinkedBlockingDeque<Message> intoCache ;
    private LinkedBlockingDeque<Message> outCache ;
    private LinkedBlockingDeque<Message> intoDataCache  = new LinkedBlockingDeque<>();
    private LinkedBlockingDeque<Message> outDataCache = new LinkedBlockingDeque<>();
    private Message data;
    GetConfig config = new GetConfig();

    public LinkedBlockingDeque<Message> getOutCache() {
        return outCache;
    }

    public void setOutCache(LinkedBlockingDeque<Message> outCache) {
        this.outCache = outCache;
    }

    public LinkedBlockingDeque<Message> getIntoCache() {
        return intoCache;
    }

    public void setIntoCache(LinkedBlockingDeque<Message> intoCache) {
        this.intoCache = intoCache;
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
    public void DataReload(LinkedBlockingDeque<Message> deque1,LinkedBlockingDeque<Message> deque2){
        synchronized (deque1){
            if(!deque1.isEmpty()&&deque1.getFirst()!=null){//把数据从处理的进程取出来

                try {
                    deque2.put(deque1.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void DataReSet(LinkedBlockingDeque<Message> deque1,LinkedBlockingDeque<Message> deque2){
        synchronized (deque1){
            if(!deque2.isEmpty()&&deque2.getFirst()!=null){//把数据从处理的进程取出来

                try {
                    deque1.put(deque2.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void Datacal(LinkedBlockingDeque<Message> deque1,LinkedBlockingDeque<Message> deque2){
        if(!deque1.isEmpty()&&deque1.getFirst()!=null){
            try {
                data = deque1.take();
                System.out.println(data.getInfo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
    @Override
    public void run() {
        while (true){//需要重新考虑
           DataReload(intoCache,intoDataCache);
           Datacal(intoDataCache,outDataCache);
           DataReSet(outCache,outDataCache);

        }

    }
}