package com.desarrollo.roomdatabasesqlite.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.desarrollo.roomdatabasesqlite.repositories.ContinentRepository;
import com.desarrollo.roomdatabasesqlite.roomdatabase.Continent;

import java.util.List;

public class ContinentViewModel extends AndroidViewModel {

    private ContinentRepository continentRepository;
    private LiveData<List<Continent>> continents;

    public ContinentViewModel(@NonNull Application application) {
        super(application);
        continentRepository = new ContinentRepository(application);
        continents = continentRepository.getAllContinents();
    }

    public LiveData<List<Continent>> getAllContinent() {
        return continents;
    }

    public void insert(Continent continent) {
        continentRepository.insert(continent);
    }
}
