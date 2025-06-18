package com.example.onshape.ui.workout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onshape.R;
import com.example.onshape.data.Routine;
import com.example.onshape.data.SessionExercise;
import com.example.onshape.data.WorkoutExercise;
import com.example.onshape.data.WorkoutSession;
import com.example.onshape.ui.history.HistoryViewModel;
import com.example.onshape.ui.routine.RoutineViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.onshape.util.NotificationHelper;

public class ActiveWorkoutFragment extends Fragment {

    private RoutineViewModel routineViewModel;
    private HistoryViewModel historyViewModel;
    private ActiveWorkoutAdapter adapter;
    private List<WorkoutExercise> exerciseList = new ArrayList<>();
    private Routine currentRoutine;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active_workout, container, false);

        routineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        TextView routineNameTitle = view.findViewById(R.id.text_routine_name_title);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_active_exercises);
        Button finishButton = view.findViewById(R.id.button_finish_workout);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ActiveWorkoutAdapter(exerciseList);
        recyclerView.setAdapter(adapter);

        if (getArguments() != null) {
            currentRoutine = (Routine) getArguments().getSerializable("routine");
            if (currentRoutine != null) {
                routineNameTitle.setText(currentRoutine.name);
                routineViewModel.getExercisesForRoutine(currentRoutine.id).observe(getViewLifecycleOwner(), workoutExercises -> {
                    exerciseList.clear();
                    exerciseList.addAll(workoutExercises);
                    adapter.notifyDataSetChanged();
                });
            }
        }

        finishButton.setOnClickListener(v -> finishWorkout(v));

        return view;
    }

    private void finishWorkout(View view) {
        SharedPreferences prefs = requireActivity().getSharedPreferences("OnShape_prefs", Context.MODE_PRIVATE);
        int userId = prefs.getInt("LOGGED_IN_USER_ID", -1);

        if (userId == -1 || currentRoutine == null) {
            Toast.makeText(getContext(), "Erro ao salvar. Tente novamente.", Toast.LENGTH_SHORT).show();
            return;
        }

        WorkoutSession session = new WorkoutSession(userId, currentRoutine.name, System.currentTimeMillis());
        historyViewModel.insertSession(session, sessionId -> {
            Map<Integer, Double> weights = adapter.getWeightsMap();
            for (int i = 0; i < exerciseList.size(); i++) {
                WorkoutExercise exercise = exerciseList.get(i);
                double weight = weights.getOrDefault(i, 0.0);

                SessionExercise sessionExercise = new SessionExercise(
                        (int) sessionId,
                        exercise.name,
                        exercise.sets,
                        exercise.reps,
                        weight
                );
                historyViewModel.insertSessionExercise(sessionExercise);
            }

            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    NotificationHelper.showWorkoutCompletedNotification(getContext(), currentRoutine.name);
                    Toast.makeText(getContext(), "Treino salvo no histórico!", Toast.LENGTH_LONG).show();
                    Navigation.findNavController(view).popBackStack();
                });
            }
        });
    }
}