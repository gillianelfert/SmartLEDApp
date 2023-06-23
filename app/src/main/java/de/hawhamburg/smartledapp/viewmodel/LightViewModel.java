package de.hawhamburg.smartledapp.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.model.profile.Profile;

public class LightViewModel extends AndroidViewModel {
    MyApplication myApplication;
    public LightViewModel(@NonNull Application application) {
        super(application);
    }

    public void setupBrightnessSeekbar(SeekBar seekBar, MyApplication myApplication){
        //seekBar.setProgress(myApplication.getActiveProfile().getLightBrightness());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
