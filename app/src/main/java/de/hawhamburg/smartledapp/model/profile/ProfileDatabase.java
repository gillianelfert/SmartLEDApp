package de.hawhamburg.smartledapp.model.profile;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Profile.class}, version = 1)
public abstract class ProfileDatabase extends RoomDatabase {

    private static ProfileDatabase instance;

    public abstract ProfileDao profileDao();

    public static synchronized ProfileDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ProfileDatabase.class,"profile_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            System.out.println("Populated");
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private ProfileDao profileDao;

        private PopulateDbAsyncTask(ProfileDatabase db) {
            profileDao = db.profileDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }

}
