package com.example.onshape.ui.routine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onshape.R;
import com.example.onshape.data.Routine;
import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder> {
    private List<Routine> routines;
    public interface OnRoutineInteractionListener {
        void onRoutineClick(Routine routine);
        void onStartRoutine(Routine routine);
    }
    private OnRoutineInteractionListener listener;

    public RoutineAdapter(List<Routine> routines, OnRoutineInteractionListener listener) {
        this.routines = routines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_routine, parent, false);
        return new RoutineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder holder, int position) {
        Routine currentRoutine = routines.get(position);
        holder.textViewRoutineName.setText(currentRoutine.name);
        holder.itemView.setOnClickListener(v -> listener.onRoutineClick(currentRoutine));
        holder.startButton.setOnClickListener(v -> listener.onStartRoutine(currentRoutine));
    }

    @Override
    public int getItemCount() {
        return routines != null ? routines.size() : 0;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
        notifyDataSetChanged();
    }

    static class RoutineViewHolder extends RecyclerView.ViewHolder {
        TextView textViewRoutineName;
        Button startButton;
        public RoutineViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRoutineName = itemView.findViewById(R.id.text_view_routine_name);
            startButton = itemView.findViewById(R.id.button_start_workout);
        }
    }
}