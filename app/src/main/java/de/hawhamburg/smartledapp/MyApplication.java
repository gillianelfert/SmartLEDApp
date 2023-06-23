package de.hawhamburg.smartledapp;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import de.hawhamburg.smartledapp.model.profile.Profile;
import de.hawhamburg.smartledapp.model.profile.ProfileRepository;

public class MyApplication extends Application {
    private ProfileRepository profileRepository;
    private MutableLiveData<List<Profile>> profileLiveData;

    @Override
    public void onCreate() {
        super.onCreate();
        profileRepository = new ProfileRepository(this);
        profileLiveData = new MutableLiveData<>();

        LiveData<List<Profile>> allProfilesLiveData = profileRepository.getAllProfiles();
        allProfilesLiveData.observeForever(profiles -> {
            profileLiveData.setValue(profiles);
        });
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
