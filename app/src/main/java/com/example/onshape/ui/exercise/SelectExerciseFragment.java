package com.example.onshape.ui.exercise;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.example.onshape.ui.routine.RoutineViewModel;
import java.util.ArrayList;
import androidx.appcompat.widget.SearchView;

public class SelectExerciseFragment extends Fragment {

    private RoutineViewModel routineViewModel;
    private ExerciseLibraryAdapter adapter;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_exercise, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_library_exercises);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ExerciseLibraryAdapter(new ArrayList<>(), this::showSetsRepsDialog);
        recyclerView.setAdapter(adapter);

        routineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
        observeAllExercises();

        searchView = view.findViewById(R.id.search_view_exercises);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    observeAllExercises();
                } else {
                    observeSearchedExercises(newText);
                }
                return true;
            }
        });

        return view;
    }

    private void observeAllExercises() {
        routineViewModel.getAllLibraryExercises().observe(getViewLifecycleOwner(), exercises -> {
            adapter.setExercises(exercises);
        });
    }

    private void observeSearchedExercises(String query) {
        routineViewModel.searchExercisesByName(query).observe(getViewLifecycleOwner(), exercises -> {
            adapter.setExercises(exercises);
        });
    }

    private void showSetsRepsDialog(ExerciseLibrary exercise) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Adicionar " + exercise.name);

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 20, 50, 20);

        final EditText setsInput = new EditText(getContext());
        setsInput.setHint("Número de séries");
        setsInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(setsInput);

        final EditText repsInput = new EditText(getContext());
        repsInput.setHint("Número de repetições");
        repsInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(repsInput);

        builder.setView(layout);

        builder.setPositiveButton("Adicionar", (dialog, which) -> {
            String setsStr = setsInput.getText().toString();
            String repsStr = repsInput.getText().toString();

            if (setsStr.isEmpty() || repsStr.isEmpty()) {
                Toast.makeText(getContext(), "Séries e repetições são obrigatórias", Toast.LENGTH_SHORT).show();
                return;
            }

            int sets = Integer.parseInt(setsStr);
            int reps = Integer.parseInt(repsStr);

            Bundle result = new Bundle();
            result.putSerializable("selectedExercise", exercise);
            result.putInt("sets", sets);
            result.putInt("reps", reps);
            getParentFragmentManager().setFragmentResult("requestKey", result);

            Navigation.findNavController(getView()).popBackStack();
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}