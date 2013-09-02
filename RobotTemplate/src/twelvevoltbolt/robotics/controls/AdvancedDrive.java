package twelvevoltbolt.robotics.controls;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import twelvevoltbolt.robotics.commands.DriveCommand;

/**
 * An advanced 4-motor drive.
 * There are two motors per side.
 */
public class AdvancedDrive extends RobotDrive {
    private NumberModifier joystickModifier;
    private CANJaguar left1;
    private CANJaguar left2;
    private CANJaguar right1;
    private CANJaguar right2;
    private boolean reversed = false;
    private double multiplier = 1;

    /**
     * Creates a new AdvancedDrive.
     * @param left1
     * The front left CANJaguar.
     * @param left2
     * The back left CANJaguar.
     * @param right1
     * The front right CANJaguar.
     * @param right2
     * The back right CANJaguar.
     * @param joystickMod 
     * The Joystick input modifier.
     */
    public AdvancedDrive(CANJaguar left1, CANJaguar left2, CANJaguar right1, CANJaguar right2, NumberModifier joystickMod) {
        super(left1, left2, right1, right2);
        
        this.joystickModifier = joystickMod;
    }
    
    /**
     * Creates a new AdvancedDrive, using the joystick modifier <i>NumberModifer.NONE</i>.
     * @param left1
     * The front left CANJaguar.
     * @param left2
     * The back left CANJaguar.
     * @param right1
     * The front right CANJaguar.
     * @param right2
     * The back right CANJaguar.
     */
    public AdvancedDrive(CANJaguar left1, CANJaguar left2, CANJaguar right1, CANJaguar right2) {
        this(left1, left2, right1, right2, NumberModifier.NONE);
    }
    
    public CANJaguar getFrontLeftJaguar() {
        return left1;
    }

    public void setFrontLeftJaguar(CANJaguar left1) {
        this.left1 = left1;
    }

    public CANJaguar getBackLeftJaguar() {
        return left2;
    }

    public void setBackLeftJaguar(CANJaguar left2) {
        this.left2 = left2;
    }

    public CANJaguar getFrontRightJaguar() {
        return right1;
    }

    public void setFrontRightJaguar(CANJaguar right1) {
        this.right1 = right1;
    }

    public CANJaguar getBackRightJaguar() {
        return right2;
    }
    
    public void setBackRightJaguar(CANJaguar right2) {
        this.right2 = right2;
    }

    /**
     * Gets whether the direction and side of the motors is reversed.
     * Drive Commands should respect the return value of this method.
     */
    public boolean isReversed() {
        return reversed;
    }

    /**
     * Sets whether the direction and side of the motors is reversed, essentially rotating the robot 180 degrees in terms of control.
     */
    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }
    
    /**
     * Executes the given drive command.
     */
    public void drive(DriveCommand driveCommand) {
        driveCommand.drive();
    }
    
    /**
     * Gets the global multiplier for the final motor speed.
     *
     * @return The global multiplier.
     */
    public double getMultiplier() {
        return multiplier;
    }

    /**
     * Sets the global multiplier for the final motor speed. If the resulting
     * number is too large, or too small, it is limited accordingly.
     *
     * @param multiplier The multiplier which every motor value input will be
     * multiplied by.
     */
    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }
    
    /**
     * Gets the NumberModifier that modifies the input from the Joysticks.
     * @return 
     * The current NumberModifier.
     */
    public NumberModifier getJoystickModifier() {
        return joystickModifier;
    }
    
    /**
     * Sets the NumberModifier that modifies the input from the Joysticks.
     */
    public void setJoystickModifier(NumberModifier joystickModifier) {
        this.joystickModifier = joystickModifier;
    }
    
    /**
     * Creates a new AdvancedDrive from Jaguars with the given id, and the given NumberModifier.
     *
     * @param left1Id The id of the front left motor.
     * @param left2Id The id of the back left motor.
     * @param right1Id The id of the front right motor.
     * @param right2Id The id of the back right motor.
     * @param modifier The number modifier to use.
     * @return The AdvancedDrive created from the given Jaguars, or null if the
     * CAN device did not respond.
     */
    public static AdvancedDrive fromJaguarIds(int left1Id, int left2Id, int right1Id, int right2Id, NumberModifier modifier) {
        try {
            CANJaguar left1 = new CANJaguar(left1Id);
            CANJaguar left2 = new CANJaguar(left2Id);
            CANJaguar right1 = new CANJaguar(right1Id);
            CANJaguar right2 = new CANJaguar(right2Id);
            
            return new AdvancedDrive(left1, left2, right1, right2, modifier);
        } catch (CANTimeoutException ex) {
            System.out.println("CAN device did not respond while creating new AdvancedDrive.");
            return null;
        }
    }
    
    /**
     * Creates a new AdvancedDrive from Jaguars with the given id, and with a unchanged NumberModifier.
     *
     * @param left1Id The id of the front left motor.
     * @param left2Id The id of the back left motor.
     * @param right1Id The id of the front right motor.
     * @param right2Id The id of the back right motor.
     * @return The AdvancedDrive created from the given Jaguars, or null if the
     * CAN device did not respond.
     */
    public static AdvancedDrive fromJaguarIds(int left1Id, int left2Id, int right1Id, int right2Id) {
        return AdvancedDrive.fromJaguarIds(left1Id, left2Id, right1Id, right2Id, NumberModifier.NONE);
    }
}
