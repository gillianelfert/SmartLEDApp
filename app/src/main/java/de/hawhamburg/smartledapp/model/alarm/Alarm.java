package de.hawhamburg.smartledapp.model.alarm;

import java.time.LocalTime;

public class Alarm {
    private LocalTime time;
    private boolean isActive;

    public Alarm(LocalTime time, boolean isActive) {
        this.time = time;
        this.isActive = isActive;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
