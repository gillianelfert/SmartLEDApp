package de.hawhamburg.smartledapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import de.hawhamburg.smartledapp.R;
import de.hawhamburg.smartledapp.model.profile.Profile;
import de.hawhamburg.smartledapp.viewmodel.ProfileViewModel;
import de.hawhamburg.smartledapp.MyApplication;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileHolder> {

    private List<Profile> profiles = new ArrayList<>();

    private ProfileViewModel profileViewModel;
    private OnItemClickListener listener;
    private MyApplication myApplication;

    public ProfileAdapter(ProfileViewModel profileViewModel, Context context) {
        this.profileViewModel = profileViewModel;
        this.myApplication = (MyApplication) context.getApplicationContext();
    }

    @NonNull
    @Override
    public ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profiles_list_item, parent, false);
        return new ProfileHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder holder, int position) {
        Profile currentProfile = profiles.get(position);
        holder.profileNameTextView.setText(currentProfile.getName());

        holder.modeTextView.setText(currentProfile.getModeString());
        holder.modeTextView.setOnClickListener(v -> {
            currentProfile.toggleMode();
            profileViewModel.update(currentProfile);
        });

        holder.profileRadioButton.setChecked(currentProfile.isStatus());
        holder.profileRadioButton.setOnClickListener(v->{
            for(Profile p : profiles){
                if (p == currentProfile){
                    p.setActive();
                }
                else{
                    p.setInactive();
                }
                profileViewModel.update(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
        notifyDataSetChanged();
    }

    public Profile getProfileAt(int position) {
        return profiles.get(position);
    }

    class ProfileHolder extends RecyclerView.ViewHolder {
        private TextView profileNameTextView;
        private TextView modeTextView;
        private RadioButton profileRadioButton;

        public ProfileHolder(@NonNull View itemView) {
            super(itemView);
            profileNameTextView = itemView.findViewById(R.id.profileNameTextView);
            modeTextView = itemView.findViewById(R.id.modeTextView);
            profileRadioButton = itemView.findViewById(R.id.profileRadioButton);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(profiles.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Profile profile);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
