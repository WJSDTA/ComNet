package com.config;

import com.json.JsonUtils;
import com.json.JsonWR;
import gnu.io.SerialPort;


/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/4/28
 * \* Time: 17:32
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class InitConfig {
    public static void main(String[] args) {
        SerialConfig serialConfig = new SerialConfig();
        serialConfig.setPort("COM3");
        serialConfig.setRate("115200");
        serialConfig.setDataBit(""+ SerialPort.DATABITS_8);
        serialConfig.setStopBit(""+SerialPort.STOPBITS_1);
        serialConfig.setParity(""+SerialPort.PARITY_NONE);
        serialConfig.setParityInt(SerialPort.PARITY_NONE);
        serialConfig.setTimeout(100);
        serialConfig.setDelay(500);
        Config config = new Config();
        config.setSerialConfig(serialConfig);
        config.setFilePath("this");
        String json= JsonUtils.objectToJson(config);
        JsonWR.saveDataToFile("config",json);
        System.out.println(json);
    }
}