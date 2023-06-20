package de.hawhamburg.smartledapp.model.light;

public class Light {
    private String name;
    private boolean isOn;
    private int brightness;

    public Light(String name, boolean isOn, int brightness) {
        this.name = name;
        this.isOn = isOn;
        this.brightness = brightness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
