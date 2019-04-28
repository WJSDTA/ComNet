import com.alibaba.fastjson.JSONObject;
import com.config.Config;
import com.config.SerialConfig;
import com.config.Config;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/4/28
 * \* Time: 16:35
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class test {
    public static void main(String[] args) {
        /*List<User> users=new ArrayList<User>();

        for(int i=0;i<20;i++){
            User user=new User();
            user.setName("FastJSON"+i);
            user.setAge(20+i);
            *//*SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(dateformat.parse("1991-10-01"));*//*
            user.setEmail("12345678"+i+"@qq.com");

            // 序列化单个对象
           // String json=com.json.JsonUtils.objectToJson(user);

            users.add(user);

            // 序列化对象列表
             String json=com.json.JsonUtils.listToJsonString(users);
            System.out.println(json);
        }*/
        SerialConfig serialConfig = new SerialConfig();
        serialConfig.setPort("COM3");
        Config config = new Config();
        config.setSerialConfig(serialConfig);
        config.setFilePath("this");
        /*try {
            System.out.println(readJsonData("area.json"));
            com.json.JsonWR.saveDataToFile("a",com.json.JsonWR.getDatafromFile("area"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }


}
class User{
    private String name;
    private int Age;
    private String Email;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


}