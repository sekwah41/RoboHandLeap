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
        return finger.jointPosition(joint1).minus(finger.jointPosition(joint2))
                .angleTo(finger.jointPosition(joint3).minus(finger.jointPosition(joint2)));
    }

    public static float angle(Vector vec1, Vector vec2){
        return vec1.angleTo(vec2);
    }

}
