package de.hawhamburg.smartledapp.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.R;
import de.hawhamburg.smartledapp.model.profile.Profile;

public class LightViewModel extends AndroidViewModel {
    MyApplication myApplication;
    Context context;
    private MutableLiveData<Integer> lightImageViewColor = new MutableLiveData<>();

    public MutableLiveData<Integer> getLighImageViewColor() {
        return lightImageViewColor;
    }

    Profile activeProfile;

    public void setLightImageViewColor(MutableLiveData<Integer> lightImageViewColor) {
        this.lightImageViewColor = lightImageViewColor;
    }

    public LightViewModel(@NonNull Application application) {
        super(application);
        myApplication = (MyApplication) application;
        activeProfile = myApplication.getProfileRepository().getActiveProfile();
        context = MyApplication.getAppContext();
    }

    public void setupBrightnessSeekBar(SeekBar seekBar, ImageView imageView){
        seekBar.setProgress(activeProfile.getLightBrightness());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myApplication.getProfileRepository().getActiveProfile().setLightBrightness(progress);
                myApplication.getProfileRepository().update(myApplication.getProfileRepository().getActiveProfile());
                seekBar.setProgress(progress);
                setupLightImageView(imageView);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void setupLightImageView(ImageView imageView) {
        int brightness = activeProfile.getLightBrightness();
        int[] brightnessColors = myApplication.getResources().getIntArray(R.array.brightnessColors);

        int colorIndex;
        if (brightness == 0) {
            colorIndex = 0;
        } else {
            colorIndex = Math.min((brightness / 10) + 1, brightnessColors.length - 1);
        }

        int color = brightnessColors[colorIndex];
        imageView.setColorFilter(color);
    }

}
