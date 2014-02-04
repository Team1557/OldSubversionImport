package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
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
    
    //double maxValue = 1;
    
    public void setArmSpeed(double speed) {
        try {
            /*if (speed > 0.1) {
                maxValue = 1;
            } else if (speed < 0.1) {
                maxValue -= 0.07;
            }
            
            if (maxValue < 0.2) {
                maxValue = 0.2;
            }
            
            if (Math.abs(speed) > maxValue) {
                if (speed < 0) {
                    speed = -maxValue;
                } else {
                    speed = maxValue;
                }
            }
            
            System.out.println("Vacuum arm: " + speed);
            */
            vacuumArm.setX(-speed);
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
