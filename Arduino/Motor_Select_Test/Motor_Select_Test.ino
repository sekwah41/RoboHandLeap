#include <Servo.h>

// 0-4 fingers 5 for thumb pinkie first to index. (use right hand left to right when facing towards you)
Servo finger[5];
// Max angle is 179 (dont try 180...)

// Data for the fingers (May need to treat thumb differently)
const int minAngle = 20;
const int maxAngle = 150;

void setup() {
  finger[0].attach(3);
  finger[1].attach(5);
  finger[2].attach(6);
  finger[3].attach(9);
  finger[4].attach(10);
  // Initialise arm positions
  for (int i = 0; i <= 4; i = i + 1) {
    finger[i].write(0);
  }

  delay(400);

  for (int i = 0; i <= 4; i = i + 1) {
    finger[i].write(179);
    delay(400);
    finger[i].write(0);
  }

  Serial.begin(9600);
  Serial.println("Init");
  //delay(10000);

}

void setPos(){
  
}

void loop() {
  // Loop to get the data from the java program
  //Serial.println("Test");
  /**for (int i = 0; i <= 4; i = i + 1) {
    Serial.println(i);
    finger[i].write(159);
    delay(2000);
    finger[i].write(10);
    delay(2000);
    }*/

  if (Serial.available() > 0) {
    int text = Serial.read() - 48 - 1;

    if ( text >= 0 && text <= 4) {
      finger[text].write(179);
      delay(400);
      finger[text].write(0);
    }

    Serial.println(text);

  }

  //Serial.println(i);
  /**for (int i = 0; i <= 4; i = i + 1) {
    delay(50);
    finger[i].write(159);
    }
    delay(1000);
    for (int i = 0; i <= 4; i = i + 1) {
    delay(50);
    finger[i].write(10);
    }
    delay(2000);*/

}
