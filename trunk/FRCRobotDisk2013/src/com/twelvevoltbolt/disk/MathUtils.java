/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.disk;

public class MathUtils {

    public static double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }
    
    public static double sign(double number) {
        return number > 0 ? 1 : number < 0 ? -1 : 0;
    }
    
}
