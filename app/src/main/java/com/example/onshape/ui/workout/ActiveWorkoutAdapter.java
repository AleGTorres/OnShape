package com.example.onshape.ui.workout;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onshape.R;
import com.example.onshape.data.WorkoutExercise;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ActiveWorkoutAdapter extends RecyclerView.Adapter<ActiveWorkoutAdapter.ViewHolder> {
    private List<WorkoutExercise> exercises;
    private Map<Integer, Double> weightsMap = new HashMap<>();

    public ActiveWorkoutAdapter(List<WorkoutExercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_active_exercise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WorkoutExercise exercise = exercises.get(position);
        holder.bind(exercise, position);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public Map<Integer, Double> getWeightsMap() {
        return weightsMap;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseName, setsReps;
        EditText weightInput;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.text_exercise_name);
            setsReps = itemView.findViewById(R.id.text_sets_reps);
            weightInput = itemView.findViewById(R.id.edit_text_weight);
        }

        void bind(WorkoutExercise exercise, int position) {
            exerciseName.setText(exercise.name);
            setsReps.setText(String.format(Locale.getDefault(), "%d séries de %d repetições", exercise.sets, exercise.reps));

            weightInput.removeTextChangedListener((TextWatcher) weightInput.getTag());

            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try {
                        weightsMap.put(position, Double.parseDouble(s.toString()));
                    } catch (NumberFormatException e) {
                        weightsMap.remove(position);
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {}
            };
            weightInput.addTextChangedListener(textWatcher);
            weightInput.setTag(textWatcher);
        }
    }
}