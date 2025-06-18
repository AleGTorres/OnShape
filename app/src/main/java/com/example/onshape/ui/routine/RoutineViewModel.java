package com.example.onshape.ui.routine;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.onshape.data.ExerciseLibrary;
import com.example.onshape.data.Routine;
import com.example.onshape.data.RoutineRepository;
import com.example.onshape.data.WorkoutExercise;
import java.util.List;

public class RoutineViewModel extends AndroidViewModel {
    private RoutineRepository repository;

    public RoutineViewModel(Application application) {
        super(application);
        repository = new RoutineRepository(application);
    }

    public LiveData<List<Routine>> getUserRoutines(int userId) {
        return repository.getUserRoutines(userId);
    }

    public LiveData<List<WorkoutExercise>> getExercisesForRoutine(int routineId) {
        return repository.getExercisesForRoutine(routineId);
    }

    public LiveData<List<ExerciseLibrary>> getAllLibraryExercises() {
        return repository.getAllLibraryExercises();
    }

    public void insertRoutine(Routine routine, RoutineRepository.InsertCallback callback) {
        repository.insertRoutine(routine, callback);
    }

    public void insertWorkoutExercise(WorkoutExercise exercise) {
        repository.insertWorkoutExercise(exercise);
    }

    public LiveData<List<ExerciseLibrary>> searchExercisesByName(String query) {
        return repository.searchExercisesByName(query);
    }

    public void deleteRoutine(Routine routine) {
        repository.deleteRoutine(routine);
    }
}