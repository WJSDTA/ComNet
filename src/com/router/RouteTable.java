package com.router;

import com.json.JsonUtils;
import com.json.JsonWR;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/5/30
 * \* Time: 10:24
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public  class  RouteTable {

    public RouteTable() {

    }

    private Set<RouteList> set = new LinkedHashSet<>();

    public Set<RouteList> getSet() {
        return set;
    }

    public void setSet(Set<RouteList> set) {
        this.set = set;
    }


}