package com.config;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/4/28
 * \* Time: 16:52
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Config {
    private SerialConfig serialConfig;
    private String FilePath;
    private int address ;

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public SerialConfig getSerialConfig() {
        return serialConfig;
    }

    public void setSerialConfig(SerialConfig serialConfig) {
        this.serialConfig = serialConfig;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }


}