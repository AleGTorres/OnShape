package com.example.onshape.ui.routine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onshape.R;
import com.example.onshape.data.WorkoutExercise;
import java.util.List;
import java.util.Locale;

public class WorkoutExerciseAdapter extends RecyclerView.Adapter<WorkoutExerciseAdapter.ViewHolder> {
    private List<WorkoutExercise> exercises;

    public WorkoutExerciseAdapter(List<WorkoutExercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout_exercise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WorkoutExercise exercise = exercises.get(position);
        holder.name.setText(exercise.name);
        String setsReps = String.format(Locale.getDefault(), "%d séries de %d repetições", exercise.sets, exercise.reps);
        holder.setsReps.setText(setsReps);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void setExercises(List<WorkoutExercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, setsReps;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_view_exercise_name);
            setsReps = itemView.findViewById(R.id.text_view_sets_reps);
        }
    }
}