package com.twelvevoltbolt.gallium;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RoboRealm {
    public static void initialize() {
        NetworkTable server = NetworkTable.getTable("SmartDashboard");
        //server.get
        
        SmartDashboard.putString("Hello", "World");
        
        System.out.println(SmartDashboard.getNumber("Input"));
    }
}
