package com.marsim.advancedmodbustester;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

public class ModbusTester {
    HashMap<String,ModbusTesterStructure> mapOfRequests = new HashMap<>();

    public HashMap<String, ModbusTesterStructure> getMapOfDevices() {
        return mapOfRequests;
    }

    public void setMapOfDevices(HashMap<String, ModbusTesterStructure> mapOfRequests) {
        this.mapOfRequests = mapOfRequests;
    }

    public void addDevice(String Key, ModbusTesterStructure Value) {
        mapOfRequests.put(Key,Value);
    }

    public void deleteDevice(String Key) {
        mapOfRequests.remove(Key);
    }

    public ObservableList<ModbusTesterStructure> generateDataInTable() {
        ObservableList<ModbusTesterStructure> allData = FXCollections.observableArrayList(
                );
        return allData;
    }
}
