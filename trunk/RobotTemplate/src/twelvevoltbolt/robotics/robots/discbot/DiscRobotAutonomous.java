package twelvevoltbolt.robotics.robots.discbot;

import edu.wpi.first.wpilibj.Timer;
import twelvevoltbolt.robotics.commands.RobotControlCommand;
import twelvevoltbolt.robotics.controls.AdvancedDrive;

public class DiscRobotAutonomous extends RobotControlCommand {
    public DiscRobotAutonomous(DiscRobot robot, AdvancedDrive robotDrive) {
        super(robot, robotDrive);
    }
    
    public void initialize() {
        System.out.println("Running default autonomous!");
        
        for (int i = 0; i < 4; i++) {
            getRobotDrive().drive(0.5, 0.0); // drive 50% forward with 0% turn
            getRobot().getWatchdog().feed();
            Timer.delay(1.0);
            getRobot().getWatchdog().feed();
            getRobotDrive().drive(0.0, 0.75); // drive 0% forward and 75% turn
            getRobot().getWatchdog().feed();
            Timer.delay(1.0);
            getRobot().getWatchdog().feed();
        }
        getRobotDrive().drive(0.0, 0.0); // drive 0% forward, 0% turn (stop)
    }
}
