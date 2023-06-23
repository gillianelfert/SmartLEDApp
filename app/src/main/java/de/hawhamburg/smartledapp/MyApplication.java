package de.hawhamburg.smartledapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import de.hawhamburg.smartledapp.model.profile.Profile;
import de.hawhamburg.smartledapp.model.profile.ProfileRepository;

public class MyApplication extends Application {
    private LiveData<List<Profile>> allProfiles;
    private ProfileRepository profileRepository;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        profileRepository = new ProfileRepository((MyApplication) this);
    }

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    public Profile getActiveProfile() {
        List<Profile> profiles = profileRepository.getAllProfiles().getValue();
        for (Profile profile : profiles){
            if (profile.isStatus()){return profile;}
        }
        Profile newProfile = new Profile("Standard", false, true, false, 100);
        profileRepository.insert(newProfile);
        return newProfile;
    }

    public void setActiveProfile(Profile activeProfile) {
        List<Profile> profiles = allProfiles.getValue();
        if (profiles != null){
            for (Profile p : profiles){
                p.setInactive();
            }
            activeProfile.setActive();
        }
    }
}
