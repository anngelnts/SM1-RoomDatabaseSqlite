package com.desarrollo.roomdatabasesqlite.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.desarrollo.roomdatabasesqlite.repositories.AnimalRepository;
import com.desarrollo.roomdatabasesqlite.roomdatabase.Animal;

import java.util.List;

public class AnimalViewModel extends AndroidViewModel {

    private AnimalRepository animalRepository;
    private LiveData<List<Animal>> animals;

    public AnimalViewModel(@NonNull Application application) {
        super(application);
        animalRepository = new AnimalRepository(application);
        animals = animalRepository.getAllAnimals();
    }

    public LiveData<List<Animal>> getAllAnimals() {
        return animals;
    }

    public void insert(Animal animal) {
        animalRepository.insert(animal);
    }
}
