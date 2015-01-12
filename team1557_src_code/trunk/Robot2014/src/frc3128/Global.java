package frc3128;

import edu.wpi.first.wpilibj.Watchdog;
import frc3128.EventManager.EventManager;
import frc3128.EventManager.ListenerManager;

/**
 * http://www.reddit.com/r/FRC/comments/1nen43/looking_for_software_framework_beta_testers/
 * https://code.google.com/p/frc-team-3128/
 * 
 * https://code.google.com/p/frc-team-3128/source/browse/#svn%2Fdoc
 * file:///C:/Users/code/Documents/frc-team-3128-doc/frc3128/EventManager/EventManager.html#addSingleEvent(frc3128.EventManager.Event)
 * 
 * @author Noah Sutton-Smolin
 */
public class Global {
    public static void initializeRobot() {}
    public static void initializeDisabled() {}
    public static void initializeAuto() {}
    public static void initializeTeleop() {}

    public static void robotKill() {Watchdog.getInstance().kill();}
    public static void robotStop() {EventManager.dropAllEvents(); ListenerManager.dropAllListeners();}

    private Global() {}
}