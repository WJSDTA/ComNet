package com.FileIO;

import java.io.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/4/28
 * \* Time: 19:54
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class test {
    public static void main(String[] args) {
        /*StringByteExchange stringByteExchange = new StringByteExchange();
        File originalFile = new File("lena.jpg");//指定要读取的图片
        try{
            File result = new File("lena1.jpg");//要写入的图片
            if (result.exists()) {//校验该文件是否已存在
                result.delete();//删除对应的文件，从磁盘中删除
                result = new File("lena1.jpg");//只是创建了一个File对象，并没有在磁盘下创建文件
            }
            if (!result.exists()) {//如果文件不存在

                result.createNewFile();//会在磁盘下创建文件，但此时大小为0K

            }
            FileInputStream in = null;

            in = new FileInputStream(originalFile);

            FileOutputStream out = null;// 指定要写入的图片

            out = new FileOutputStream(result);

            int n = 0;// 每次读取的字节长度
            byte[] bb = new byte[1024];// 存储每次读取的内容
            String ss;
            while ((n = in.read(bb)) != -1) {
                ss = stringByteExchange.byteArrayToStr(bb);

                out.write(ss.getBytes(), 0, n);// 将读取的内容，写入到输出流当中
            }
            //执行完以上后，磁盘下的该文件才完整，大小是实际大小
            out.close();// 关闭输入输出流
            in.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }*/
       /* File originalFile = new File("lena.jpg");//指定要读取的图片
        try {
            File result = new File("lena1.jpg");//要写入的图片
            if (result.exists()) {//校验该文件是否已存在
                result.delete();//删除对应的文件，从磁盘中删除
                result = new File("lena1.jpg");//只是创建了一个File对象，并没有在磁盘下创建文件
            }
            if (!result.exists()) {//如果文件不存在
                result.createNewFile();//会在磁盘下创建文件，但此时大小为0K
            }
            FileInputStream in = new FileInputStream(originalFile);
            FileOutputStream out = new FileOutputStream(result);// 指定要写入的图片
            int n = 0;// 每次读取的字节长度
            byte[] bb = new byte[1024];// 存储每次读取的内容
            while ((n = in.read(bb)) != -1) {
                out.write(bb, 0, n);// 将读取的内容，写入到输出流当中
            }
            //执行完以上后，磁盘下的该文件才完整，大小是实际大小
            out.close();// 关闭输入输出流
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/





        /*try {
            //读取文件(字节流)
            InputStream in = new FileInputStream("a.txt");
            //写入相应的文件
            OutputStream out = new FileOutputStream("b.txt");
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
                out.write(str.getBytes(), 0, n);
            }
            //关闭流
            in.close();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }*/
       /* byte[] bytes = new byte[2048];
        String str = null;

            System.out.println(str.getBytes());



*/



        try {
            //读取文件(字节流)
            InputStream in = new FileInputStream("a.txt");
            //写入相应的文件
            //  OutputStream out = new FileOutputStream("b.txt");
            //读取数据
            //一次性取多少字节
            byte[] bytes = new byte[128];
            //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
            int n = -1;
            //循环取出数据
            while ((n = in.read(bytes,0,bytes.length)) != -1) {
                //转换成字符串
                String str = new String(bytes,0,n,"GBK");
                System.out.println(str);
                //写入相关文件
                String.format("%05d", -1);
                System.out.println( String.format("%05d", n));
                System.out.println( Integer.valueOf(String.format("%05d", -1).substring(0,5)));
                // cache.put(str);
                //out.write(str.getBytes(), 0, n);
            }
          //  String str = new String(bytes,n,n,"GBK");
            //关闭流
            in.close();
            //out.close();
        }catch (Exception e){
            e.printStackTrace();
        }



    }


}