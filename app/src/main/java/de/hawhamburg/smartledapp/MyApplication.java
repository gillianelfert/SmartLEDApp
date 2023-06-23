package de.hawhamburg.smartledapp;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import de.hawhamburg.smartledapp.model.profile.Profile;
import de.hawhamburg.smartledapp.view.ProfileActivity;
import de.hawhamburg.smartledapp.viewmodel.ProfileViewModel;

public class MyApplication extends Application {
    private Profile activeProfile;
    public ProfileViewModel profileViewModel;
    public void setProfileViewModel(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
    }

    public Profile getActiveProfile() {
        return activeProfile;
    }

    public void setProfileActive() {
        activeProfile.setActive();
    }
}
