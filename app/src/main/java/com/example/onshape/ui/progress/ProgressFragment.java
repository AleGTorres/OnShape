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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.onshape.R;
import com.example.onshape.data.SessionExercise;
import com.example.onshape.ui.history.HistoryViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

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
            if (exerciseNames != null && !exerciseNames.isEmpty()) {
                exerciseNames.add(0, "Selecione um exercício...");
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, exerciseNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            exerciseSpinner.setAdapter(adapter);
        });

        exerciseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    lineChart.clear();
                    lineChart.invalidate();
                    return;
                }
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

                int purpleColor = ContextCompat.getColor(getContext(), R.color.purple_primary);
                dataSet.setColor(purpleColor);
                dataSet.setCircleColor(purpleColor);
                dataSet.setCircleHoleColor(purpleColor);
                dataSet.setValueTextColor(Color.WHITE);
                dataSet.setLineWidth(2.5f);
                dataSet.setCircleRadius(5f);
                dataSet.setValueTextSize(10f);

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
        lineChart.setNoDataText("Selecione um exercício para ver o progresso.");
        lineChart.setNoDataTextColor(Color.WHITE);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(Color.GRAY);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setTextColor(Color.WHITE);
        yAxisLeft.setAxisLineColor(Color.GRAY);
        yAxisLeft.setGridColor(Color.parseColor("#40FFFFFF"));

        lineChart.getAxisRight().setEnabled(false);

        Legend legend = lineChart.getLegend();
        legend.setTextColor(Color.WHITE);
    }
}