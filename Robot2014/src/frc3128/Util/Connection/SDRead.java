package frc3128.Util.Connection;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;

/**
 *
 * @author Yousuf Soliman
 */
//TODO Test SmartDashboard reading
public class SDRead {

    /**
     * Gets an integer value from the SmartDashboard, if it is open and
     * connected.
     *
     * @param table the name of the table to retrieve from
     * @param key the key for the data in the table
     * @return an integer value from the table if it exists
     * <p>Null if it does not
     */
    public static int getInt(String table, String key) {
        try {
            return (int) NetworkTable.getTable(table).getInt(key);
        } catch (NetworkTableKeyNotDefined ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    /**
     * Gets a double value from the SmartDashboard, if it is open and connected.
     *
     * @param table the name of the table to retrieve from
     * @param key the key for the data in the table
     * @return a double value from the table if it exists
     * <p>Null if it does not
     */
    public static double getDouble(String table, String key) {
        try {
            return NetworkTable.getTable(table).getDouble(key);
        } catch (NetworkTableKeyNotDefined ex) {
            ex.printStackTrace();
            return -0;
        }
    }

    /**
     * Gets a double value from the SmartDashboard, if it is open and connected.
     *
     * @param table the name of the table to retrieve from
     * @param key the key for the data in the table
     * @return a double value from the table if it exists
     * <p>Null if it does not
     */
    public static String getString(String table, String key) {
        try {
            return NetworkTable.getTable(table).getString(key);
        } catch (NetworkTableKeyNotDefined ex) {
            ex.printStackTrace();
            return "";
        }
    }

    private SDRead() {
    }
}
