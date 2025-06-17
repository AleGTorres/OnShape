package com.example.onshape.ui.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onshape.R;
import com.example.onshape.data.WorkoutSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<WorkoutSession> sessions;

    public HistoryAdapter(List<WorkoutSession> sessions) {
        this.sessions = sessions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_session, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WorkoutSession session = sessions.get(position);
        holder.routineName.setText(session.routineName);

        SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));
        String dateString = formatter.format(new Date(session.timestamp));
        holder.date.setText(dateString);
    }

    @Override
    public int getItemCount() {
        return sessions != null ? sessions.size() : 0;
    }

    public void setSessions(List<WorkoutSession> sessions) {
        this.sessions = sessions;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView routineName, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            routineName = itemView.findViewById(R.id.text_history_routine_name);
            date = itemView.findViewById(R.id.text_history_date);
        }
    }
}