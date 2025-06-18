package com.example.onshape.ui.routine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onshape.R;
import com.example.onshape.data.Routine;
import java.util.ArrayList;

public class RoutineDetailFragment extends Fragment {

    private RoutineViewModel routineViewModel;
    private Routine routine;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine_detail, container, false);

        routineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);

        if (getArguments() != null) {
            routine = (Routine) getArguments().getSerializable("routine");
        }

        TextView routineName = view.findViewById(R.id.detail_routine_name);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_detail_exercises);
        Button startButton = view.findViewById(R.id.button_start_workout_detail);
        Button deleteButton = view.findViewById(R.id.button_delete_routine);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        WorkoutExerciseAdapter adapter = new WorkoutExerciseAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        if (routine != null) {
            routineName.setText(routine.name);
            routineViewModel.getExercisesForRoutine(routine.id).observe(getViewLifecycleOwner(), adapter::setExercises);

            startButton.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("routine", routine);
                Navigation.findNavController(v).navigate(R.id.action_routineDetailFragment_to_activeWorkoutFragment, bundle);
            });

            deleteButton.setOnClickListener(v -> showDeleteConfirmationDialog());
        }

        return view;
    }

    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Excluir Rotina")
                .setMessage("Tem certeza que deseja excluir a rotina '" + routine.name + "'? Esta ação não pode ser desfeita.")
                .setPositiveButton("Excluir", (dialog, which) -> {
                    routineViewModel.deleteRoutine(routine);
                    Navigation.findNavController(requireView()).popBackStack();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}