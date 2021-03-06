package com.comreader;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/4/11
 * \* Time: 19:24
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

public class Test implements Observer{

    SerialReader sr=new SerialReader();
    public Test()
    {
        openSerialPort(""); //打开串口。
    }
    public void update(Observable o, Object arg){
        String mt=new String((byte[])arg);
        System.out.println("---"+mt); //串口数据
    }


    public void send(String message)
    {
        Test test = new Test();
        test.openSerialPort(message);
    }

    public void openSerialPort(String message)
    {
        HashMap<String, Comparable> params = new HashMap<String, Comparable>();
        String port="COM1";
        String rate = "9600";
        String dataBit = ""+SerialPort.DATABITS_8;
        String stopBit = ""+SerialPort.STOPBITS_1;
        String parity = ""+SerialPort.PARITY_NONE;
        int parityInt = SerialPort.PARITY_NONE;
        params.put( SerialReader.PARAMS_PORT, port ); // 端口名称
        params.put( SerialReader.PARAMS_RATE, rate ); // 波特率
        params.put( SerialReader.PARAMS_DATABITS,dataBit  ); // 数据位
        params.put( SerialReader.PARAMS_STOPBITS, stopBit ); // 停止位
        params.put( SerialReader.PARAMS_PARITY, parityInt ); // 无奇偶校验
        params.put( SerialReader.PARAMS_TIMEOUT,100 ); // 设备超时时间 1秒
        params.put( SerialReader.PARAMS_DELAY, 100 ); // 端口数据准备时间 1秒
        try {
            sr.open(params);
            sr.addObserver(this);
            if(message!=null&&message.length()!=0)
            {
                sr.start();
                sr.run(message);
            }
        } catch (Exception e) {
        }
    }
    public void getList(){
        HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
        h =sr.getAvailableSerialPorts();
        /*while (!h.isEmpty())
        System.out.println(h.iterator());*/
        for (CommPortIdentifier values : h) {
            System.out.println(values.getName());
        }

    }

    public String Bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }


    public  String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0)
            return null;
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }
}

