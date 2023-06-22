package de.hawhamburg.smartledapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import de.hawhamburg.smartledapp.R;

public class AddEditProfileActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "de.hawhamburg.smartledapp.view.EXTRA_ID";
    public static final String EXTRA_NAME =
            "de.hawhamburg.smartledapp.view.EXTRA_TITLE";
    public static final String EXTRA_CLAP_MODE =
            "de.hawhamburg.smartledapp.view.EXTRA_CLAP_MODE";

    private EditText editTextName;
    private Switch clapModeSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        editTextName = findViewById(R.id.edit_text_name);
        clapModeSwitch = findViewById(R.id.clap_mode_switch);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Profile");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            clapModeSwitch.setChecked(intent.getBooleanExtra(EXTRA_CLAP_MODE,true));

        } else {
            setTitle("Add Profile");
        }


    }

    private void saveProfile(){
        String name = editTextName.getText().toString();
        boolean clapMode = clapModeSwitch.isChecked();

        if (name.trim().isEmpty()){
            Toast.makeText(this,"Please insert a name.",Toast.LENGTH_SHORT).show();
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

                saveProfile();
                return true;


    }
}
