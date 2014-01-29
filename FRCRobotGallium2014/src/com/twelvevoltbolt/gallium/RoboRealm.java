package com.twelvevoltbolt.gallium;

import edu.wpi.first.wpilibj.networktables.NetworkTable;


public class RoboRealm {
    
    public static int IMAGE_WIDTH = 640;
    public static int IMAGE_HEIGHT = 480;
    
    public static void initialize() {
        NetworkTable server = NetworkTable.getTable("SmartDashboard");

        // server.getNumber("FPS");
        
        int x = (int) server.getNumber("COG_X");
        int y = (int) server.getNumber("COG_Y");
        
        double deltaX = x - (IMAGE_WIDTH / 2.0);
        // double deltaY = y - (IMAGE_HEIGHT / 2.0);
        
        if (Math.abs(deltaX) > 30) {
            if (deltaX < 0) {
                // Go left
            } else {
                // Go right
            }
        }
        
        // double close = server.getNumber("COG_BOX_SIZE");
        
    }
}
