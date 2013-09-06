/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/*----------------------------------------------------------------------------*/
package twelvevoltbolt.robotics.robots.discbot;

import edu.wpi.first.wpilibj.DigitalInput;
import java.util.Vector;

import twelvevoltbolt.robotics.controls.AdvancedDrive;
import twelvevoltbolt.robotics.commands.ButtonCommand;

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

public class DiscRobot extends IterativeRobot {
    AdvancedDrive robotDrive;
    
    Joystick leftJoystick = new Joystick(1);
    Joystick rightJoystick = new Joystick(2);
    Joystick altJoystick = new Joystick(3);
    
    TankDriveCommand tankDriveCommand;
    ArcadeDriveCommand arcadeDriveCommand;
    
    Relay compressor = new Relay(1);
    DigitalInput compressorShutoff = new DigitalInput(1);
    
    /**
     * The motor that controls the arm that grabs onto the pyramid.
     */
    Victor armVictor = new Victor(7);
    
    /**
     * The button that, when pressed, allows the arm to move at the same speed
     * as the altJoystick's Y axis.
     */
    AdvancedJoystickButton armButton = new AdvancedJoystickButton(altJoystick, 1);
    
    /**
     * The solenoid that controls the lifter, to get the robot off the ground.
     */
    DoubleSolenoid lifterSolenoid = new DoubleSolenoid(1, 2);
    
    /**
     * The button that toggles the lifter.
     */
    AdvancedJoystickButton lifterButton = new AdvancedJoystickButton(altJoystick, 4);
    
    ButtonCommand lifterButtonCommand = new ButtonCommand() {
            public void initialize() {
                lifterOn = !lifterOn;
                lifterSolenoid.set(lifterOn ? 
					DoubleSolenoid.Value.kForward : 
					DoubleSolenoid.Value.kReverse);
            }
        };
    
    /**
     * Whether the lifter is up.
     */
    boolean lifterOn = false;
    
    /**
     * The button that, when pressed, reverses the direction and 'side' of the motors.
     */
    AdvancedJoystickButton reverseButton = new AdvancedJoystickButton(altJoystick, 5);
    
    ButtonCommand reverseButtonCommand = new ButtonCommand() {
        public void initialize() {
            System.out.println("Setting robot reversed to " + (!robotDrive.isReversed()));
            robotDrive.setReversed(!robotDrive.isReversed());
        }
    };
    
    public DiscRobot() {
        getWatchdog().setEnabled(true);
    }
    
    public void regulateCompressor() {
        compressor.set(compressorShutoff.get() ? Relay.Value.kOff : Relay.Value.kForward);
    }
    
    public void robotInit() {
        robotDrive = AdvancedDrive.fromJaguarIds(1, 2, 3, 4, NumberModifier.SQUARED);
        robotDrive.setSafetyEnabled(false);
        
        tankDriveCommand = new TankDriveCommand(robotDrive, leftJoystick, rightJoystick);
        arcadeDriveCommand = new ArcadeDriveCommand(robotDrive, leftJoystick);
        
        compressor.set(Relay.Value.kForward);
        
        Vector keys = edu.wpi.first.wpilibj.Preferences.getInstance().getKeys();
        for (int i = 0; i < keys.size(); i++) {
            String key = (String)keys.elementAt(i);
            System.out.println("CRIO key: " + key);
        }
    }
    
	/**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomousInit() {
        for (int i = 0; i < 4; i++) {
            robotDrive.drive(0.5, 0.0); // drive 50% forward with 0% turn
            Timer.delay(1.0); // wait 1 second
            robotDrive.drive(0.0, 0.75); // drive 0% forward and 75% turn
            Timer.delay(1.0); // wait 1 second
        }
        
		robotDrive.drive(0.0, 0.0); // stop
    }

    public void autonomousPeriodic() {
        regulateCompressor();
    }
    
    /**
     * This function is called at a rate of ~100hz.
     */
    public void teleopPeriodic() {
        regulateCompressor();
        if (armButton.isPressed()) {
            armVictor.set(altJoystick.getAxis(Joystick.AxisType.kY) / 2.5);
        }
        else {
            armVictor.set(0);
        }
        
        lifterButton.whenPressed(lifterButtonCommand);
        reverseButton.whenPressed(reverseButtonCommand);
        dumperButton.whenPressed(dumperButtonCommand);
        
        robotDrive.drive(tankDriveCommand);
    }
}