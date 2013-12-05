package twelvevoltbolt.robotics.robots.gallium;

import edu.wpi.first.wpilibj.Joystick;
import twelvevoltbolt.robotics.commands.RobotControlCommand;
import twelvevoltbolt.robotics.controls.AdvancedDrive;

public class GalliumRobotTeleop extends RobotControlCommand {
    public GalliumRobotTeleop(GalliumMain robot, AdvancedDrive robotDrive) {
        super(robot, robotDrive);
    }
    
    public void initialize() {
        
    }

    protected void execute() {
        GalliumMain robot = getRobot();
        
        if (robot.buttons.armButton.isPressed()) {
            robot.armVictor.set(robot.altJoystick.getAxis(Joystick.AxisType.kY) / 2.5);
        } else {
            robot.armVictor.set(0);
        }
        
        robot.buttons.update();
        robot.robotDrive.drive(robot.tankDriveCommand);
    }
}
