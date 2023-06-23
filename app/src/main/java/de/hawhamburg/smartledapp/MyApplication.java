package de.hawhamburg.smartledapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import de.hawhamburg.smartledapp.model.profile.Profile;
import de.hawhamburg.smartledapp.model.profile.ProfileRepository;

public class MyApplication extends Application {
    private static MyApplication sInstance;
    private LiveData<List<Profile>> allProfiles;
    private ProfileRepository profileRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initializeProfileRepository();
    }

    public MyApplication() {
    }

    public MyApplication(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public static MyApplication getInstance() {
        return sInstance;
    }

    public Profile getActiveProfile() {
        Profile activeProfile;
        List<Profile> profiles = allProfiles.getValue();
        if (profiles == null){
            profiles = new ArrayList<>();
            profiles.add(new Profile("Standard", false, true, false, 100));
            return profiles.get(0);
        }
        for (Profile p : profiles){
            if (p.isStatus()){
                activeProfile = p;
                    return activeProfile;
            }
        }
        activeProfile = profiles.get(0);
        activeProfile.setActive();
        return activeProfile;
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

    public void initializeProfileRepository() {
        if (profileRepository == null) {
            profileRepository = new ProfileRepository(this);
        }
        allProfiles = profileRepository.getAllProfiles();
    }
}
