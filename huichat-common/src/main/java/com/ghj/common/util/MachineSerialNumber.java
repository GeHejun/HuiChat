package com.ghj.common.util;

/**
 * 随机生成数值
 * @author gehj
 */
public class MachineSerialNumber {
    public static Long get() {
        int serialNumber = (int) Math.random() * 31;
        String machineSerialNumber = PropertiesUtil.getInstance().getValue("machine.serial.number", String.valueOf(serialNumber));
        return Long.valueOf(machineSerialNumber);
    }
}
