package twelvevoltbolt.robotics.commands;

import edu.wpi.first.wpilibj.Joystick;
import twelvevoltbolt.robotics.controls.AdvancedDrive;

/**
 * An abstract Drive command.
 * Takes an AdvancedDrive, and implements a 'drive' command, which should handle the input and drive speed values.
 */
public abstract class DriveCommand extends SimpleCommand {
    private AdvancedDrive drive;
    
    public DriveCommand(AdvancedDrive drive) {
        this.drive = drive;
    }
    
    /**
     * Runs this drive command, using the given AdvancedDrive.
     */
    public void initialize() {
        drive();
    }
    
    /**
     * This method should handle all input and drive output.
     */
    public abstract void drive();
    
    public AdvancedDrive getDrive() {
        return drive;
    }

    public void setDrive(AdvancedDrive drive) {
        this.drive = drive;
    }
    
    /**
     * Gets a Joystick's value for the given axis, modified by the AdvancedDrive's Joystick Modifier.
     * @param joystick
     * The joystick to get the value from
     * @param axis
     * The axis to get the value on.
     * @return 
     * The value of the axis, modified by the joystick modifier.
     */
    public double getJoystickAxis(Joystick joystick, Joystick.AxisType axis) {
        return getDrive().getJoystickModifier().modify(joystick.getAxis(axis));
    }
}
