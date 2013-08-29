/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twelvevoltbolt.robotics.controls;

import edu.wpi.first.wpilibj.Joystick;
import twelvevoltbolt.robotics.commands.SimpleCommand;

/**
 *
 * @author code
 */
public class AdvancedJoystickButton {
    private Joystick joystick;
    private int button;
    
    public AdvancedJoystickButton(Joystick joystick, int button) {
        this.joystick = joystick;
        this.button = button;
    }
    
    private boolean wasPressedLastTime = false;
    
    public boolean isPressed() {
        return joystick.getRawButton(button);
    }
    
    public void whenPressed(SimpleCommand command) {
        if (this.isPressed()) {
            if (!wasPressedLastTime) {
                wasPressedLastTime = true;
                command.initialize();
            }
        }
        else {
            wasPressedLastTime = false;
        }
    }
}
