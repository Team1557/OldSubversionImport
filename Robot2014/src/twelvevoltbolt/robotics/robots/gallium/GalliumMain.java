package twelvevoltbolt.robotics.robots.gallium;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import twelvevoltbolt.robotics.controls.AdvancedDrive;
import twelvevoltbolt.robotics.commands.SimpleCommand;
import twelvevoltbolt.robotics.controls.NumberModifier;
import twelvevoltbolt.robotics.commands.TankDriveCommand;
import twelvevoltbolt.robotics.dashboard.AdvancedDashboard;
import twelvevoltbolt.robotics.mechanics.RegulatedCompressor;

/**
 * <h3>Buttons</h3>
 * <ul>
 * <li>No - Test whether having the "reverseButton" on from a previous TeleOp
 * period leaves the system reversed while in Autonomous, by first testing
 * autonomous, observing the direction of the wheels, then reversing the
 * direction in teleop and repeating autonomous.</li>
 * </ul>
 *
 * <h3>SmartDashboard</h3>
 * <ul>
 * <li>No - Run code with and without SmartDashboard to see whether the code
 * will crash on either.</li>
 * <li>No - Test whether the displayed team information on the SmartDashboard is
 * correct.</li>
 * <li>No - Examine SmartDashboard to check if all added elements are
 * present.</li>
 * <li>No - Test the "Test Function" button to see if it outputs "Ran test
 * function!"</li>
 * <li>No - Test the autonomous select, first without changing anything to see
 * if the default will be run.</li>
 * <li>No - Change the autonomous select to see if it a.) Doesn't run the
 * default program and b.) Runs the desired/selected program.</li>
 * </ul>
 */
public class GalliumMain extends IterativeRobot {
    // ID Constants
    public static int JOYSTICK_LEFT = 1;
    public static int JOYSTICK_RIGHT = 2;
    public static int JOYSTICK_ALTERNATE = 3;
    
    public static int RELAY_COMPRESSOR = 1;
    public static int DIGITAL_INPUT_COMPRESSOR_SHUTOFF = 1;
    public static int VICTOR_ARM = 7;
    
    public static int SOLENOID_LIFTER_1 = 1;
    public static int SOLENOID_LIFTER_2 = 2;
    public static int SOLENOID_SHIFTER_1 = 3;
    public static int SOLENOID_SHIFTER_2 = 4;
    public static int SOLENOID_DUMPER_1 = 5;
    public static int SOLENOID_DUMPER_2 = 6;
    
    public static int JAGUAR_MOTOR_LEFT_1 = 1;
    public static int JAGUAR_MOTOR_LEFT_2 = 2;
    public static int JAGUAR_MOTOR_RIGHT_1 = 3;
    public static int JAGUAR_MOTOR_RIGHT_2 = 4;
    
    public static int BUTTON_LIFTER = 5;
    public static int BUTTON_SHIFTER = 2;
    public static int BUTTON_DUMPER = 2;
    public static int BUTTON_REVERSE = 5;
    public static int BUTTON_ARM = 1;
    
    // Drive System
    public static AdvancedDrive robotDrive;
    public TankDriveCommand tankDriveCommand;
    public GalliumRobotAutonomous defaultAutonomous;
    public GalliumRobotTeleop teleop;
    
    // Joysticks
    public Joystick leftJoystick = new Joystick(JOYSTICK_LEFT);
    public Joystick rightJoystick = new Joystick(JOYSTICK_RIGHT);
    public Joystick altJoystick = new Joystick(JOYSTICK_ALTERNATE);
    
    // UI
    public AdvancedDashboard dashboard;
    public SendableChooser autonomousSelect;
    
    // Mechanical
    public RegulatedCompressor compressor = RegulatedCompressor.fromIds(RELAY_COMPRESSOR, DIGITAL_INPUT_COMPRESSOR_SHUTOFF);
    public Victor armVictor = new Victor(VICTOR_ARM);
    
