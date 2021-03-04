#include <Arduino.h>
#include <U8x8lib.h>

auto display = U8X8_SSD1306_128X64_NONAME_HW_I2C(U8X8_PIN_NONE);

void setup() {
    Serial.begin(9600);

    display.begin();
    display.setFlipMode(0);
    display.clearDisplay();
}

void loop() {
    display.setFont(u8x8_font_profont29_2x3_r);
    display.setCursor(0, 0);

    if (!Serial.available()) {
        return;
    }

    const auto receivedData = Serial.read();

    char buf[16];
    sprintf(buf, "%03d", receivedData);

    display.print(buf);
}