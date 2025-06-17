package com.example.onshape.ui.routine;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onshape.R;
import com.example.onshape.data.ExerciseLibrary;
import com.example.onshape.data.Routine;
import com.example.onshape.data.WorkoutExercise;
import java.util.ArrayList;

public class CreateRoutineFragment extends Fragment {

    private RoutineViewModel routineViewModel;
    private EditText routineNameEditText;
    private long currentRoutineId = -1;
    private WorkoutExerciseAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("requestKey", this, (requestKey, bundle) -> {
            ExerciseLibrary selectedExercise = (ExerciseLibrary) bundle.getSerializable("selectedExercise");
            int sets = bundle.getInt("sets");
            int reps = bundle.getInt("reps");

            if (selectedExercise != null && currentRoutineId != -1) {
                WorkoutExercise workoutExercise = new WorkoutExercise(
                        (int) currentRoutineId,
                        selectedExercise.name,
                        selectedExercise.muscle,
                        sets,
                        reps
                );
                routineViewModel.insertWorkoutExercise(workoutExercise);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_routine, container, false);

        routineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
        routineNameEditText = view.findViewById(R.id.edit_text_routine_name);
        Button addExerciseButton = view.findViewById(R.id.button_add_exercise);
        Button saveRoutineButton = view.findViewById(R.id.button_save_routine);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_workout_exercises);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WorkoutExerciseAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        addExerciseButton.setOnClickListener(v -> handleAddExercise(v));
        saveRoutineButton.setOnClickListener(v -> saveRoutineAndExit(v));

        return view;
    }

    private void handleAddExercise(View view) {
        String routineName = routineNameEditText.getText().toString().trim();
        if (TextUtils.isEmpty(routineName)) {
            Toast.makeText(getContext(), "Por favor, dê um nome à rotina primeiro.", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = requireActivity().getSharedPreferences("OnShape_prefs", Context.MODE_PRIVATE);
        int userId = prefs.getInt("LOGGED_IN_USER_ID", -1);

        if (userId == -1) {
            Toast.makeText(getContext(), "Erro: Usuário não identificado. Por favor, faça login novamente.", Toast.LENGTH_LONG).show();
            return;
        }

        if (currentRoutineId == -1) {
            Routine newRoutine = new Routine(routineName, userId);
            routineViewModel.insertRoutine(newRoutine, id -> {
                currentRoutineId = id;
                observeExercises();
                navigateToSelectExercise(view);
            });
        } else {
            navigateToSelectExercise(view);
        }
    }

    private void navigateToSelectExercise(View view) {
        Navigation.findNavController(view).navigate(R.id.action_createRoutineFragment_to_selectExerciseFragment);
    }

    private void observeExercises() {
        if (currentRoutineId != -1) {
            routineViewModel.getExercisesForRoutine((int) currentRoutineId).observe(getViewLifecycleOwner(), workoutExercises -> {
                adapter.setExercises(workoutExercises);
            });
        }
    }

    private void saveRoutineAndExit(View view) {
        if (currentRoutineId == -1 && TextUtils.isEmpty(routineNameEditText.getText().toString().trim())) {
            Toast.makeText(getContext(), "Crie uma rotina e adicione exercícios antes de salvar.", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getContext(), "Rotina salva com sucesso!", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).popBackStack();
    }
}