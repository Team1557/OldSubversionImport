package com.twelvevoltbolt.gallium.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.twelvevoltbolt.gallium.OI;
import com.twelvevoltbolt.gallium.subsystems.CompressorSubsystem;
import com.twelvevoltbolt.gallium.subsystems.DriveSubsystem;
import com.twelvevoltbolt.gallium.subsystems.FiringSubsystem;
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
    public static FiringSubsystem firing = new FiringSubsystem();
    public static CompressorSubsystem compressor = new CompressorSubsystem();
    public static VacuumArmSubsystem arms;
    
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        try {
            drive = new DriveSubsystem();
            vacuum = new VacuumSubsystem();
            arms = new VacuumArmSubsystem();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            System.out.println("Can exception while creating subsystems");
        }

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
