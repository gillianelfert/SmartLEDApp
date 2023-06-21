package de.hawhamburg.smartledapp.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import de.hawhamburg.smartledapp.model.alarm.Alarm;
import de.hawhamburg.smartledapp.model.alarm.AlarmRepository;

public class AlarmViewModel extends AndroidViewModel {
    private AlarmRepository alarmRepository;
    private LiveData<List<Alarm>> allAlarms;


    public AlarmViewModel(@NonNull Application application) {
        super(application);
        alarmRepository = new AlarmRepository(application);
        allAlarms = alarmRepository.getAllAlarms();
    }

    public void insert(Alarm alarm){
        alarmRepository.insert(alarm);
    }

    public void update(Alarm alarm){
        alarmRepository.update(alarm);
    }

    public void delete(Alarm alarm){
        alarmRepository.delete(alarm);
    }

    public void deleteAllAlarms(){
        alarmRepository.deleteAllAlarms();
    }

    public LiveData<List<Alarm>> getAllAlarms(){
        return allAlarms;
    }
}
