package de.hawhamburg.smartledapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.R;
import de.hawhamburg.smartledapp.model.mqtt.CalculationClass;
import de.hawhamburg.smartledapp.viewmodel.BottomNavigationHelper;
import de.hawhamburg.smartledapp.viewmodel.LightViewModel;

public class LightActivity extends AppCompatActivity {
    private LightViewModel lightViewModel;
    private BottomNavigationView bottomNavigationView;
    private SeekBar brightnessSeekBar;
    private ImageView lightImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);


        getSupportActionBar().hide();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.light);
        BottomNavigationHelper.setupBottomNavigation(this,bottomNavigationView);

        lightViewModel = new ViewModelProvider(this).get(LightViewModel.class);

        lightImageView = findViewById(R.id.lightImageView);
        lightViewModel.setupLightImageView(lightImageView);

        brightnessSeekBar = findViewById(R.id.brightnessSeekBar);
        lightViewModel.setupBrightnessSeekBar(brightnessSeekBar, lightImageView);
    }
}