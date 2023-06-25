package de.hawhamburg.smartledapp;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import de.hawhamburg.smartledapp.model.mqtt.CalculationClass;
import de.hawhamburg.smartledapp.model.mqtt.MqttClient;
import de.hawhamburg.smartledapp.model.profile.Profile;
import de.hawhamburg.smartledapp.model.profile.ProfileRepository;

public class MyApplication extends Application {
    private ProfileRepository profileRepository;
    private MutableLiveData<List<Profile>> profileLiveData;
    private static Context appContext;

    private CalculationClass calculationClass;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        profileRepository = new ProfileRepository(this);
        profileLiveData = new MutableLiveData<>();

        LiveData<List<Profile>> allProfilesLiveData = profileRepository.getAllProfiles();
        allProfilesLiveData.observeForever(profiles -> {
            profileLiveData.setValue(profiles);
        });

        Thread thread = new Thread(new CalculationClass(getAppContext()));
        thread.start();
    }

    public static Context getAppContext() {
        return appContext;
    }

    public ProfileRepository getProfileRepository() {
        return profileRepository;
    }

}
