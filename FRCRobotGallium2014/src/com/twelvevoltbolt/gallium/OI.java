package com.twelvevoltbolt.gallium;

import com.twelvevoltbolt.gallium.commands.FireActionCommand;
import com.twelvevoltbolt.gallium.commands.JoystickArmCommand;
import com.twelvevoltbolt.gallium.commands.ShiftCommand;
import com.twelvevoltbolt.gallium.commands.TrackingCommand;
import com.twelvevoltbolt.gallium.commands.VacuumSuckCommand;
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

    public Joystick left = new Joystick(1);
    public Joystick right = new Joystick(2);
    public Joystick alt = new Joystick(3);
    
    //Button reverse = new JoystickButton(left, 1);
    Button shiftDownButton = new JoystickButton(left, 1);
    Button shiftUpButton = new JoystickButton(right, 1);
    
    Button armControl = new JoystickButton(alt, 1);
    Button suckButton = new JoystickButton(alt, 2);
    Button fireTriggerButton = new JoystickButton(alt, 3);
    Button debugButton = new JoystickButton(alt, 10);
    Button trackButton = new JoystickButton(alt, 11);

    public OI() {
        armControl.whileHeld(new JoystickArmCommand());
        
        shiftDownButton.whenPressed(new ShiftCommand(false));
        shiftUpButton.whenPressed(new ShiftCommand(true));
        
        suckButton.toggleWhenPressed(new VacuumSuckCommand());
        
        // Fire based on joystick input
        fireTriggerButton.whenPressed(new FireActionCommand());
        
        trackButton.toggleWhenPressed(new TrackingCommand());
    }

    public double getLeftInput() {
        double value = left.getAxis(Joystick.AxisType.kY);

        if (Math.abs(value) < 0.05) {
            return 0;
        }
        
        // Square the input
        if (value >= 0) {
            return value * value;
        } else {
            return -(value * value);
        }
    }

    public double getRightInput() {
        double value = right.getAxis(Joystick.AxisType.kY);

        if (Math.abs(value) < 0.05) {
            return 0;
        }
        
        // Square the input
        if (value >= 0) {
            return value * value;
        } else {
            return -(value * value);
        }
    }

    /**
     * Gets the angle to fire at, from -1 to 1
     *
     * @return
     */
    public double getFiringAngle() {
        double value = alt.getAxis(Joystick.AxisType.kX);
        
        if (Math.abs(value) < 0.1) {
            return 0;
        }
        
        return value;
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
