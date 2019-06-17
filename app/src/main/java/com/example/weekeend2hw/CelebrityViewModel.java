package com.example.weekeend2hw;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CelebrityViewModel extends AndroidViewModel {
    private CelebrityRepository repository;
    private LiveData<List<Celebrity>> allCelebrities;

    public CelebrityViewModel(@NonNull Application application) {
        super(application);
        repository = new CelebrityRepository(application);
        allCelebrities = repository.getAllCelebrities();
    }

    public void insert(Celebrity celebrity){
        repository.insert(celebrity);
    }

    public void update(Celebrity celebrity){
        repository.update(celebrity);
    }

    public void delete(Celebrity celebrity){
        repository.delete(celebrity);
    }

public void deleteAllNotes(){
        repository.deleteAllNotes();
}

public LiveData<List<Celebrity>> getAllCelebrities(){
        return allCelebrities;
}



}

