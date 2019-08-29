package com.router;

import com.config.GetConfig;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/5/30
 * \* Time: 11:36
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class test {
    public static void main(String[] args) {
        ManageRouterTable manageRouterTable = new ManageRouterTable();
//        manageRouterTable.addHop(String.valueOf('1'),String.valueOf('2'),String.valueOf('3'));
//        manageRouterTable.addHop(String.valueOf('3'),String.valueOf('4'),String.valueOf('5'));
//        manageRouterTable.showHop();
//        System.out.println(manageRouterTable.searchHop(String.valueOf('1'),String.valueOf('2')));
//        manageRouterTable.removeHop(String.valueOf('3'),String.valueOf('4'),String.valueOf('5'));
       // manageRouterTable.showHop();
       /* GetConfig config = new GetConfig();
        System.out.println(config.getAddress());*/
      /* for (int i =0;i<10;i++){
           manageRouterTable.addHop(String.valueOf(i),String.valueOf('1'),String.valueOf('3'));
       }*/
        manageRouterTable.addHop("3","0","1");
    }
}