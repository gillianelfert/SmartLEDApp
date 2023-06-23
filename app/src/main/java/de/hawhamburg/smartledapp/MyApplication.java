package de.hawhamburg.smartledapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.hawhamburg.smartledapp.model.profile.Profile;
import de.hawhamburg.smartledapp.model.profile.ProfileRepository;

public class MyApplication extends Application {
    private static MyApplication sInstance;
    private LiveData<List<Profile>> allProfiles;
    private ProfileRepository profileRepository;
    private Profile activeProfile;

    @Override
    public void onCreate() {
        super.onCreate();
        profileRepository = new ProfileRepository(this);
        allProfiles = profileRepository.getAllProfiles();
    }

    public Profile getActiveProfile() {
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
}
