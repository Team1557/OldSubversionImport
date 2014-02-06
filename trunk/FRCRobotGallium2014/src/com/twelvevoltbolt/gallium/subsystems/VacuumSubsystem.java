package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import com.twelvevoltbolt.gallium.commands.CommandBase;
import com.twelvevoltbolt.gallium.commands.VacuumSuckCommand;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

public class VacuumSubsystem extends Subsystem {

    public CANJaguar vacuum1;
    public CANJaguar vacuum2;
    
    public VacuumSubsystem() throws CANTimeoutException {
        vacuum1 = new CANJaguar(RobotMap.vacuumMotor1);
        vacuum2 = new CANJaguar(RobotMap.vacuumMotor2);
    }
    
    protected void initDefaultCommand() {
    }
    
    private boolean sucks = false;
    
    public boolean isSucks() {
        return sucks;
    }
    
    public void setSuck(boolean suck) {
        if (CommandBase.oi.isDebug()) {
            System.out.println("Vacuum: " + suck);
        }
        
        try {
            this.sucks = suck;
            if (suck) {
                vacuum1.setX(+1);
                vacuum2.setX(-1);
            } else {
                vacuum1.setX(0);
                vacuum2.setX(0);
            }
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            
            this.sucks = false;
        }
    }
}
