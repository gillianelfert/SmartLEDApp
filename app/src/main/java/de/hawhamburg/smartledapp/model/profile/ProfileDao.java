package de.hawhamburg.smartledapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProfileDao {
    @Insert
    void insert(Profile profile);

    @Update
    void update(Profile profile);

    @Delete
    void delete(Profile profile);

    @Query("DELETE FROM profile_table")
    void deleteAllProfiles();

    @Query("SELECT * FROM profile_table ORDER BY id DESC")
    LiveData<List<Profile>> getAllNotes();

}
