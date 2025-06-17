package com.example.onshape.ui.progress;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.onshape.R;
import com.example.onshape.data.SessionExercise;
import com.example.onshape.ui.history.HistoryViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;
import java.util.List;

public class ProgressFragment extends Fragment {

    private HistoryViewModel historyViewModel;
    private Spinner exerciseSpinner;
    private LineChart lineChart;
    private int userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        exerciseSpinner = view.findViewById(R.id.spinner_exercises_progress);
        lineChart = view.findViewById(R.id.progress_chart);
        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        SharedPreferences prefs = requireActivity().getSharedPreferences("OnShape_prefs", Context.MODE_PRIVATE);
        userId = prefs.getInt("LOGGED_IN_USER_ID", -1);

        if (userId != -1) {
            setupSpinner();
        }

        setupChartStyle();

        return view;
    }

    private void setupSpinner() {
        historyViewModel.getUniqueExerciseNames(userId).observe(getViewLifecycleOwner(), exerciseNames -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, exerciseNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            exerciseSpinner.setAdapter(adapter);
        });

        exerciseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedExercise = (String) parent.getItemAtPosition(position);
                updateChartWithExerciseData(selectedExercise);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                lineChart.clear();
                lineChart.invalidate();
            }
        });
    }

    private void updateChartWithExerciseData(String exerciseName) {
        historyViewModel.getHistoryForExercise(exerciseName, userId).observe(getViewLifecycleOwner(), sessionExercises -> {
            if (sessionExercises != null && !sessionExercises.isEmpty()) {
                ArrayList<Entry> entries = new ArrayList<>();
                for (int i = 0; i < sessionExercises.size(); i++) {
                    SessionExercise exercise = sessionExercises.get(i);
                    entries.add(new Entry(i, (float) exercise.weight));
                }

                LineDataSet dataSet = new LineDataSet(entries, "Evolução de Carga (kg)");
                dataSet.setColor(Color.BLUE);
                dataSet.setValueTextColor(Color.BLACK);
                dataSet.setCircleColor(Color.BLUE);
                dataSet.setLineWidth(2f);

                LineData lineData = new LineData(dataSet);
                lineChart.setData(lineData);
            } else {
                lineChart.clear();
            }
            lineChart.invalidate();
        });
    }

    private void setupChartStyle() {
        lineChart.getDescription().setEnabled(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.setNoDataText("Selecione um exercício para ver o progresso.");
    }
}