package de.hawhamburg.smartledapp.viewmodel;

import android.app.Application;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.R;

public class LightViewModel extends AndroidViewModel {
    MyApplication myApplication;
    public LightViewModel(@NonNull Application application) {
        super(application);
        myApplication = (MyApplication) application;
    }

    public void setupBrightnessSeekBar(SeekBar seekBar){
        seekBar.setProgress(myApplication.getProfileRepository().getActiveProfile().getLightBrightness());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myApplication.getProfileRepository().getActiveProfile().setLightBrightness(progress);
                myApplication.getProfileRepository().update(myApplication.getProfileRepository().getActiveProfile());
                seekBar.setProgress(progress);
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
