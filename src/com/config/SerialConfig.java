package com.config;

import gnu.io.SerialPort;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/4/28
 * \* Time: 17:04
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class SerialConfig {
    private String port;
    private String rate ;
    private String dataBit;
    private String stopBit;
    private String parity;
    private int parityInt;
    private int timeout;
    private int delay;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDataBit() {
        return dataBit;
    }

    public void setDataBit(String dataBit) {
        this.dataBit = dataBit;
    }

    public String getStopBit() {
        return stopBit;
    }

    public void setStopBit(String stopBit) {
        this.stopBit = stopBit;
    }

    public String getParity() {
        return parity;
    }

    public void setParity(String parity) {
        this.parity = parity;
    }

    public int getParityInt() {
        return parityInt;
    }

    public void setParityInt(int parityInt) {
        this.parityInt = parityInt;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}