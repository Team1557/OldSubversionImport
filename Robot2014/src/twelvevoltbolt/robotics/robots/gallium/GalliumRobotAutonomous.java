package twelvevoltbolt.robotics.robots.gallium;

import edu.wpi.first.wpilibj.Timer;
import twelvevoltbolt.robotics.commands.RobotControlCommand;
import twelvevoltbolt.robotics.controls.AdvancedDrive;

public class GalliumRobotAutonomous extends RobotControlCommand {

    public GalliumMain robot = null;

    public GalliumRobotAutonomous(GalliumMain robot, AdvancedDrive robotDrive) {
        super(robot, robotDrive);
        this.robot = robot;
    }

    public void initialize() {
        System.out.println("Running default autonomous!");

        //for (int i = 0; i < 4; i++) {
        for (int seconds = 0; seconds < 5; seconds++) {
            getRobotDrive().drive(0.5, 0.0); // drive 50% forward with 0% turn
            getRobot().getWatchdog().feed();
            Timer.delay(1.0);
        }
        /*getRobot().getWatchdog().feed();
         getRobotDrive().drive(0.0, 0.75); // drive 0% forward and 75% turn
         Timer.delay(1.0);
         getRobot().getWatchdog().feed();
         */
        getRobotDrive().drive(0.0, 0.0);
        getRobot().getWatchdog().feed();
        Timer.delay(0.5);
        getRobot().getWatchdog().feed();
        robot.dumperOnSolenoid.set(true);
        robot.dumperOffSolenoid.set(false);
        Timer.delay(1.0);
        getRobot().getWatchdog().feed();
        robot.dumperOnSolenoid.set(false);
        robot.dumperOffSolenoid.set(true);
        //}
        Timer.delay(1.0);
        getRobotDrive().drive(0.0, 0.0); // drive 0% forward, 0% turn (stop)
    }
}
