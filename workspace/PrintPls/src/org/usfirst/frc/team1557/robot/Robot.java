
package org.usfirst.frc.team1557.robot;


import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.RGBImage;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	Joystick mainJoy;
	BuiltInAccelerometer BA;
	CANTalon backRight;
	CANTalon backLeft;
	CANTalon frontRight;
	CANTalon frontLeft;
	RobotDrive tankDrive;
	
	
	
	public void robotInit() {
		
		BA = new BuiltInAccelerometer(Range.k2G);
		frontLeft = new CANTalon(1);
		backLeft = new CANTalon(2);
		frontRight = new CANTalon(3);
		backRight = new CANTalon(4);
		
		tankDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
		
		
		mainJoy = new Joystick(0);
		System.out.println("RobotInit Pls");
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		

	}
	
	@Override
	public void teleopInit() {
	
		
		//command.start();
	}

	
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
        //Scheduler.getInstance().run();
		
        tankDrive.tankDrive(-mainJoy.getRawAxis(1), -mainJoy.getRawAxis(5));
		//Back Right 1
		//Back Left  2
		//Front Left 4
		//Front Right 3
		
	/*	backRight.set(0);
		frontRight.set(0);
		frontLeft.set(0);
		backLeft.set(0);
		//talonOne.set(0.5);
		if(mainJoy.getRawButton(1)){
			backRight.set(1);
		}
		if(mainJoy.getRawButton(2)){
			backLeft.set(1);
		}
		if(mainJoy.getRawButton(3)){
			frontRight.set(1);
		}
		if(mainJoy.getRawButton(4)){
			frontLeft.set(1);
		}
		
		*/
		
		

		//if (Math.abs(x) > .01 && Math.abs(y) > .01 && Math.abs(z - 1) > .01) {
//		if (ticks > 3) {
//			ticks = 0;
//			System.out.printf("X: %8.4f, Y: %8.4f, Z: %8.4f%n", BA.getX(), BA.getY(), BA.getZ() );
//		}

		
		//servoOne.setAngle(Math.atan2(mainJoy.getX(), mainJoy.getY()) * (360.0 / Math.PI));

	


	}
	

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}
	public void disabledInit(){
	
		
	}
}
