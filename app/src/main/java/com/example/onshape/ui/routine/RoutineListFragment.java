package com.example.onshape.ui.routine;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast; // Importe a classe Toast
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onshape.R;
import com.example.onshape.data.Routine; // Importe a classe Routine
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RoutineListFragment extends Fragment implements RoutineAdapter.OnRoutineInteractionListener {

    private RoutineViewModel routineViewModel;
    private RecyclerView recyclerView;
    private RoutineAdapter adapter;
    private int userId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = requireActivity().getSharedPreferences("OnShape_prefs", Context.MODE_PRIVATE);
        userId = prefs.getInt("LOGGED_IN_USER_ID", -1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_routines);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RoutineAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab_add_routine);
        fab.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_routineListFragment_to_createRoutineFragment);
        });

        routineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);

        if (userId != -1) {
            routineViewModel.getUserRoutines(userId).observe(getViewLifecycleOwner(), routines -> {
                adapter.setRoutines(routines);
            });
        }

        return view;
    }

    @Override
    public void onStartRoutine(Routine routine) {
        Toast.makeText(getContext(), "Iniciando: " + routine.name, Toast.LENGTH_SHORT).show();
    }
}