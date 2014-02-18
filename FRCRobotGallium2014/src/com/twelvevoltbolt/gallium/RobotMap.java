package com.twelvevoltbolt.gallium;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // Pair of left side motors
    public static final int leftMotor1 = 2; // Jaguar port
    public static final int leftMotor2 = 3; // Jaguar port
    
    // Pair of right side motors
    public static final int rightMotor1 = 4; // Jaguar port
    public static final int rightMotor2 = 5; // Jaguar port
    
    // Ultrasonic screwdriver
    public static final int ultra = 2;
    public static final int pingChannel = 2;
    public static final int echoChannel = 2;
    
    // SuperShifters
    public static final int superShifter1 = 5;
    public static final int superShifter2 = 6;
    
    // Vacuum
    public static int vacuum = 1;
    
    public static int vacuumMotor1 = 6;
    public static int vacuumMotor2 = 7;
    public static int vacuumArmMotor = 8;
    
    // The arm launcher
    public static final int leftArmLauncherOn = 1;
    public static final int leftArmLauncherOff = 2;
    
    public static final int rightArmLauncherOn = 4;
    public static final int rightArmLauncherOff = 3;
    
    // Port of relay for compressor
    public static final int compressorRelay = 1;
    
    // Port of digital input compressor shutoff
    public static final int compressorShutoffDigitalInput = 1;
    
    
    
    
    /**
     * How long the transition between fast and slow speed takes
     */
    public static double vacuumArmSlowTime = 0.5;
    
    public static int vacuumArmDownSpeed = 75;
    
    /**
     * The starting speed of the arm as it goes up
     */
    public static int vacuumArmUpSpeed = 100;
    /**
     * The speed the arm ends at
     */
    public static int vacuumArmUpSlowSpeed = 20;
    
    // The speed at which the ramp-down starts to apply
    public static double motorRampStart = 0.4;
    public static double motorRampStep = 0.1;
    
    /**
     * Motor Shift Speeds
     */
    public static int speedShiftUp;
    public static int speedShiftDown;
}
