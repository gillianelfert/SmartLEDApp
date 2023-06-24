package de.hawhamburg.smartledapp.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.media.Image;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.Calendar;

import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.R;

public class LightViewModel extends AndroidViewModel {
    MyApplication myApplication;
    Context context;
    private MutableLiveData<Integer> lightImageViewColor = new MutableLiveData<>();

    public MutableLiveData<Integer> getLighImageViewColor() {
        return lightImageViewColor;
    }

    public void setLightImageViewColor(MutableLiveData<Integer> lightImageViewColor) {
        this.lightImageViewColor = lightImageViewColor;
    }

    public LightViewModel(@NonNull Application application) {
        super(application);
        myApplication = (MyApplication) application;
        context = MyApplication.getAppContext();
    }

    public void setupBrightnessSeekBar(SeekBar seekBar, ImageView imageView){
        seekBar.setProgress(myApplication.getProfileRepository().getActiveProfile().getLightBrightness());

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
        int brightness = myApplication.getProfileRepository().getActiveProfile().getLightBrightness();
        int[] brightnessColors = myApplication.getResources().getIntArray(R.array.brightnessColors);

        int index = Math.min(brightness / 10, brightnessColors.length - 1); // Begrenzung des Index auf den maximalen Wert im Array

        int color = brightnessColors[index];
        imageView.setColorFilter(color);


    }

}
