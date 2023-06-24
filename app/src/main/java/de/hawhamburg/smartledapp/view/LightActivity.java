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
    private MyApplication myApplication;
    private BottomNavigationView bottomNavigationView;
    private CalculationClass calculationClass = new CalculationClass(this);
    private SeekBar brightnessSeekBar;
    private ImageView lightImageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApplication = (MyApplication) this.getApplication();
        lightViewModel = new ViewModelProvider(this).get(LightViewModel.class);

        //connectToMQTT();

        setContentView(R.layout.activity_light);

        getSupportActionBar().hide();

        lightImageView = findViewById(R.id.lightImageView);
        lightViewModel.setupLightImageView(lightImageView);

        brightnessSeekBar = findViewById(R.id.brightnessSeekBar);
        lightViewModel.setupBrightnessSeekBar(brightnessSeekBar, lightImageView);




        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.light);
        BottomNavigationHelper.setupBottomNavigation(this,bottomNavigationView);

    }

    private void connectToMQTT() {
        try {
            calculationClass.connectToMQTT();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}