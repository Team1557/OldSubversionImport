package com.twelvevoltbolt.gallium;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // Pair of left side motors
    public static final int leftMotor1 = 1;
    public static final int leftMotor2 = 2;
    
    // Pair of right side motors
    public static final int rightMotor1 = 3;
    public static final int rightMotor2 = 4;
    
    // Arms
//    public static final int leftLauncherShoulder = 5;
//    public static final int rightLauncherShoulder = 6;
    
    public static final int armLauncher = 5;
    
    // Port of relay for compressor
    public static final int compressorRelay = 7;
    
    // Port of digital input compressor shutoff
    public static final int compressorShutoffDigitalInput = 7;
}
