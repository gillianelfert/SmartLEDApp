package de.hawhamburg.smartledapp.model.alarm;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Alarms {
    List<Alarm> alarms;

    public Alarms() {
        alarms = new ArrayList<>();
        alarms.add(new Alarm(LocalTime.of(13,37), true));
        alarms.add(new Alarm(LocalTime.of(14,37), true));
        alarms.add(new Alarm(LocalTime.of(15,37), true));
        alarms.add(new Alarm(LocalTime.of(16,37), true));
        alarms.add(new Alarm(LocalTime.of(11,37), false));
        alarms.add(new Alarm(LocalTime.of(10,37), true));
        Collections.sort(alarms, new AlarmSortingComparator());
    }

    public List<Alarm> getAlarms() {
        return alarms;
    }
}
