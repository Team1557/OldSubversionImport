package com.twelvevoltbolt.gallium.commands;

import com.twelvevoltbolt.gallium.MathUtils;
import com.twelvevoltbolt.gallium.RobotMap;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author code
 */
public class VacuumArmLiftCommand extends CommandBase {
    
    boolean moveArmsUp = false;
    
    public VacuumArmLiftCommand(boolean moveArmsUp) {
        requires(arms);
        this.moveArmsUp = moveArmsUp;
    }

    // Called just before this command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this command is scheduled to run
    protected void execute() {
        if (getMovesArmsUp()) {
            if (timeSinceInitialized() < RobotMap.vacuumArmSlowTime) {
                double armValue = MathUtils.lerp(RobotMap.vacuumArmUpSpeed, RobotMap.vacuumArmUpSlowSpeed, timeSinceInitialized() / RobotMap.vacuumArmSlowTime);
                arms.setArmSpeed(armValue / 100.0);
            } else {
                arms.setArmSpeed(0);
            }
        } else {            
            arms.setArmSpeed(0.75);
            Timer.delay(1);
        }
    }
    
    protected boolean getMovesArmsUp() {
        return moveArmsUp;
    }

    // Make this return true when this command no longer needs to run execute()
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