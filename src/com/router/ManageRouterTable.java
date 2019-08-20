package com.router;

import com.json.JsonUtils;
import com.json.JsonWR;

import java.util.Iterator;
import java.util.Set;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/7/8
 * \* Time: 21:38
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ManageRouterTable {
    public static RouteTable routeTable  = new RouteTable();
    //public static RouteTable routeTable  ;
    public Set<RouteList> set;
    public ManageRouterTable() {

         set =     routeTable.getSet();
    }

    public String searchHop(String destination_address, String source_address){
        String json = JsonWR.getDatafromFile("routeJson");
        //Config config = JsonUtils.stringTOJSONObject(json,Config.class);
        routeTable= JsonUtils.jsonStringToObject(json, RouteTable.class);
        String tmp=null;
        set = routeTable.getSet();
        for (RouteList routeList : set) {
            if (routeList.getDestination_address().equals(destination_address) && routeList.getSource_address().equals(source_address)) {
                tmp = routeList.getNext_hop();
                break;
            }
        }
        return tmp;
    }
    void addHop(String destination_address, String source_address,String next_hop){
        set.add(new RouteList(destination_address,source_address,next_hop));
        routeTable.setSet(set);
        String json= JsonUtils.objectToJson(routeTable);
        JsonWR.saveDataToFile("routeJson",json);

    }
    void removeHop(String destination_address, String source_address,String next_hop){

        Iterator<RouteList> it = set.iterator();
        while(it.hasNext()){
            RouteList str = it.next();
            if(str.getNext_hop().equals(next_hop)&&str.getSource_address().equals(source_address)&&str.getDestination_address().equals(destination_address)){
                it.remove();
            }
        }
        routeTable.setSet(set);
        String json= JsonUtils.objectToJson(routeTable);
        JsonWR.saveDataToFile("routeJson",json);

    }
    void showHop(){
        //set.add(new RouteList(destination_address,source_address,next_hop));
        String json = JsonWR.getDatafromFile("routeJson");

        //Config config = JsonUtils.stringTOJSONObject(json,Config.class);
        routeTable= JsonUtils.jsonStringToObject(json, RouteTable.class);
       // System.out.println(routeTable.getSet().size());
        //Iterator it = set.iterator();
        set = routeTable.getSet();
        Iterator<RouteList> it = set.iterator();
        while(it.hasNext()){
            RouteList str = it.next();


            System.out.println("destination_address is :"+str.getDestination_address()+"\t"+"source_address is :"+str.getSource_address()+"\t"+"next_hop is :"+str.getNext_hop());
        }

    }
}