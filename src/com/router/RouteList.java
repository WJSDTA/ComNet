package com.router;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/5/30
 * \* Time: 10:27
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class RouteList {
    private String destination_address;
    private String source_address;
    private String next_hop;

    public RouteList(String destination_address, String source_address, String next_hop) {
        this.destination_address = destination_address;
        this.source_address = source_address;
        this.next_hop = next_hop;
    }

    public String getDestination_address() {
        return destination_address;
    }

    public void setDestination_address(String destination_address) {
        this.destination_address = destination_address;
    }

    public String getSource_address() {
        return source_address;
    }

    public void setSource_address(String source_address) {
        this.source_address = source_address;
    }

    public String getNext_hop() {
        return next_hop;
    }

    public void setNext_hop(String next_hop) {
        this.next_hop = next_hop;
    }

}