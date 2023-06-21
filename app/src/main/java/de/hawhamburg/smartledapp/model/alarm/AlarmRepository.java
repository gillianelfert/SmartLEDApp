package de.hawhamburg.smartledapp.model.alarm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;



public class AlarmRepository {

    private AlarmDao alarmDao;
    private LiveData<List<Alarm>> allAlarms;

    public AlarmRepository(Application application){
        AlarmDatabase database = AlarmDatabase.getInstance(application);
        alarmDao = database.alarmDao();
        allAlarms = alarmDao.getAllAlarms();
    }

    public void insert(Alarm alarm){
        new AlarmRepository.InsertAlarmAsyncTask(alarmDao).execute(alarm);
    }

    public void update(Alarm alarm){
        new AlarmRepository.UpdateAlarmAsyncTask(alarmDao).execute();

    }

    public void delete(Alarm alarm){
        new AlarmRepository.DeleteAlarmAsyncTask(alarmDao).execute(alarm);
    }

    public void deleteAllAlarms(){
        new AlarmRepository.DeleteAllAlarmsAsyncTask(alarmDao).execute();
    }

    public LiveData<List<Alarm>> getAllAlarms(){
        return allAlarms;
    }

    private static class InsertAlarmAsyncTask extends AsyncTask<Alarm,Void,Void> {
        private AlarmDao alarmDao;

        private InsertAlarmAsyncTask(AlarmDao alarmDao){
            this.alarmDao = alarmDao;
        }


        @Override
        protected Void doInBackground(Alarm... alarms) {
            alarmDao.insert(alarms[0]);
            return null;
        }
    }

    private static class UpdateAlarmAsyncTask extends AsyncTask<Alarm,Void,Void> {
        private AlarmDao alarmDao;

        private UpdateAlarmAsyncTask(AlarmDao alarmDao){
            this.alarmDao = alarmDao;
        }


        @Override
        protected Void doInBackground(Alarm... alarms) {
            alarmDao.update(alarms[0]);
            return null;
        }
    }

    private static class DeleteAlarmAsyncTask extends AsyncTask<Alarm,Void,Void> {
        private AlarmDao alarmDao;

        private DeleteAlarmAsyncTask(AlarmDao alarmDao){
            this.alarmDao = alarmDao;
        }


        @Override
        protected Void doInBackground(Alarm... alarms) {
            alarmDao.delete(alarms[0]);
            return null;
        }
    }

    private static class DeleteAllAlarmsAsyncTask extends AsyncTask<Void,Void,Void> {
        private AlarmDao alarmDao;

        private DeleteAllAlarmsAsyncTask(AlarmDao alarmDao){
            this.alarmDao = alarmDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            alarmDao.deleteAllAlarms();
            return null;
        }
    }

}
