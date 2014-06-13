package com.twelvevoltbolt.disk;

import com.twelvevoltbolt.disk.commands.FireActionCommand;
import com.twelvevoltbolt.disk.commands.ShiftCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);

    public Joystick main = new Joystick(1);
    
    //Button reverse = new JoystickButton(main, 1);
    Button shiftDownButton = new JoystickButton(main, 5);
    Button shiftUpButton = new JoystickButton(main, 6);
    
    public Button armControl = new JoystickButton(main, 1);
    Button fireTriggerButton = new JoystickButton(main, 2);
    Button debugButton = new JoystickButton(main, 10);
//    Button trackButton = new JoystickButton(alt, 11);

    public OI() {
        
        shiftDownButton.whenPressed(new ShiftCommand(false));
        shiftUpButton.whenPressed(new ShiftCommand(true));   
        
        // Fire based on joystick input
        fireTriggerButton.whenPressed(new FireActionCommand());
        
//        trackButton.toggleWhenPressed(new TrackingCommand());
    }

    public Joystick getMainJoystick() {
        return main;
    }
    
    public double getArmSpeed() {
        double value = alt.getAxis(Joystick.AxisType.kY);
        
        System.out.println(value);
        if (Math.abs(value) < 0.1) {
            return 0;
        }
        
        return value * .75;
    }

    public boolean isDebug() {
        return debugButton.get();
    }
}
