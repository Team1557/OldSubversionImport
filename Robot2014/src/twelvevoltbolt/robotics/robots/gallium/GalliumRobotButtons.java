package twelvevoltbolt.robotics.robots.gallium;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import twelvevoltbolt.robotics.commands.SimpleCommand;
import twelvevoltbolt.robotics.controls.AdvancedJoystickButton;

public class GalliumRobotButtons {
    public AdvancedJoystickButton armButton;
    public AdvancedJoystickButton lifterButton;
    public AdvancedJoystickButton shifterButton;
    public AdvancedJoystickButton reverseButton;
    public AdvancedJoystickButton dumperButton;
    
    private GalliumMain robot;
    public GalliumRobotButtons(GalliumMain robot) {
        this.robot = robot;
        
        armButton = new AdvancedJoystickButton(robot.altJoystick, GalliumMain.BUTTON_ARM);
        lifterButton = new AdvancedJoystickButton(robot.altJoystick, GalliumMain.BUTTON_LIFTER);
        shifterButton = new AdvancedJoystickButton(robot.leftJoystick, GalliumMain.BUTTON_SHIFTER);
        reverseButton = new AdvancedJoystickButton(robot.leftJoystick, GalliumMain.BUTTON_REVERSE);
        dumperButton = new AdvancedJoystickButton(robot.altJoystick, GalliumMain.BUTTON_DUMPER);
    }
    
    public void update() {
        lifterButton.whenPressed(lifterButtonCommand);
        shifterButton.whenPressed(shifterButtonCommand);
        reverseButton.whenPressed(reverseButtonCommand);
        dumperButton.whenPressed(dumperButtonCommand);
    }
    
    public SimpleCommand lifterButtonCommand = new SimpleCommand() {
        public void initialize() {
            robot.lifterOn = !robot.lifterOn;
            System.out.println("Lifter: " + robot.lifterOn);
            
            robot.lifterSolenoid.set(robot.lifterOn ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
        }
    };
    
    public SimpleCommand shifterButtonCommand = new SimpleCommand() {
        public void initialize() {
            robot.shifterOn = !robot.shifterOn;
            System.out.println("Shifter: " + robot.shifterOn);
            
            robot.shifterSolenoid.set(robot.shifterOn ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
        }
    };
    
    public SimpleCommand reverseButtonCommand = new SimpleCommand() {
        public void initialize() {
            robot.robotDrive.setReversed(!robot.robotDrive.isReversed());
        }
    };
    
    public SimpleCommand dumperButtonCommand = new SimpleCommand() {
        public void initialize() {
            System.out.println(robot.dumperOn);
            robot.dumperOn = !robot.dumperOn;
            
            robot.dumperOnSolenoid.set(robot.dumperOn);
            robot.dumperOffSolenoid.set(!robot.dumperOn);
        }
    };
}
