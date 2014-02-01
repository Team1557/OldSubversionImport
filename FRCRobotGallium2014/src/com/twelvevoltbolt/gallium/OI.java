
package com.twelvevoltbolt.gallium;

import com.twelvevoltbolt.gallium.commands.FireCommand;
import com.twelvevoltbolt.gallium.commands.JoystickArmCommand;
import com.twelvevoltbolt.gallium.commands.TurnAndAimCommand;
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
    Button suckButton = new JoystickButton(alt, 2);
    Button armControl = new JoystickButton(alt, 1);          

    Button fireTriggerButton = new JoystickButton(alt, 3);

    public OI() {
        armControl.whileHeld(new JoystickArmCommand());
        suckButton.whileHeld(new VacuumSuckCommand());
        
        // Fire based on joystick input
        fireTriggerButton.whenPressed(new FireCommand());
    }
    
    public double getLeftInput() {
        double value = left.getAxis(Joystick.AxisType.kY);
        
        // Square the input
        return value * value;
    }
    
    public double getRightInput() {
        double value = right.getAxis(Joystick.AxisType.kY);
        
        // Square the input
        return value * value;
    }
    
    /**
     * Gets the angle to fire at, from -1 to 1
     * @return 
     */
    public double getFiringAngle() {
        return alt.getAxis(Joystick.AxisType.kX);
    }
}

