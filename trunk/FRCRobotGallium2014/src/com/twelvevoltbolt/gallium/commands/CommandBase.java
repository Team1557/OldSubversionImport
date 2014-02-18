package com.twelvevoltbolt.gallium.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.twelvevoltbolt.gallium.OI;
import com.twelvevoltbolt.gallium.subsystems.CompressorSubsystem;
import com.twelvevoltbolt.gallium.subsystems.DriveSubsystem;
import com.twelvevoltbolt.gallium.subsystems.FiringSubsystem;
import com.twelvevoltbolt.gallium.subsystems.ShifterSubsystem;
import com.twelvevoltbolt.gallium.subsystems.UltraSubsystem;
import com.twelvevoltbolt.gallium.subsystems.VacuumArmSubsystem;
import com.twelvevoltbolt.gallium.subsystems.VacuumSubsystem;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    
    // Create a single static instance of all of your subsystems
    public static DriveSubsystem drive;
    public static VacuumSubsystem vacuum;
    public static FiringSubsystem firing;
    public static CompressorSubsystem compressor;
    public static VacuumArmSubsystem arms;
    public static ShifterSubsystem shifter;
    public static UltraSubsystem ultrasonic;
    
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        try {
            System.out.println("Creating drive");
            drive = new DriveSubsystem();
            System.out.println("Creating vacuum");
            vacuum = new VacuumSubsystem();
            System.out.println("Creating firing");
            firing = new FiringSubsystem();
            System.out.println("Creating compressor");
            compressor = new CompressorSubsystem();
            System.out.println("Creating arms");
            arms = new VacuumArmSubsystem();
            System.out.println("Creating shifters");
            shifter = new ShifterSubsystem();
            System.out.println("Creating ultrasonic");
            ultrasonic = new UltraSubsystem();
        } catch (CANTimeoutException ex) {
            System.out.println("CAN exception while creating subsystems");
            ex.printStackTrace();
        }

        oi = new OI();
        
        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(drive);
        SmartDashboard.putData(vacuum);
        SmartDashboard.putData(firing);
        SmartDashboard.putData(compressor);
        SmartDashboard.putData(arms);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
