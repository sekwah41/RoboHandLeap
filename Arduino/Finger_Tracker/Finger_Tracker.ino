#include <Servo.h>

const int servos = 5;

// 0-4 fingers 5 for thumb pinkie first to index. (use right hand left to right when facing towards you)
Servo finger[servos];

// Max angle is 179 (dont try 180...)

// Data for the fingers (May need to treat thumb differently)
const int minAngle = 20;
const int maxAngle = 150;

boolean idMode = true;

int fingerId = 0;

void setup() {


  finger[0].attach(3);
  finger[1].attach(5);
  finger[2].attach(6);
  finger[3].attach(9);
  finger[4].attach(10);
  // Initialise arm positions
  for (int i = 0; i < servos; i = i + 1) {
    finger[i].write(minAngle);
  }
  for (int i = 0; i <= 4; i = i + 1) {
    finger[i].write(maxAngle);
    delay(400);
    finger[i].write(minAngle);
  }

  
  // put your setup code here, to run once:

}

void setPos(int fingerId, int angle) {
  if (fingerId < 0 && fingerId > servos) {
    return;
  }
  if (angle < minAngle) {
    angle = minAngle;
  }
  else if (angle > maxAngle) {
    angle = maxAngle;
  }
  finger[fingerId].write(angle);

}

void loop() {
  // put your main code here, to run repeatedly:
  if (Serial.available() > 0) {
    int serIn = Serial.read();

    if (serIn > 179) {
      idMode = true;
      return;
    }

    if (idMode) {
      idMode = false;
      fingerId = serIn;
      return;
    }
    else{
      setPos(fingerId, serIn);
    }

  }
}
