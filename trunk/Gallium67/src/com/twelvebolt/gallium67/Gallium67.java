/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvebolt.gallium67;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 * @author david
 */
public class Gallium67 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Gallium67.run();
    }
    
    public static void run() throws InterruptedException {
        NetworkTable.setClientMode();
        //NetworkTable.setIPAddress("10.15.57.2");
        //NetworkTable.setIPAddress("127.0.0.1");
        NetworkTable.setIPAddress("localhost");
        
        NetworkTable table = NetworkTable.getTable("SmartDashboard");
        
        System.out.println(table);
        
        int id = table.getInt("id");
        table.putNumber("id", id + 1);
        
        table.putNumber("time" + id, 500);
        
        int i = 0;
        while (true) {
            table.putNumber("Hello", table.getNumber("Hello") + 1);
            Thread.sleep((long) Math.max(table.getNumber("time" + id),1));
        }
    }
}
