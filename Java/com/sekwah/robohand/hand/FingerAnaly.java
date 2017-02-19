package com.sekwah.robohand.hand;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Vector;

/**
 * @author sekwah41
 */
public class FingerAnaly {

    /**
     * TODO figure out how to do compared to hand (check for vector pointing up out of the palm.
     * @param finger the finger to analyse
     * @return 0 to 1 of how curley the finger is
     */
    public static float curleyWhurly(Finger finger){
        float div = 1;
        float total = 0;
        if(finger.type() != Finger.Type.TYPE_THUMB){
            div = 2;

            total += anglePoints(finger, Finger.Joint.JOINT_DIP, Finger.Joint.JOINT_PIP, Finger.Joint.JOINT_MCP);

        }/*
        Vector vec = finger.jointPosition(Finger.Joint.JOINT_TIP).minus(finger.jointPosition(Finger.Joint.JOINT_DIP));
        Vector vec2 = finger.jointPosition(Finger.Joint.JOINT_PIP).minus(finger.jointPosition(Finger.Joint.JOINT_DIP));*/

        total += anglePoints(finger, Finger.Joint.JOINT_TIP, Finger.Joint.JOINT_DIP, Finger.Joint.JOINT_PIP);

        return total / div;
        /*Vector vec = finger.jointPosition(Finger.Joint.JOINT_DIP).minus(finger.jointPosition(Finger.Joint.JOINT_PIP));
        Vector vec2 = finger.jointPosition(Finger.Joint.JOINT_MCP).minus(finger.jointPosition(Finger.Joint.JOINT_PIP));
        return (float) Math.toDegrees(angle(vec, vec2));*/
        //finger.jointPosition(Joint.)
    }

    public static float anglePoints(Finger finger, Finger.Joint joint1,  Finger.Joint joint2,  Finger.Joint joint3){
        Vector vec = finger.jointPosition(joint1).minus(finger.jointPosition(joint2));
        Vector vec2 = finger.jointPosition(joint3).minus(finger.jointPosition(joint2));

        float crossProd = vec.getX() * vec2.getX() + vec.getZ() * vec2.getZ() + vec.getY() * vec2.getY();

        float length = getLength(vec);

        float length2 = getLength(vec2);

        return (float) Math.acos(crossProd / (length * length2));
    }

    private static float getLength(Vector vec) {
        return (float) Math.sqrt(vec.getX() * vec.getX() + vec.getY() * vec.getY() + vec.getZ() * vec.getZ());
    }

}
