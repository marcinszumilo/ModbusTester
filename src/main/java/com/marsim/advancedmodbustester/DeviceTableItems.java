package com.marsim.advancedmodbustester;

public class DeviceTableItems {
    private final String deviceName;
    private final String modbusType;
    private final String deviceIP;
    private final String devicePort;

    public DeviceTableItems(String deviceName, String modbusType, String deviceIP, String devicePort) {
        this.deviceName = deviceName;
        this.modbusType = modbusType;
        this.deviceIP = deviceIP;
        this.devicePort = devicePort;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getModbusType() {
        return modbusType;
    }

    public String getDeviceIP() {
        return deviceIP;
    }

    public String getDevicePort() {
        return devicePort;
    }
}
