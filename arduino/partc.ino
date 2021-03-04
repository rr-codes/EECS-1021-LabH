#include <Arduino.h>
#include <U8x8lib.h>

void setup() {
    Serial.begin(9600);
}

void sendPotentiometerData() {
    const auto value = analogRead(A0);
    const byte data[] = {0, 0, highByte(value), lowByte(value)};

    Serial.write(data, 4);
    Serial.println();
}

void loop() {
    sendPotentiometerData();
    delay(1000);
}