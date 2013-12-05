package twelvevoltbolt.robotics.commands;

import edu.wpi.first.wpilibj.Joystick;
import twelvevoltbolt.robotics.controls.AdvancedDrive;

public class ArcadeDriveCommand extends DriveCommand {
    private Joystick joystick;
    
    public ArcadeDriveCommand(AdvancedDrive drive, Joystick joystick) {
        super(drive);
        this.joystick = joystick;
    }
    
    public void drive() {
        double mul = getDrive().getMultiplier() * (getDrive().isReversed() ? -1 : 1);
        
        double joystickForward = getJoystickAxis(getJoystick(), Joystick.AxisType.kY) * mul;
        double joystickSideways = getJoystickAxis(getJoystick(), Joystick.AxisType.kX) * mul;
        
        this.getDrive().arcadeDrive(-joystickForward, joystickSideways);
    }

    public Joystick getJoystick() {
        return joystick;
    }

    public void setJoystick(Joystick joystick) {
        this.joystick = joystick;
    }
    
}
