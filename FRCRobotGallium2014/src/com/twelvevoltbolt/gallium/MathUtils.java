/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.gallium;

public class MathUtils {

    public static double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }
}
