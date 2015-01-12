package com.twelvevoltbolt.disk.commands;

import com.twelvevoltbolt.disk.RobotMap;
import edu.wpi.first.wpilibj.Joystick;

/**
 * A tank drive command that takes input from two joysticks and outputs to the
 * drive.
 *
 * Left:
 *     Y axis: Drive left
 *     Trigger: Shift Down
 * 
 * Right:
 *     Y axis: Drive right
 *     Trigger: Shift Up
 * 
 * Alternate:
 *     2: Vacuum
 *     3: Fire
 *     X axis: Turning, when driver is not active
 *     Trigger + Y axis: Arm control
 * 
 * @author code
 */
public class ArcadeDriveCommand extends CommandBase {
    
    double motorLeft = 0;
    double motorRight = 0;
    
    public ArcadeDriveCommand() {
        requires(drive);
        // drive.leftMotor1.configEncoderCodesPerRev(codesPerRev);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    public double normalize(double joystick, double motor) {
        
        if (Math.abs(joystick) < Math.abs(motor)) {
            if (joystick > 0) {
                double result = Math.min(motor - RobotMap.motorRampStep, motor);
                System.out.println("Joystick>0 I:"+joystick+", O:" + result);
                return result;
            } else if (Math.abs(motor) < RobotMap.motorRampStep) {
                System.out.println("Joystick<step");
                return 0;
            } else if (joystick < 0) {
                double result = Math.min(motor + RobotMap.motorRampStep, motor);
                System.out.println("Joystick<0 I:"+joystick+", O:" + result);
                return Math.max(motor + RobotMap.motorRampStep, motor);
            }
        }
        
        return joystick;
        
        /*
        if (Math.abs(joystick) < Math.abs(motor) || Math.abs(joystick) < RobotMap.motorRampStart) {
            return motor - MathUtils.sign(joystick) * Math.min(RobotMap.motorRampStep, Math.abs(motor) - Math.abs(joystick));
        } else {
            return joystick;
        }
        */
    }
    
    public static double MAX_SPEED_WHILE_VACUUM = 0.7;

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Joystick left = oi.getMainJoystick();

        drive.drive(left.getAxis(Joystick.AxisType.kY), left.getAxis(Joystick.AxisType.kX));

        if (oi.isDebug()) {
            System.out.println("Tank drive: " + motorLeft + ", " + motorRight);
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}