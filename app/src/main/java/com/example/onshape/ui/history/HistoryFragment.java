package com.example.onshape.ui.history;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onshape.R;
import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private HistoryViewModel historyViewModel;
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HistoryAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        SharedPreferences prefs = requireActivity().getSharedPreferences("OnShape_prefs", Context.MODE_PRIVATE);
        int userId = prefs.getInt("LOGGED_IN_USER_ID", -1);

        if (userId != -1) {
            historyViewModel.getAllSessions(userId).observe(getViewLifecycleOwner(), sessions -> {
                if (sessions != null) {
                    adapter.setSessions(sessions);
                }
            });
        }

        return view;
    }
}