package com.sekwah.robohand;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Listener;

/**
 * @author sekwah41
 */
public class LeapTracker extends Listener {

    // Take the last time and set a max update rate.
    private long lastTime = System.currentTimeMillis();

    private final int UPDATE_DELAY = 100;

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
        }


        /*System.out.println("Handy");
        for(int i = 0; i < 5; i++){
            RoboHand.sendToHand(i);
            RoboHand.sendToHand(160);
            RoboHand.sendToHand(200);
        }*/
    }

}
