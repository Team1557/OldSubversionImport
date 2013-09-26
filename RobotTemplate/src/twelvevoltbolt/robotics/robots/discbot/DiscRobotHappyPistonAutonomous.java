package twelvevoltbolt.robotics.robots.discbot;

import edu.wpi.first.wpilibj.Timer;
import twelvevoltbolt.robotics.commands.RobotControlCommand;
import twelvevoltbolt.robotics.controls.AdvancedDrive;

public class DiscRobotHappyPistonAutonomous extends RobotControlCommand {

    public DiscRobotHappyPistonAutonomous(DiscRobot robot, AdvancedDrive robotDrive) {
        super(robot, robotDrive);
    }

    public void initialize() {
        DiscRobot robot = getRobot();
        for (int i = 0; i <= 4; i++) {
            DiscRobot.robotDrive.drive(0.5, 0.0);
            getRobot().getWatchdog().feed();
            Timer.delay(1.0);
            getRobot().getWatchdog().feed();
            getRobotDrive().drive(0.0, 0.0);
//            System.out.println(robot.dumperOn);
//            robot.dumperOn = !robot.dumperOn;
//
//            robot.dumperOnSolenoid.set(robot.dumperOn);
//          robot.dumperOffSolenoid.set(!robot.dumperOn);
            robot.getWatchdog().feed();
            Timer.delay(3.0);
            //Timer.delay(1.0);

            robot.getWatchdog().feed();
        }
    }
}
