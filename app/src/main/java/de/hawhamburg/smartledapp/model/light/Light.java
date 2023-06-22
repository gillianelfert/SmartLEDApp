package de.hawhamburg.smartledapp.model.light;

public class Light {
    private boolean isOn;
    private int brightness;

    public Light(boolean isOn, int brightness) {
        this.isOn = isOn;
        this.brightness = brightness;
    }
    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }
}
