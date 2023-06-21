package de.hawhamburg.smartledapp.model.profile;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profile_table")
public class Profile {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String name;
    private boolean reactsToClap;
    private boolean status;

    public Profile(String name, boolean reactsToClap, boolean status) {
        this.name = name;
        this.reactsToClap = reactsToClap;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isReactsToClap() {
        return reactsToClap;
    }

    public boolean isStatus() {
        return status;
    }
}
