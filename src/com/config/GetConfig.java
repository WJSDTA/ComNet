package com.config;

import com.alibaba.fastjson.JSONObject;
import com.json.JsonUtils;
import com.json.JsonWR;
import com.config.Config;


/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/4/28
 * \* Time: 17:46
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class GetConfig {
    String json = JsonWR.getDatafromFile("config");
    //Config config = JsonUtils.stringTOJSONObject(json,Config.class);
    Config config=JsonUtils.jsonStringToObject(json, Config.class);
    public  String getFilePath(){
        return config.getFilePath();
    }
    public SerialConfig getSerialConfig(){
        return config.getSerialConfig();
    }


   /* public static void main(String[] args) {
        String json = JsonWR.getDatafromFile("config");
        //Config config = JsonUtils.stringTOJSONObject(json,Config.class);
        Config config=JsonUtils.jsonStringToObject(json, Config.class);
        System.out.println(config.getSerialConfig().getDataBit());
    }
*/

}