package de.hawhamburg.smartledapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileHolder> {

    private List<Profile> profiles = new ArrayList<Profile>();

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
        holder.profileRadioButton.setChecked(currentProfile.isStatus());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public void setProfiles(List<Profile> profiles){
        this.profiles = profiles;
        notifyDataSetChanged();
    }

    public Profile getProfileAt(int position){
        return profiles.get(position);
    }

    class ProfileHolder extends RecyclerView.ViewHolder{
        private TextView profileNameTextView;
        private TextView modeTextView;
        private RadioButton profileRadioButton;

        public ProfileHolder(@NonNull View itemView) {
            super(itemView);
            profileNameTextView = itemView.findViewById(R.id.profileNameTextView);
            modeTextView = itemView.findViewById(R.id.modeTextView);
            profileRadioButton = itemView.findViewById(R.id.profileRadioButton);
        }
    }


}
