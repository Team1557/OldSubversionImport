/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.twelvevoltbolt.autonomous;

import static com.twelvevoltbolt.autonomous.AutonomousTables.check;
import static com.twelvevoltbolt.autonomous.AutonomousTables.getAutonomous;
import static com.twelvevoltbolt.autonomous.AutonomousTables.server;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

public class AutonomousTables {
    
    public static NetworkTable server;
    
    public static void init() {
        NetworkTable.setClientMode();
        NetworkTable.setIPAddress("10.15.57.2");
        
        server = NetworkTable.getTable("SmartDashboard");
    }
    
    public static boolean check() {
        return server.isConnected();
    }
    
    public static ITable getAutonomous() {
        return server.getSubTable("Autonomous");
    }
    
    public static double getAutoNumber(String key, double def) {
        if (check()) {
            return getAutonomous().getNumber(key);
        } else {
            return def;
        }
    }
    
    public static String getAutoString(String key, String def) {
        if (check()) {
            return getAutonomous().getString(key);
        } else {
            return def;
        }
    }
    
    public static boolean getAutoBoolean(String key, boolean def) {
        if (check()) {
            return getAutonomous().getBoolean(key);
        } else {
            return def;
        }
    }
}
