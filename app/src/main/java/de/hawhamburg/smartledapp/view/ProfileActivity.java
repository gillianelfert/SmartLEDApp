package de.hawhamburg.smartledapp.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.nio.charset.StandardCharsets;
import java.util.List;

import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.R;
import de.hawhamburg.smartledapp.model.mqtt.CalculationClass;
import de.hawhamburg.smartledapp.model.mqtt.MqttClient;
import de.hawhamburg.smartledapp.model.profile.Profile;
import de.hawhamburg.smartledapp.view.adapter.ProfileAdapter;
import de.hawhamburg.smartledapp.viewmodel.BottomNavigationHelper;
import de.hawhamburg.smartledapp.viewmodel.ProfileViewModel;

public class ProfileActivity extends AppCompatActivity {

    public static final int ADD_PROFILE_REQUEST = 1;
    public static final int EDIT_PROFILE_REQUEST = 2;

    public ProfileViewModel profileViewModel;
    BottomNavigationView bottomNavigationView;

    private RecyclerView profilesRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        BottomNavigationHelper.setupBottomNavigation(this,bottomNavigationView);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        profilesRecView = findViewById(R.id.profilesRecView);
        profilesRecView.setLayoutManager(new LinearLayoutManager(this));
        profilesRecView.setHasFixedSize(true);

        ProfileAdapter adapter = new ProfileAdapter(profileViewModel, this);
        profilesRecView.setAdapter(adapter);

        profileViewModel.getAllProfiles().observe(this, new Observer<List<Profile>>() {
            @Override
            public void onChanged(List<Profile> profiles) {
                adapter.setProfiles(profiles);
            }
        });

        FloatingActionButton buttonAddProfile = findViewById(R.id.button_add_profile);
        buttonAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, AddEditProfileActivity.class);
                startActivityForResult(intent,ADD_PROFILE_REQUEST);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                profileViewModel.delete(adapter.getProfileAt(viewHolder.getAdapterPosition()));
                Toast.makeText(ProfileActivity.this, "Profil gelöscht", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(profilesRecView);

        adapter.setOnItemClickListener(new ProfileAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Profile profile) {
                Intent intent = new Intent(ProfileActivity.this, AddEditProfileActivity.class);
                intent.putExtra(AddEditProfileActivity.EXTRA_ID,profile.getId());
                intent.putExtra(AddEditProfileActivity.EXTRA_NAME,profile.getName());
                intent.putExtra(AddEditProfileActivity.EXTRA_CLAP_MODE,profile.isReactsToClap());
                startActivityForResult(intent,EDIT_PROFILE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_PROFILE_REQUEST && resultCode == RESULT_OK){
            String name = data.getStringExtra(AddEditProfileActivity.EXTRA_NAME);
            boolean clapMode = data.getBooleanExtra(AddEditProfileActivity.EXTRA_CLAP_MODE,true);
            Profile profile = new Profile(name,clapMode,false,false,100);
            profileViewModel.insert(profile);
            Toast.makeText(this, "Profil gespeichert.", Toast.LENGTH_SHORT).show();
        }else if(requestCode == EDIT_PROFILE_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(AddEditProfileActivity.EXTRA_ID,-1);

            if(id == -1) {
                Toast.makeText(this, "Profil kann nicht aktualisiert werden.", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = data.getStringExtra(AddEditProfileActivity.EXTRA_NAME);
            boolean clapMode = data.getBooleanExtra(AddEditProfileActivity.EXTRA_CLAP_MODE,true);

            Profile profile = new Profile(name,clapMode,profileViewModel.getProfileWithID(id).isStatus(),false,100);
            profile.setId(id);
            profileViewModel.update(profile);
            Toast.makeText(this, "Profil aktualisiert.", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Profil wurde nicht gespeichert.", Toast.LENGTH_SHORT).show();
        }
    }


}