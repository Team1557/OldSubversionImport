package com.twelvebolt.gallium67;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Gallium67 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        NetworkTable.setClientMode();
        NetworkTable.setIPAddress("localhost");
        
        NetworkTable table = NetworkTable.getTable("SmartDashboard");
        
        if (!table.containsKey("id")) {
            table.putNumber("id", 0);
        }
        if (!table.containsKey("Hello")) {
            table.putNumber("Hello", 0);
        }
        
        int id = (int) table.getNumber("id");
        table.putNumber("id", id + 1);
        table.putNumber("time" + id, 500);
        
        while (true) {
            table.putNumber("Hello", table.getNumber("Hello") + 1);
            Thread.sleep((long) Math.max(table.getNumber("time" + id),1));
        }
    }
}
