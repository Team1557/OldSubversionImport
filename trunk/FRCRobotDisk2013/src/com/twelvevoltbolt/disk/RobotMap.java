package com.twelvevoltbolt.disk;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // Pair of left side motors
    public static final int leftMotor1 = 1; // Jaguar port
    public static final int leftMotor2 = 2; // Jaguar port
    
    // Pair of right side motors
    public static final int rightMotor1 = 3; // Jaguar port
    public static final int rightMotor2 = 4; // Jaguar port
    
    // SuperShifters
    public static final int superShifter1 = 5;
    public static final int superShifter2 = 6;
    
    public static final int encoderA = 2;
    public static final int encoderB = 3;
    
    // The arm launcher
    public static final int leftArmLauncherOn = 1;
    public static final int leftArmLauncherOff = 2;
    
    public static final int rightArmLauncherOn = 4;
    public static final int rightArmLauncherOff = 3;
    
    // Port of relay for compressor
    public static final int compressorRelay = 1;
    
    // Port of digital input compressor shutoff
    public static final int compressorShutoffDigitalInput = 1;
    
    // The speed at which the ramp-down starts to apply
    public static double motorRampStart = 0.4;
    public static double motorRampStep = 0.1;
    
}
