package com.twelvebolt.gallium67;

public class Calculation {
    public double mapX;
    public double mapY;
    public double goalNearX;
    public double goalNearY;
    public double goalMidX;
    public double goalMidY;
    public double goalFarX;
    public double goalFarY;
    public double joystickX;
    public double joystickY;
    public double magnitude;
    public double fireAngle;
    public double fireRange;
    public boolean inRange;
    
    public boolean inRange(double mapX, double mapY, boolean goalTarget) {
        if ((Math.sqrt(Math.pow((goalNearX - mapX), 2) + Math.pow((goalNearY - mapY), 2)) > 5) || (Math.sqrt(Math.pow((goalMidX - mapX), 2) + Math.pow((goalMidY - mapY), 2)) > 5) || (Math.sqrt(Math.pow((goalFarX - mapX), 2) + Math.pow((goalFarY - mapY), 2)) > 5)) {
            return inRange = true;
        } else {
            return inRange = false;
        }
    }

    public double resolveAngle(double mapX, double mapY,boolean goalTarget) {      
        return fireAngle;
    }
    
    public double trajectory() {
        return 0;        
    }    
}
