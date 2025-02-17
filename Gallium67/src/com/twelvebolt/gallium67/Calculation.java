package com.twelvebolt.gallium67;

public class Calculation {
    public double mapX;
    public double mapY;
    public double goalLeftNearX;
    public double goalLeftNearY;
    public double goalLeftMidX;
    public double goalLeftMidY;
    public double goalLeftFarX;
    public double goalLeftFarY;
    public double goalRightNearX;
    public double goalRightNearY;
    public double goalRightMidX;
    public double goalRightMidY;
    public double goalRightFarX;
    public double goalRightFarY;
    public double joystickX;
    public double joystickY;
    public double magnitude;
    public double fireAngle;
    public double fireRange;
    public boolean inRange;
    
    public static double getDistanceSquared(double x, double y, double x2, double y2) {
        return Math.pow(x - x2, 2) + Math.pow(y - y2, 2);
    }
    
    public boolean inRange(double mapX, double mapY, boolean goalTarget) {
        if (goalTarget == true) {
            if (getDistanceSquared(goalRightNearX, goalRightNearY, mapX, mapY) > fireRange * fireRange
                    || (Math.sqrt(Math.pow((goalRightMidX - mapX), 2) + Math.pow((goalRightMidY - mapY), 2)) > fireRange)
                    || (Math.sqrt(Math.pow((goalRightFarX - mapX), 2) + Math.pow((goalRightFarY - mapY), 2)) > fireRange)) {
                return inRange = true;
            } else {
                return inRange = false;
            }
        } else {

            if ((Math.sqrt(Math.pow((goalLeftNearX - mapX), 2) + Math.pow((goalLeftNearY - mapY), 2)) > fireRange)
                    || (Math.sqrt(Math.pow((goalLeftMidX - mapX), 2) + Math.pow((goalLeftMidY - mapY), 2)) > fireRange)
                    || (Math.sqrt(Math.pow((goalLeftFarX - mapX), 2) + Math.pow((goalLeftFarY - mapY), 2)) > fireRange)) {
                return inRange = true;
            } else {
                return inRange = false;
            }
        }
    }

    public double resolveAngle(double mapX, double mapY,boolean goalTarget) {
        if (goalTarget == true) {
            return fireAngle;
        } else {
            return fireAngle;
        }
    }
    
    public double trajectory() {        
        return 0;        
    }    
}
