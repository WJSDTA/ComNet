package com.FileIO;

import java.io.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/4/28
 * \* Time: 21:19
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class FileRecv {
    public static void write(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content+"\r\n");
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void Recv(String fileName, String content){
        if(Integer.valueOf(content.substring(0,5))==-2){
            OutputStream out = null;
            try {
                out = new FileOutputStream("b.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            try {
                out.write(content.substring(5,content.length()).getBytes(), 0, content.length()-5);
                out.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(Integer.valueOf(content.substring(0,5))==-1){


        }else {

            write("b.txt",content.substring(5,content.length()));
        }


    }


}