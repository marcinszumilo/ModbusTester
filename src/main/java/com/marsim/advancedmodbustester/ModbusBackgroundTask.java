package com.marsim.advancedmodbustester;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.server.*;

import java.util.Random;

public class ModbusBackgroundTask {

    private ModbusServer modbusServer = new ModbusServer();

    public ModbusBackgroundTask() {
        modbusServer.setPort(1522);//Note that Standard Port for Modbus TCP communication is 502
        modbusServer.holdingRegisters[1] = 1234;
        modbusServer.holdingRegisters[2] = 2234;
        modbusServer.holdingRegisters[3] = 3234;
        modbusServer.holdingRegisters[4] = 4234;
    }

    private long referenceTime;
    Random randomValue = new Random();
    public boolean execute(ModbusTesterStructure request1 , ModbusTesterStructure request2 , ModbusTesterStructure request3 , ModbusTesterStructure request4) {
        if (System.currentTimeMillis() - referenceTime > 3000) {
            referenceTime = System.currentTimeMillis();
            request1.setModbusValue((short) randomValue.nextInt(20000));
            modbusServer.holdingRegisters[1] = request1.getModbusValue();
            request2.setModbusValue((short) randomValue.nextInt(20000));
            modbusServer.holdingRegisters[2] = request2.getModbusValue();
            request3.setModbusValue((short) randomValue.nextInt(20000));
            modbusServer.holdingRegisters[3] = request3.getModbusValue();
            request4.setModbusValue((short) randomValue.nextInt(20000));
            modbusServer.holdingRegisters[4] = request4.getModbusValue();
            return true;
        }
        return false;
    }

    public void executeCommunication() {
        try
        {
            modbusServer.Listen();
        }
        catch (java.io.IOException e)
        {
        }
    }
}
