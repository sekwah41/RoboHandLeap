package com.sekwah.robohand;

import com.leapmotion.leap.Controller;

/**
 * @author sekwah41
 */
public class RoboHand {

    private final LeapTracker leapTracker;
    private final DataHandler handTracker;

    public static RoboHand instance;

    public RoboHand(){
        instance = this;

        handTracker = new DataHandler();
        handTracker.initialize();
        leapTracker = new LeapTracker();

        Controller controller = new Controller();
        controller.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);
        controller.addListener(leapTracker);

        /*Thread t = new Thread() {
            public void run() {
                //the following line will keep this app alive for 1000 seconds,
                //waiting for events to occur and responding to them (printing incoming messages to console).
                try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
            }
        };
        t.start();*/
        while(true){
            try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
        }
    }

    public static void sendToHand(String s) {
        for(char key : s.toCharArray()){
            instance.handTracker.sendToHand(key);
        }
    }

    public static void sendToHand(char data) {
        instance.handTracker.sendToHand(data);
    }

    public static void sendToHand(int data) {
        instance.handTracker.sendToHand(data);
    }
}
