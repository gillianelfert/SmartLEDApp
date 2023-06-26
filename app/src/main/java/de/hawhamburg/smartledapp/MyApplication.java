package de.hawhamburg.smartledapp;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import de.hawhamburg.smartledapp.model.mqtt.CalculationClass;
import de.hawhamburg.smartledapp.model.profile.Profile;
import de.hawhamburg.smartledapp.model.profile.ProfileRepository;

public class MyApplication extends Application {
    private ProfileRepository profileRepository;
    private MutableLiveData<List<Profile>> profileLiveData;
    private static Context appContext;
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

    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public MutableLiveData<List<Profile>> getProfileLiveData() {
        return profileLiveData;
    }

    public void setProfileLiveData(MutableLiveData<List<Profile>> profileLiveData) {
        this.profileLiveData = profileLiveData;
    }
}
