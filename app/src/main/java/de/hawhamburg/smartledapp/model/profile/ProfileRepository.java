package de.hawhamburg.smartledapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProfileRepository {

    private ProfileDao profileDao;
    private LiveData<List<Profile>> allProfiles;

    public ProfileRepository(Application application){
        ProfileDatabase database = ProfileDatabase.getInstance(application);
        profileDao = database.profileDao();
        allProfiles = profileDao.getAllNotes();
    }

    public void insert(Profile profile){
        new InsertProfileAsyncTask(profileDao).execute(profile);
    }

    public void update(Profile profile){
        new UpdateProfileAsyncTask(profileDao).execute(profile);

    }

    public void delete(Profile profile){
        new DeleteProfileAsyncTask(profileDao).execute(profile);
    }

    public void deleteAllProfiles(){
        new DeleteAllProfilesAsyncTask(profileDao).execute();
    }

    public LiveData<List<Profile>> getAllProfiles(){
        return allProfiles;
    }

    private static class InsertProfileAsyncTask extends AsyncTask<Profile,Void,Void> {
        private ProfileDao profileDao;

        private InsertProfileAsyncTask(ProfileDao profileDao){
            this.profileDao = profileDao;
        }


        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.insert(profiles[0]);
            return null;
        }
    }

    private static class UpdateProfileAsyncTask extends AsyncTask<Profile,Void,Void> {
        private ProfileDao profileDao;

        private UpdateProfileAsyncTask(ProfileDao profileDao){
            this.profileDao = profileDao;
        }


        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.update(profiles[0]);
            return null;
        }
    }

    private static class DeleteProfileAsyncTask extends AsyncTask<Profile,Void,Void> {
        private ProfileDao profileDao;

        private DeleteProfileAsyncTask(ProfileDao profileDao){
            this.profileDao = profileDao;
        }


        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.delete(profiles[0]);
            return null;
        }
    }

    private static class DeleteAllProfilesAsyncTask extends AsyncTask<Void,Void,Void> {
        private ProfileDao profileDao;

        private DeleteAllProfilesAsyncTask(ProfileDao profileDao){
            this.profileDao = profileDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            profileDao.deleteAllProfiles();
            return null;
        }
    }

}


