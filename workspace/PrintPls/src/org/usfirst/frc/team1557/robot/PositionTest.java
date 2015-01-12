
package org.usfirst.frc.team1557.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class PositionTest extends IterativeRobot {
	
	public void robotInit() {
		
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		

	}
	
	@Override
	public void teleopInit() {
		double pos = 0.0;
		double speed = 0.0;
		double acceleration = 0.0;
		double avgError = 0.0;
		final int avgingTime = 1000; 
		
		BuiltInAccelerometer accel = new BuiltInAccelerometer(Range.k2G);
		
		for(int i = 0; i < avgingTime; i++) {
			avgError += accel.getY();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
		avgError /= avgingTime;
		
		for(int j = 0; j < 1000; j++) {
			for(int i = 0; i < 10; i++) {
				acceleration = accel.getY() - avgError;
				speed += (acceleration / 1000);
				pos += (speed / 1000);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}
			SmartDashboard.putNumber("Acceleration", acceleration);
			SmartDashboard.putNumber("Speed", speed * 10);
			SmartDashboard.putNumber("Position", pos * 100);
		}
	}
	
	
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
        
		
	}
	

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}
	public void disabledInit(){
		
		
	}
}
