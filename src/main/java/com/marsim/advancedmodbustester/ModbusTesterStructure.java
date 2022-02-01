package com.marsim.advancedmodbustester;

public class ModbusTesterStructure {
    private String key;
    private String device;
    private byte modbusDeviceAddress;
    private byte functionCode;
    private int modbusAddress;
    private short modbusValue;
    private short modbusTimeout;

    public ModbusTesterStructure(String key, String device , byte modbusDeviceAddress , byte functionCode, int modbusAddress, short modbusValue, short modbusTimeout) {
        this.key = key;
        this.device = device;
        this.modbusDeviceAddress = modbusDeviceAddress;
        this.functionCode = functionCode;
        this.modbusAddress = modbusAddress;
        this.modbusValue = modbusValue;
        this.modbusTimeout = modbusTimeout;
    }

    public int getModbusDeviceAddress() {
        return modbusDeviceAddress;
    }

    public void setModbusDeviceAddress(byte modbusDeviceAddress) {
        this.modbusDeviceAddress = modbusDeviceAddress;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public byte getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(byte functionCode) {
        this.functionCode = functionCode;
    }

    public int getModbusAddress() {
        return modbusAddress;
    }

    public void setModbusAddress(int modbusAddress) {
        this.modbusAddress = modbusAddress;
    }

    public short getModbusValue() {
        return modbusValue;
    }

    public void setModbusValue(short modbusValue) {
        this.modbusValue = modbusValue;
    }

    public short getModbusTimeout() {
        return modbusTimeout;
    }

    public void setModbusTimeout(short modbusTimeout) {
        this.modbusTimeout = modbusTimeout;
    }
}
