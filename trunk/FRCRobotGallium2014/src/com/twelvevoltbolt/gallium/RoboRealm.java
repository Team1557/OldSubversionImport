package com.twelvevoltbolt.gallium;

import edu.wpi.first.wpilibj.networktables.NetworkTable;


public class RoboRealm {
    
    public static int IMAGE_WIDTH = 320;
    public static int IMAGE_HEIGHT = 240;
    
    public static NetworkTable server;
    
    public static void init() {
        NetworkTable.setClientMode();
        NetworkTable.setIPAddress("10.15.57.2");
        
        server = NetworkTable.getTable("SmartDashboard");
    }
    
    public static void debug() {
        System.out.println(server.getNumber("COG_X"));
    }
}
