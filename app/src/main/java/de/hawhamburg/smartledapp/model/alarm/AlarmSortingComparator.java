package de.hawhamburg.smartledapp.model.alarm;

import java.util.Comparator;

public class AlarmSortingComparator implements Comparator<Alarm> {
    @Override
    public int compare(Alarm alarm1, Alarm alarm2) {
        return alarm1.getTime().compareTo(alarm2.getTime());
    }
}