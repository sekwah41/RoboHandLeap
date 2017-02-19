package com.sekwah.robohand;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Listener;
import com.sekwah.robohand.hand.FingerAnaly;

/**
 * @author sekwah41
 */
public class LeapTracker extends Listener {

    // Take the last time and set a max update rate.
    private long lastTime = System.currentTimeMillis();

    private int[] lastKnown = new int[5];

    private final float minVal = 1.7f;
    private final float maxVal = 3f;

    private final float[] convMinVal = {20,20,20,20,20};
    private final float[] convMaxVal = {150,150,150,150,150};

    private final int UPDATE_DELAY = 100;

    private final int MIN_MOVE = 5;

    public void onFrame(Controller controller) {
        if(System.currentTimeMillis() - lastTime < UPDATE_DELAY){
            return;
        }
        lastTime = System.currentTimeMillis();
        //System.out.println("Frame available " + controller.frame().currentFramesPerSecond());
        controller.frame().currentFramesPerSecond();

        // TODO add software constraints so that the arduino doesnt HAVE to remember but could also work with the other

        HandList hands = controller.frame().hands();
        //System.out.println(hands);
        if(hands.isEmpty()){
            //System.out.println("Not Handy");
            return;
        }

        Hand hand = hands.get(0);

        for(int i = 0; i < 5; i++){
            //System.out.println(FingerAnaly.curleyWhurly(hand.fingers().get(i)));
            float ang = FingerAnaly.curleyWhurly(hand.fingers().get(i));
            ang -= minVal;
            ang /= maxVal - minVal;
            ang *= convMaxVal[i] - convMinVal[i];
            ang += convMinVal[i];
            if(ang < convMinVal[i]){
                ang = convMinVal[i];
            }
            else if(ang > convMaxVal[i]){
                ang = convMaxVal[i];
            }
            int diff = (int) (lastKnown[i] - ang);
            if(diff > MIN_MOVE || diff < -MIN_MOVE){
                lastKnown[i] = (int) ang;
                RoboHand.sendToHand(i);
                RoboHand.sendToHand((int) ang);
                //System.out.println(ang);
                RoboHand.sendToHand(200);
            }

        }
        System.out.println("");

        /*for(int i = 0; i < 5; i++){
            if(hand.fingers().get(i).isExtended()){
                RoboHand.sendToHand(i);
                RoboHand.sendToHand(10);
                RoboHand.sendToHand(200);
            }
            else{
                RoboHand.sendToHand(i);
                RoboHand.sendToHand(160);
                RoboHand.sendToHand(200);
            }
        }*/


        /*System.out.println("Handy");
        for(int i = 0; i < 5; i++){
            RoboHand.sendToHand(i);
            RoboHand.sendToHand(160);
            RoboHand.sendToHand(200);
        }*/
    }

}
