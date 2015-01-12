/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.twelvevoltbolt.disk.commands;

public class DriveCommand extends CommandBase {

    private double time;
    private double left = 0;
    private double right = 0;

    public DriveCommand(double left, double right, double time) {
        this.time = time;
        this.left = left;
        this.right = right;
        
        requires(drive);
    }
    
    protected void initialize() {
        setTimeout(getTime());
    }

    protected void execute() {
        drive.drive(getLeft(), getRight());
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        drive.drive(0, 0);
    }

    protected void interrupted() {
        end();
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double right) {
        this.right = right;
    }

}
