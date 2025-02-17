package com.twelvevoltbolt.gallium.commands;

import com.twelvevoltbolt.gallium.RobotMap;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

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
public class TankDriveCommand extends CommandBase {
    
    double motorLeft = 0;
    double motorRight = 0;
    
    public TankDriveCommand() {
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
//        drive.updateGears();
        
//        motorLeft = normalize(oi.getLeftInput(), motorLeft);
//        motorRight = normalize(oi.getRightInput(), motorRight);
//        
//        if (vacuum.isSucks()) {
//            //if (motorLeft < 
//        }
        
        motorLeft = oi.getLeftInput();
        motorRight = oi.getRightInput();
        
        /*if (Math.abs(motorLeft) < 0.03 && Math.abs(motorRight) < 0.03) {
            //Alt drive
            double amountForward = oi.getAltInput();
            double angle = oi.getAltTurnAngle();
            
            if (Math.abs(angle) > 0.1 || Math.abs(amountForward) > 0.1) {
                // drive.drive(angle * .75, -angle * .75);
                
                if (oi.isDebug()) {
                    System.out.println("Alt drive: " + angle + ", " + amountForward);
                }
                
                if (!oi.armControl.get()) {
                    drive.arcadeDrive(oi.getAltInput() * .75, oi.getAltTurnAngle());
                }
            } else {
                drive.drive(0, 0);
            }
        } else */{
            //Normal Drive
//            System.out.println("r " + shifter.getEncoder().getRate());
//            System.out.println("s " + shifter.getEncoder().getRaw() + ".0");
            
            drive.drive(-motorLeft, -motorRight);
            
            if (oi.isDebug()) {
                System.out.println("Tank drive: " + motorLeft + ", " + motorRight);
            }
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