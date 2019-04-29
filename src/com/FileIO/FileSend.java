package com.FileIO;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/4/28
 * \* Time: 21:13
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class FileSend implements Runnable{
    private LinkedBlockingDeque<String> cache = new LinkedBlockingDeque<String>(1024);

    public LinkedBlockingDeque<String> getCache() {
        return cache;
    }

    public void setCache(LinkedBlockingDeque<String> cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
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
            boolean flag =false;
            while ((n = in.read(bytes,0,bytes.length)) != -1) {
                //转换成字符串
                String str = new String(bytes,0,n,"GBK");
                System.out.println(str);
                //写入相关文件

                System.out.println( String.format("%05d", n));
                if(flag ==false){
                    cache.put(String.format("%05d", -2)+str);
                    flag =true;
                }
                else {
                    cache.put(String.format("%05d", n)+str);
                }

                //out.write(str.getBytes(), 0, n);
            }
            cache.put(String.format("%05d", -1));
            // String str = new String(bytes,0,n,"GBK");
            //关闭流
            in.close();
            //out.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}