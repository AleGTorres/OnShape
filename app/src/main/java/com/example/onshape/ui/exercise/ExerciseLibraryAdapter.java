package com.example.onshape.ui.exercise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onshape.R;
import com.example.onshape.data.ExerciseLibrary;
import java.util.List;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.content.Context;

public class ExerciseLibraryAdapter extends RecyclerView.Adapter<ExerciseLibraryAdapter.ViewHolder> {
    private List<ExerciseLibrary> exercises;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ExerciseLibrary exercise);
    }

    public ExerciseLibraryAdapter(List<ExerciseLibrary> exercises, OnItemClickListener listener) {
        this.exercises = exercises;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise_library, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExerciseLibrary exercise = exercises.get(position);
        holder.name.setText(exercise.name);
        holder.muscle.setText(exercise.muscle);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(exercise));

        Context context = holder.itemView.getContext();
        int imageResId = context.getResources().getIdentifier(exercise.imageName, "drawable", context.getPackageName());

        Glide.with(context)
                .load(imageResId)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.exerciseImage);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void setExercises(List<ExerciseLibrary> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, muscle;
        ImageView exerciseImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_view_library_exercise_name);
            muscle = itemView.findViewById(R.id.text_view_library_muscle);
            exerciseImage = itemView.findViewById(R.id.image_view_exercise);
        }
    }
}