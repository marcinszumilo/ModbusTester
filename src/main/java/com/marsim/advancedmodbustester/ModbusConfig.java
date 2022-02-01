package com.marsim.advancedmodbustester;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

public class ModbusConfig {
    HashMap<String,ModbusConfigStructure> mapOfDevices = new HashMap<>();

    public HashMap<String, ModbusConfigStructure> getMapOfDevices() {
        return mapOfDevices;
    }

    public void setMapOfDevices(HashMap<String, ModbusConfigStructure> mapOfDevices) {
        this.mapOfDevices = mapOfDevices;
    }

    public void addDevice(String Key, ModbusConfigStructure Value) {
        mapOfDevices.put(Key,Value);
    }

    public void deleteDevice(String Key) {
        mapOfDevices.remove(Key);
    }

    public ObservableList<ModbusConfigStructure> generateDataInTable() {
        ObservableList<ModbusConfigStructure> allData = FXCollections.observableArrayList(
                new ModbusConfigStructure("urzadzenie 1","TCP", "SLAVE" , (byte) 5, "127.0.0.1","502"),
                new ModbusConfigStructure("urzadzenie 2","TCP","SLAVE" , (byte) 6 ,"127.0.0.1","502"),
                new ModbusConfigStructure("urzadzenie 3","TCP", "SLAVE" , (byte) 7 , "127.0.0.1","502"),
                new ModbusConfigStructure("urzadzenie 4","TCP","SLAVE" , (byte) 8 ,"127.0.0.1","502")
                );
        return allData;
    }
}
