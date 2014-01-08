
package com.twelvevoltbolt.gallium;

import com.twelvevoltbolt.gallium.commands.ReverseCommand;
import com.twelvevoltbolt.gallium.commands.TestCommand;
import com.twelvevoltbolt.gallium.commands.VacuumSuckCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    Joystick left = new Joystick(1);
    Joystick right = new Joystick(2);
    Joystick alt = new Joystick(3);
    
    Button reverse = new JoystickButton(left, 1);
    Button suckButton = new JoystickButton(alt, 2);
    Button testButton = new JoystickButton(alt, 1);

    public OI() {
        reverse.whenPressed(new ReverseCommand());
        
        testButton.whileHeld(new TestCommand());
        suckButton.whileHeld(new VacuumSuckCommand());
    }
    
    public double getLeftJoystick() {
        return left.getAxis(Joystick.AxisType.kY);
    }
    
    public double getRightJoystick() {
        return right.getAxis(Joystick.AxisType.kY);
    }
}

