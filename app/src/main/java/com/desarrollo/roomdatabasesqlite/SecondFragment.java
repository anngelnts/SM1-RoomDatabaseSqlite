package com.desarrollo.roomdatabasesqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.desarrollo.roomdatabasesqlite.adapters.ContinentListAdapter;
import com.desarrollo.roomdatabasesqlite.roomdatabase.Continent;
import com.desarrollo.roomdatabasesqlite.viewmodel.ContinentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SecondFragment extends Fragment {

    public static final int NEW_CONTINENT_RESULT = 1;
    private ContinentViewModel continentViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //recyclerview continents
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_continents);
        final ContinentListAdapter adapter = new ContinentListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        continentViewModel = ViewModelProviders.of(this).get(ContinentViewModel.class);

        continentViewModel.getAllContinent().observe(getViewLifecycleOwner(), new Observer<List<Continent>>() {
            @Override
            public void onChanged(List<Continent> continents) {
                adapter.setContinents(continents);
            }
        });

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_continents);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContinentRegisterActivity.class);
                startActivityForResult(intent, NEW_CONTINENT_RESULT);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_CONTINENT_RESULT && resultCode == Activity.RESULT_OK) {
            assert data != null;
            String name = data.getStringExtra(ContinentRegisterActivity.RESPONSE_NAME);
            assert name != null;
            Continent continent = new Continent(name);
            continentViewModel.insert(continent);
        }else {
            Toast.makeText(getContext(), "THERE IS NO DATA", Toast.LENGTH_SHORT).show();
        }
    }
}
