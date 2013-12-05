package twelvevoltbolt.robotics.commands;

import twelvevoltbolt.robotics.controls.AdvancedDrive;
import twelvevoltbolt.robotics.robots.gallium.GalliumMain;

public abstract class RobotControlCommand extends SimpleCommand {
    private GalliumMain robot;
    private AdvancedDrive robotDrive;
    
    public RobotControlCommand(GalliumMain robot, AdvancedDrive robotDrive) {
        this.robot = robot;
        this.robotDrive = robotDrive;
    }
    
    public GalliumMain getRobot() {
        return robot;
    }
    
    public void setRobot(GalliumMain robot) {
        this.robot = robot;
    }
    
    public AdvancedDrive getRobotDrive() {
        return robotDrive;
    }
    
    public void setRobotDrive(AdvancedDrive robotDrive) {
        this.robotDrive = robotDrive;
    }
}
