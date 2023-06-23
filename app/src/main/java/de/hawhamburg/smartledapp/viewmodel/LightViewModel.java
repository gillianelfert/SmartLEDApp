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
    public LightViewModel(@NonNull Application application) {
        super(application);
    }

    public void setupBrightnessSeekBar(SeekBar seekBar){

    }
}
