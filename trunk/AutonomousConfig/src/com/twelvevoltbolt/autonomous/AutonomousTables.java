package com.twelvevoltbolt.autonomous;
 
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;
 
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
            try {
                return getAutonomous().getNumber(key);
            } catch (TableKeyNotDefinedException ex) {
                return def;
            }
        } else {
            return def;
        }
    }
   
    public static String getAutoString(String key, String def) {
        if (check()) {
            try {
                return getAutonomous().getString(key);
            } catch (TableKeyNotDefinedException ex) {
                return def;
            }
        } else {
            return def;
        }
    }
   
    public static boolean getAutoBoolean(String key, boolean def) {
        if (check()) {
            try {
                return getAutonomous().getBoolean(key);
            } catch (TableKeyNotDefinedException ex) {
                return def;
            }
        } else {
            return def;
        }
    }
   
    public static void setAutoNumber(String key, double value) throws NotConnectedException {
        if (check()) {
            getAutonomous().putNumber(key, value);
        } else {
            throw new NotConnectedException();
        }
    }
   
    public static void setAutoString(String key, String value) throws NotConnectedException {
        if (check()) {
            getAutonomous().putString(key, value);
        } else {
            throw new NotConnectedException();
        }
    }
   
    public static void setAutoBoolean(String key, boolean value) throws NotConnectedException {
        if (check()) {
            getAutonomous().putBoolean(key, value);
        } else {
            throw new NotConnectedException();
        }
    }
}