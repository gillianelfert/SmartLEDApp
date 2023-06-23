package de.hawhamburg.smartledapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.hawhamburg.smartledapp.model.profile.Profile;

public class MyApplication extends Application {
    private Profile activeProfile;

    public Profile getActiveProfile() {
        return activeProfile;
    }

    public void setActiveProfile(Profile activeProfile) {
        this.activeProfile = activeProfile;
    }
}
