package twelvevoltbolt.robotics.commands;

import twelvevoltbolt.robotics.controls.AdvancedDrive;

public class SineDriveCommand extends DriveCommand {
    public SineDriveCommand(AdvancedDrive drive) {
        super(drive);
    }
    
    double i = 0.0;
    
    public void drive() {
        double sine = Math.sin(i);
        this.getDrive().setLeftRightMotorOutputs(sine, sine);
        
        // At 100hz, the default periodic, this is around one cycle every 4 seconds.
        i += .008;
    }
}
