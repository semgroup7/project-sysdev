#include <Smartcar.h>
#include <Wire.h>
#include <Servo.h>

Car car;
Gyroscope gyro;
Odometer encoderL;
Odometer encoderR;
const int encoderPinL = 2;
const int encoderPinR = 3;
int motorSpeed = 100; //100% of the max speed
float currentSpeed;
unsigned long currentDistance;
String angle;
String speed;
String distance;
String a = "a";
String d = "d";
String s = "s";

void setup() {
  gyro.attach();
  encoderL.attach(encoderPinL);
  encoderL.begin();
  encoderR.attach(encoderPinR);
  encoderR.begin();
  car.begin(encoderL,encoderR,gyro);
  Serial.begin(9600);
  Serial.setTimeout(200);
  gyro.begin();
}

void loop() {
  handleInput();
}

void handleInput() { //handle serial input if there is any
  if (Serial.available()) {
    String input = Serial.readStringUntil('/');
    if (input.startsWith("w")) {
      int throttle = input.substring(1).toInt(); // Example: recieve 'w100' = move forward with speed 100.
      if (throttle > 0 || throttle < 0) {
        car.setSpeed(throttle);
      } else {
        car.stop();
      }
      
    }else if (input.startsWith("r")){
      int side = input.substring(1).toInt();      // Example: recieve 'r1' = rotate right
      rotateOnSpot(side);
      
    }else if (input.startsWith("a")){
      int deg = input.substring(1).toInt();      // Example: recieve 'a90' = turn 90 degrees
      car.setAngle(deg);
      gyro.update();
    }else if (input.startsWith("c")){
      Serial.end();
      
    }else{
      car.stop();
    }
  }
  sendData();
}

void rotateOnSpot(int targetDegrees){
  /* Let's set opposite speed on each side of the car, so it rotates on spot */
  if (targetDegrees == 1){ //positive value means we should rotate clockwise
    car.setMotorSpeed(motorSpeed, -motorSpeed); // left motors spin forward, right motors spin backward
  }else if(targetDegrees == -1){ //rotate counter clockwise
    car.setMotorSpeed(-motorSpeed, motorSpeed); // left motors spin backward, right motors spin forward
  }else if (targetDegrees > 1) {
    car.rotate(targetDegrees);
  } else{
    car.setMotorSpeed(0,0); //stop rotation
  }
  car.setSpeed(0);
  car.setAngle(0);
}

void sendData() {
      gyro.update();
      angle = String(gyro.getAngularDisplacement());
      angle = String(a + angle);
      Serial.println(angle);

      currentSpeed = (encoderL.getSpeed() + encoderR.getSpeed()) / 2;
      speed = String(currentSpeed);
      speed = String(s + speed);
      Serial.println(speed);

      currentDistance = (encoderL.getDistance() + encoderR.getDistance()) / 2;
      distance = String(currentDistance);
      distance = String(d + distance);
      Serial.println(distance);
}
