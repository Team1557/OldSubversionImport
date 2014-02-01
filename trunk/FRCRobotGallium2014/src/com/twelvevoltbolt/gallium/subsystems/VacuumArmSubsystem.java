package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import com.twelvevoltbolt.gallium.commands.CommandBase;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

public class VacuumArmSubsystem extends Subsystem {

    public CANJaguar vacuumArm;

    public VacuumArmSubsystem() throws CANTimeoutException {
        vacuumArm = new CANJaguar(RobotMap.vacuumArmMotor);
    }
    
    protected void initDefaultCommand() {
    }
    
    public void setArmSpeed(double speed) {
        try {
            if (CommandBase.vacuum.isSucks()) {
                if (speed > 0) {
                    speed = Math.min(speed, 0.5);
                } else {
                    speed = Math.max(speed, -0.5);
                }
            }
            
            vacuumArm.setX(speed);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public double getSpeed() {
        try {
            return vacuumArm.getSpeed();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
