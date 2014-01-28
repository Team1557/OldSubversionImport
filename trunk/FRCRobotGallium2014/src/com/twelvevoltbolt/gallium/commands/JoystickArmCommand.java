/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.twelvevoltbolt.gallium.commands;

import edu.wpi.first.wpilibj.Joystick;


public class JoystickArmCommand extends VacuumArmLiftCommand {

    public JoystickArmCommand() {
        super(false);
    } 
    
    protected boolean getMovesArmsUp() {
        return oi.alt.getAxis(Joystick.AxisType.kY) < 0;
    }
}
