package com.twelvevoltbolt.disk.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.twelvevoltbolt.disk.OI;
import com.twelvevoltbolt.disk.subsystems.CompressorSubsystem;
import com.twelvevoltbolt.disk.subsystems.DriveSubsystem;
import com.twelvevoltbolt.disk.subsystems.FiringSubsystem;
import com.twelvevoltbolt.disk.subsystems.ShifterSubsystem;

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
    public static FiringSubsystem firing;
    public static CompressorSubsystem compressor;
    public static ShifterSubsystem shifter;
    
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        System.out.println("Creating drive");
        drive = new DriveSubsystem();
        System.out.println("Creating firing");
        firing = new FiringSubsystem();
        System.out.println("Creating compressor");
        compressor = new CompressorSubsystem();
        System.out.println("Creating shifters");
        shifter = new ShifterSubsystem();

        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(drive);
        SmartDashboard.putData(firing);
        SmartDashboard.putData(compressor);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
