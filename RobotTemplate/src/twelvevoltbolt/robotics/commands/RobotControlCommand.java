package twelvevoltbolt.robotics.commands;

import twelvevoltbolt.robotics.controls.AdvancedDrive;
import twelvevoltbolt.robotics.robots.discbot.DiscRobot;

public abstract class RobotControlCommand extends SimpleCommand {
    private DiscRobot robot;
    private AdvancedDrive robotDrive;
    
    public RobotControlCommand(DiscRobot robot, AdvancedDrive robotDrive) {
        this.robot = robot;
        this.robotDrive = robotDrive;
    }
    
    public DiscRobot getRobot() {
        return robot;
    }
    
    public void setRobot(DiscRobot robot) {
        this.robot = robot;
    }
    
    public AdvancedDrive getRobotDrive() {
        return robotDrive;
    }
    
    public void setRobotDrive(AdvancedDrive robotDrive) {
        this.robotDrive = robotDrive;
    }
}
