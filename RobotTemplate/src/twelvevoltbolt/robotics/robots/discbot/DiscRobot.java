/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package twelvevoltbolt.robotics.robots.discbot;

import edu.wpi.first.wpilibj.DigitalInput;
import java.util.Vector;
import twelvevoltbolt.robotics.controls.AdvancedDrive;
import twelvevoltbolt.robotics.commands.SimpleCommand;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import twelvevoltbolt.robotics.controls.AdvancedJoystickButton;
import twelvevoltbolt.robotics.controls.NumberModifier;
import twelvevoltbolt.robotics.commands.ArcadeDriveCommand;
import twelvevoltbolt.robotics.commands.TankDriveCommand;
import twelvevoltbolt.robotics.dashboard.AdvancedDashboard;

/**
 * <h1>Tasks:</h1>
 * <h3>General</h3>
 * <ul>
 * <li>No - Determine what the correct Class is for motor controls such as the
 * arms and lifters, ie: Solenoid, Relay, CANJaguar.</li>
 * <li>No - Determine the correct channels for solenoids, lifters, buttons, etc,
 * then change their defined values in code.</li>
 * <li>Yes - Test whether robot loads the code without errors.</li>
 * <li>Yes - Determine if the robot outputs team information, etc on call to
 * robotInit(), and note the information when loading code, and when enabling
 * from driver station.</li>
 * <li>No - Try re-enabling safety on the robotDrive, and see if safety checks
 * kick in.</li>
 * </ul>
 *
 * <h3>AdvancedDrive</h3>
 * <ul>
 * <li>Yes - Test whether the joysticks control the robot.</li>
 * <li>Yes - Test whether the joysticks control the motors in a gradual increase
 * with the square function.</li>
 * <li>Yes - Test whether direction is correct by default.</li>
 * <li>No - Test arcade drive</li>
 * <li>No - Test sine drive</li>
 * </ul>
 *
 * <h3>Buttons</h3>
 * <ul>
 * <li>No - Test whether the "reverseButton" toggles the reverse, and confirm a
 * switch in both motor direction and motor side.</li>
 * <li>Yes - Test whether the "lifterButton" toggles lifterOn, by watching the
 * output upon pressing the button.</li>
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
public class DiscRobot extends IterativeRobot {
    private AdvancedDashboard dashboard;
    
    AdvancedDrive robotDrive;
    Joystick leftJoystick = new Joystick(1);
    Joystick rightJoystick = new Joystick(2);
    Joystick altJoystick = new Joystick(3); //Hi Taylor!
    TankDriveCommand tankDriveCommand;
    ArcadeDriveCommand arcadeDriveCommand;
    Relay compressor = new Relay(1);
    DigitalInput compressorShutoff = new DigitalInput(1);
    /**
     * A radio-button select for the autonomous mode Command.
     */
    SendableChooser autonomousSelect;
    // TODO: Determine the channel and motor type the arm is on.
    /**
     * The motor that controls the arm that grabs onto the pyramid.
     */
    Victor armVictor = new Victor(7);
    /**
     * The button that, when pressed, allows the arm to move at the same speed
     * as the altJoystick's Y axis.
     */
    AdvancedJoystickButton armButton = new AdvancedJoystickButton(altJoystick, 1);
    DoubleSolenoid lifterSolenoid = new DoubleSolenoid(1, 2);
    DoubleSolenoid shifterSolenoid = new DoubleSolenoid(3, 4);
    /**
     * The button that toggles the lifter.
     */
    AdvancedJoystickButton lifterButton = new AdvancedJoystickButton(altJoystick, 4);
    SimpleCommand lifterButtonCommand = new SimpleCommand() {
        public void initialize() {
            lifterOn = !lifterOn;
            System.out.println("Would have changed lifter to " + lifterOn);
            // off, rev //
            lifterSolenoid.set(lifterOn ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
        }
    };
    
    AdvancedJoystickButton shifterButton = new AdvancedJoystickButton(altJoystick, 5);
    SimpleCommand shifterButtonCommand = new SimpleCommand() {
        public void initialize() {
            shifterOn = !shifterOn;
            System.out.println("Would have changed shifter to " + shifterOn);
            
            
            
            // off, rev //
            shifterSolenoid.set(shifterOn ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
        }
    };
    
    //Robot 1 & 2 lifter. Robot 3 & 4 Shifters.
    /**
     * Whether the lifter is up.
     */
    boolean lifterOn = false;
    boolean shifterOn = false;
    /**
     * The button that, when pressed, reverses the direction and 'side' of the
     * motors.
     */
    AdvancedJoystickButton reverseButton = new AdvancedJoystickButton(leftJoystick, 5);
    SimpleCommand reverseButtonCommand = new SimpleCommand() {
        public void initialize() {
            System.out.println("Setting robot reversed to " + (!robotDrive.isReversed())); //Hi again Taylor!
            robotDrive.setReversed(!robotDrive.isReversed());
        }
    };
   // DoubleSolenoid dumper = new DoubleSolenoid(5, 6);
    Solenoid dumperOn = new Solenoid(5);
    Solenoid dumperOff = new Solenoid(6);
    boolean dumperIsOn = false;
    AdvancedJoystickButton dumperButton = new AdvancedJoystickButton(altJoystick, 2);
    SimpleCommand dumperButtonCommand = new SimpleCommand() {
        public void initialize() {
            System.out.println(dumperIsOn);
            dumperIsOn = !dumperIsOn;

            dumperOn.set(dumperIsOn);
            dumperOff.set(!dumperIsOn);
            //dumper.set(dumperIsOn ? DoubleSolenoid.Value.kOff : DoubleSolenoid.Value.kReverse);
            
        }
    };

    public DiscRobot() {
        getWatchdog().setEnabled(true);
    }

    public void regulateCompressor() {
        compressor.set(compressorShutoff.get() ? Relay.Value.kOff : Relay.Value.kForward);
    }

    public void robotInit() {
        dashboard = new AdvancedDashboard();
        
        System.out.println("Entering robotInit()."); //Taylor!

        robotDrive = AdvancedDrive.fromJaguarIds(1, 2, 3, 4, NumberModifier.SQUARED);
        robotDrive.setSafetyEnabled(true);

        tankDriveCommand = new TankDriveCommand(robotDrive, leftJoystick, rightJoystick);
        arcadeDriveCommand = new ArcadeDriveCommand(robotDrive, leftJoystick);

        compressor.set(Relay.Value.kForward);

        System.out.println("Battery Voltage: " + DriverStation.getInstance().getBatteryVoltage());
        System.out.println("On a Field Management System: " + DriverStation.getInstance().isFMSAttached());
        System.out.println("Alliance name: " + DriverStation.getInstance().getAlliance().name);
        System.out.println("Station Number: " + DriverStation.getInstance().getLocation());
        System.out.println("Team Number: " + DriverStation.getInstance().getTeamNumber());

        SmartDashboard.putBoolean("Field Managed", DriverStation.getInstance().isFMSAttached());
        SmartDashboard.putInt("Team Number", DriverStation.getInstance().getTeamNumber());
        SmartDashboard.putData("Test Function", new SimpleCommand() {
            public void initialize() {
                System.out.println("Ran test function!");
            }
        });

        autonomousSelect = new SendableChooser();
        autonomousSelect.addDefault("Default Autonomnous", new SimpleCommand() {
            public void initialize() {
                System.out.println("Running default autonomous!");

                for (int i = 0; i < 4; i++) {
                    robotDrive.drive(0.5, 0.0); // drive 50% forward with 0% turn
                    getWatchdog().feed();
                    Timer.delay(1.0); // wait 1 seconds
                    getWatchdog().feed();
                    robotDrive.drive(0.0, 0.75); // drive 0% forward and 75% turn
                    getWatchdog().feed();
                    Timer.delay(1.0); // wait 1 seconds
                    getWatchdog().feed();
                }
                robotDrive.drive(0.0, 0.0); // drive 0% forward, 0% turn (stop)
            }
        });
        autonomousSelect.addObject("Alternate 1", new SimpleCommand() {
            public void initialize() {
                System.out.println("Running the alternate 1!");
            }
        });
        autonomousSelect.addObject("Alternate 2", new SimpleCommand() {
            public void initialize() {
                System.out.println("Running the alternate 2!");
            }
        }); //Taylor! Hi!
        SmartDashboard.putData("Autonomous Chooser", autonomousSelect);

        Vector keys = edu.wpi.first.wpilibj.Preferences.getInstance().getKeys();
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.elementAt(i);
            System.out.println("CRIO Preferences contains key: " + key);
        }
    }

    // Disabled Functions
    public void disabledInit() {
        System.out.println("Disabled.");
    }

    public void disabledPeriodic() {
        regulateCompressor();
    }

    public void disabledContinuous() {
    }

    // Autonomous Functions
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomousInit() {
        System.out.println("Enabling autonomous.");

        //Command command = (Command) autonomousSelect.getSelected();
        //command.start(); //Hi

        
         for (int i = 0; i < 4; i++) {
            /*robotDrive.drive(0.5, 0.0); // drive 50% forward with 0% turn
            getWatchdog().feed();
            Timer.delay(1.0); // wait 1 seconds
            getWatchdog().feed();
            robotDrive.drive(0.0, 0.75); // drive 0% forward and 75% turn
            getWatchdog().feed();
            Timer.delay(1.0); // wait 1 seconds
            */
            
       
            System.out.println(dumperIsOn);
            dumperIsOn = !dumperIsOn;

            dumperOn.set(dumperIsOn);
            dumperOff.set(!dumperIsOn);
            getWatchdog().feed();
            Timer.delay(3.0);
            
            getWatchdog().feed();
            
         }
         robotDrive.drive(0.0, 0.0); // drive 0% forward, 0% turn (stop)
    }

    public void autonomousPeriodic() {
        regulateCompressor();
    }

    public void autonomousContinuous() {
    }

    // Tele-operated functions
    /**
     * This function is called once each time the robot enters operator control.
     */
    public void teleopInit() {
        System.out.println("Enabling Teleop.");
    }

    public void teleopPeriodic() {
        dashboard.update();
        regulateCompressor();
        if (armButton.isPressed()) {
            armVictor.set(altJoystick.getAxis(Joystick.AxisType.kY) / 2.5);
        } else {
            armVictor.set(0);
        }


        lifterButton.whenPressed(lifterButtonCommand);
        shifterButton.whenPressed(shifterButtonCommand);
        reverseButton.whenPressed(reverseButtonCommand);
        dumperButton.whenPressed(dumperButtonCommand);

        robotDrive.drive(tankDriveCommand); //Heyyyyyyyyy
    }

    public void teleopContinuous() {
    }
} //End