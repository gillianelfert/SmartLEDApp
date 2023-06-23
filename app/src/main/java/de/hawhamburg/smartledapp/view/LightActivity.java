package de.hawhamburg.smartledapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.R;
import de.hawhamburg.smartledapp.model.mqtt.CalculationClass;
import de.hawhamburg.smartledapp.viewmodel.LightViewModel;

public class LightActivity extends AppCompatActivity {
    private LightViewModel lightViewModel;
    private MyApplication myApplication;
    private BottomNavigationView bottomNavigationView;
    private CalculationClass calculationClass = new CalculationClass(this);
    private SeekBar brightnessSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApplication = MyApplication.getInstance();
        lightViewModel = new ViewModelProvider(this).get(LightViewModel.class);

        //connectToMQTT();

        setContentView(R.layout.activity_light);

        getSupportActionBar().hide();

        brightnessSeekBar = findViewById(R.id.brightnessSeekBar);
        lightViewModel.setupBrightnessSeekBar(brightnessSeekBar);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.light);


    }

    private void connectToMQTT() {
        try {
            calculationClass.connectToMQTT();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}