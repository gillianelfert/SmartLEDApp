package de.hawhamburg.smartledapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.R;
import de.hawhamburg.smartledapp.viewmodel.AddEditProfileViewModel;

public class AddEditProfileActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "de.hawhamburg.smartledapp.view.EXTRA_ID";
    public static final String EXTRA_NAME =
            "de.hawhamburg.smartledapp.view.EXTRA_TITLE";
    public static final String EXTRA_CLAP_MODE =
            "de.hawhamburg.smartledapp.view.EXTRA_CLAP_MODE";

    private EditText editTextName;
    private Switch modeSwitch;
    private TextView lightModeTextView, clapModeTextView;
    MyApplication myApplication;

    AddEditProfileViewModel addEditProfileViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        addEditProfileViewModel = new AddEditProfileViewModel();

        myApplication = (MyApplication) getApplication();

        editTextName = findViewById(R.id.nameEditText);
        modeSwitch = findViewById(R.id.modeSwitch);
        lightModeTextView = findViewById(R.id.lightModeTextView);
        clapModeTextView = findViewById(R.id.clapModeTextView);

        addEditProfileViewModel.setupTextViewColors(lightModeTextView, clapModeTextView, modeSwitch);

        modeSwitch.setOnClickListener(v->{
            addEditProfileViewModel.setupTextViewColors(lightModeTextView, clapModeTextView, modeSwitch);
        });


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Profil bearbeiten");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            modeSwitch.setChecked(intent.getBooleanExtra(EXTRA_CLAP_MODE,true));

        } else {
            setTitle("Profil hinzufügen");
        }


    }

    private void saveProfile(){
        String name = editTextName.getText().toString();
        boolean clapMode = modeSwitch.isChecked();

        if (name.trim().isEmpty()){
            Toast.makeText(this,"Bitte einen Namen eingeben",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME,name);
        data.putExtra(EXTRA_CLAP_MODE,clapMode);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if(id != -1){
            data.putExtra(EXTRA_ID,id);

        }

        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_profile_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_profile){
            saveProfile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