    public DoubleSolenoid lifterSolenoid = new DoubleSolenoid(SOLENOID_LIFTER_1, SOLENOID_LIFTER_2);
    public DoubleSolenoid shifterSolenoid = new DoubleSolenoid(SOLENOID_SHIFTER_1, SOLENOID_SHIFTER_2);
    public Solenoid dumperOffSolenoid = new Solenoid(SOLENOID_DUMPER_1);
    public Solenoid dumperOnSolenoid = new Solenoid(SOLENOID_DUMPER_2);
    
    public boolean lifterOn = false;
    public boolean shifterOn = false;
    public boolean dumperOn = false; 
    
    public GalliumRobotButtons buttons;

    public GalliumMain() {
    }
    
    public void robotInit() {
        System.out.println("Hello, World.");
        
        dashboard = new AdvancedDashboard();
        
        // GalliumMain Drive
        robotDrive = AdvancedDrive.fromJaguarIds(JAGUAR_MOTOR_LEFT_1, JAGUAR_MOTOR_LEFT_2, JAGUAR_MOTOR_RIGHT_1, JAGUAR_MOTOR_RIGHT_2, NumberModifier.SQUARED);
        getWatchdog().setEnabled(true);
        robotDrive.setSafetyEnabled(true);
        
        tankDriveCommand = new TankDriveCommand(robotDrive, leftJoystick, rightJoystick);
        
        // Create Control commands;
        defaultAutonomous = new GalliumRobotAutonomous(this, robotDrive);
        teleop = new GalliumRobotTeleop(this, robotDrive);
        
        // Set up joystick buttons
        buttons = new GalliumRobotButtons(this);
        
        // Output stuffs
        System.out.println("Battery Voltage: " + DriverStation.getInstance().getBatteryVoltage());
        System.out.println("On a Field Management System: " + DriverStation.getInstance().isFMSAttached());
        System.out.println("Alliance name: " + DriverStation.getInstance().getAlliance().name);
        System.out.println("Station Number: " + DriverStation.getInstance().getLocation());
        System.out.println("Team Number: " + DriverStation.getInstance().getTeamNumber());
        
        // SmartDashboard stuffs
        SmartDashboard.putBoolean("Field Managed", DriverStation.getInstance().isFMSAttached());
        SmartDashboard.putInt("Team Number", DriverStation.getInstance().getTeamNumber());
        SmartDashboard.putData("Test Button", new SimpleCommand() {public void initialize() {System.out.println("Ran test function!");}});
        
        // Add the Autonomous modes.
        autonomousSelect = new SendableChooser();
        autonomousSelect.addDefault("Default Autonomnous", defaultAutonomous);
        autonomousSelect.addObject("Alternate", new SimpleCommand() {public void initialize() {System.out.println("Running alternate!");}});
        SmartDashboard.putData("Autonomous Chooser", autonomousSelect);
    }

    // Disabled Functions
    public void disabledInit() {
        System.out.println("Disabled");
    }

    public void disabledPeriodic() {
        compressor.regulate();
    }

    public void disabledContinuous() {}

    // Autonomous Functions
    public void autonomousInit() {
        System.out.println("Autonomous");

        //Command command = (Command) autonomousSelect.getSelected();
        //command.start();
      
        getWatchdog().setEnabled(false);
        robotDrive.setSafetyEnabled(false);

        defaultAutonomous.initialize();
        
        robotDrive.drive(0.0, 0.0);
    }

    public void autonomousPeriodic() {
        compressor.regulate();
        getWatchdog().feed();
    }
    
    public void autonomousContinuous() {
        
    }

    public void teleopInit() {
        System.out.println("Teleoperated");
        
        getWatchdog().setEnabled(true);
        robotDrive.setSafetyEnabled(true);
        
        teleop.initialize();
    }

    public void teleopPeriodic() {
        compressor.regulate();
        dashboard.update();
        getWatchdog().feed();
        teleop.execute();
        getWatchdog().feed();
    }
    
    public void teleopContinuous() {}
}