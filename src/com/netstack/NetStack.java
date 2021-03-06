package com.netstack;

import com.FileIO.FileSend;
import com.config.Config;
import com.config.SerialConfig;
import com.json.JsonUtils;
import com.json.JsonWR;
import com.netstack.*;
import com.router.ManageRouterTable;
import gnu.io.SerialPort;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/8/20
 * \* Time: 18:05
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class NetStack {
    public NetStack() {
    }
    public void init(String port,String rate,int timeout,int delay,int address ){
        SerialConfig serialConfig = new SerialConfig();
        serialConfig.setPort(port);
        serialConfig.setRate(rate);
        serialConfig.setDataBit(""+ SerialPort.DATABITS_8);
        serialConfig.setStopBit(""+SerialPort.STOPBITS_1);
        serialConfig.setParity(""+SerialPort.PARITY_NONE);
        serialConfig.setParityInt(SerialPort.PARITY_NONE);
        serialConfig.setTimeout(timeout);
        serialConfig.setDelay(delay);
        Config config = new Config();
        config.setSerialConfig(serialConfig);
        config.setFilePath("this");
        config.setAddress(address);
        String json= JsonUtils.objectToJson(config);
        JsonWR.saveDataToFile("config",json);
    }

    public void start(){
    LinkedBlockingDeque<Message> queue = new LinkedBlockingDeque<Message>(1024);
    LinkedBlockingDeque<String> cache = new LinkedBlockingDeque<String>(1024);
    PhyLayer phyLayer = new PhyLayer(queue);
    MacLayer macLayer = new MacLayer(queue);
    NetLayer netLayer = new NetLayer(queue);
    TransportLayer transportLayer = new TransportLayer(queue);
    ApplicationLayer applicationLayer = new ApplicationLayer(queue);
    FileSend fileSend = new FileSend();
    phyLayer.setName("PhyLayer");
    macLayer.setName("MacLayer");
    netLayer.setName("NetLayer");
    transportLayer.setName("TransportLayer");
    applicationLayer.setName("ApplicationLayer");
    applicationLayer.setBuffer(cache);
    //fileSend.setCache(cache);
    new Thread(phyLayer).start();
    new Thread(macLayer).start();
    new Thread(netLayer).start();
    new Thread(transportLayer).start();
    new Thread(applicationLayer).start();
}
    public void add_hop(String destination,String source,String next_address){

        ManageRouterTable manageRouterTable = new ManageRouterTable();

        manageRouterTable.addHop(destination,source,next_address);

    }
    public static void main(String[] args) {

    }
}