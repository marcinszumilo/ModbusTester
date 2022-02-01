package com.marsim.advancedmodbustester;

public class ModbusConfigStructure {
    private String key;
    private String modbusType; // TCP/RTU
    private String modbusMode; // Master/Slave
    private byte modbusAddress; // Modbus adres
    private String modbusTCPIP; // IP
    private String modbusTCPIPPort; // Port

    public ModbusConfigStructure(String key , String modbusType , String modbusMode , byte modbusAdres , String modbusTCPIP , String modbusTCPPort) {
        this.key = key;
        this.modbusType = modbusType;
        this.modbusMode = modbusMode;
        this.modbusAddress = modbusAddress;
        this.modbusTCPIP = modbusTCPIP;
        this.modbusTCPIPPort = modbusTCPPort;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        key = key;
    }

    public String getModbusType() {
        return modbusType;
    }

    public void setModbusType(String modbusType) {
        this.modbusType = modbusType;
    }

    public String getModbusMode() {
        return modbusMode;
    }

    public void setModbusMode(String modbusMode) {
        this.modbusMode = modbusMode;
    }

    public byte getModbusAddress() {
        return modbusAddress;
    }

    public void setModbusAddress(byte modbusAddress) {
        this.modbusAddress = modbusAddress;
    }

    public String getModbusTCPIP() {
        return modbusTCPIP;
    }

    public void setModbusTCPIP(String modbusTCPIP) {
        this.modbusTCPIP = modbusTCPIP;
    }

    public String getModbusTCPIPPort() {
        return modbusTCPIPPort;
    }

    public void setModbusTCPIPPort(String modbusTCPPort) {
        this.modbusTCPIPPort = modbusTCPPort;
    }
}
