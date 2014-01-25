package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

public class VacuumSubsystem extends Subsystem {

    private DigitalOutput vacuum;
    public CANJaguar vacuum1;
    public CANJaguar vacuum2;
    
    public VacuumSubsystem() throws CANTimeoutException {
        vacuum = new DigitalOutput(1);
        vacuum1 = new CANJaguar(RobotMap.vacuumMotor1);
        vacuum2 = new CANJaguar(RobotMap.vacuumMotor2);
    }
    
    protected void initDefaultCommand() {
    }
    
    public void setSuck(boolean sucks) {
        vacuum.set(sucks);
    }
}
