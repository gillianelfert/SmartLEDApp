package de.hawhamburg.smartledapp.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.model.profile.Profile;
import de.hawhamburg.smartledapp.model.profile.ProfileRepository;

public class LightViewModel extends AndroidViewModel {
    MyApplication myApplication;
    public LightViewModel(@NonNull Application application) {
        super(application);
        myApplication = (MyApplication) application;
        myApplication.initializeProfileRepository();
    }

    public void setupBrightnessSeekbar(SeekBar seekBar){
        seekBar.setProgress(myApplication.getActiveProfile().getLightBrightness());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Profile profile = myApplication.getActiveProfile();
                profile.setLightBrightness(progress);
                myApplication.setActiveProfile(profile);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

}
