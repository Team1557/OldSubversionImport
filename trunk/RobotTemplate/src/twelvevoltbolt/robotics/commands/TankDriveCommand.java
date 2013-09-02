package twelvevoltbolt.robotics.commands;

import edu.wpi.first.wpilibj.Joystick;
import twelvevoltbolt.robotics.controls.AdvancedDrive;

/**
 * Tank Drive, takes two joysticks, whose Y-axis values correspond to the motor output speeds.
 */
public class TankDriveCommand extends DriveCommand {
    private Joystick leftJoystick;
    private Joystick rightJoystick;
    
    public TankDriveCommand(AdvancedDrive drive, Joystick joy1, Joystick joy2) {
        super(drive);
        
        this.leftJoystick = joy1;
        this.rightJoystick = joy2;
    }
    
    public void drive() {
        double mul = getDrive().getMultiplier() * (getDrive().isReversed() ? -1 : 1);
        
        double leftJoyY = getJoystickAxis(leftJoystick, Joystick.AxisType.kY);
        double rightJoyY = getJoystickAxis(rightJoystick, Joystick.AxisType.kY);
        
        double leftMotor = (!getDrive().isReversed() ? leftJoyY : rightJoyY) * mul;
        double rightMotor = (!getDrive().isReversed() ? rightJoyY : leftJoyY) * mul;

        this.getDrive().setLeftRightMotorOutputs(leftMotor, rightMotor);
    }
    
    public Joystick getLeftJoystick() {
        return leftJoystick;
    }

    public void setLeftJoystick(Joystick leftJoystick) {
        this.leftJoystick = leftJoystick;
    }

    public Joystick getRightJoystick() {
        return rightJoystick;
    }

    public void setRightJoystick(Joystick rightJoystick) {
        this.rightJoystick = rightJoystick;
    }

    
}
