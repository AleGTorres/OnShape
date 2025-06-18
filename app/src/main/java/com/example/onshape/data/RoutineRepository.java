package com.example.onshape.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class RoutineRepository {
    private RoutineDao routineDao;
    private ExerciseLibraryDao exerciseLibraryDao;

    public RoutineRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        routineDao = db.routineDao();
        exerciseLibraryDao = db.exerciseLibraryDao();
    }

    public LiveData<List<Routine>> getUserRoutines(int userId) {
        return routineDao.getUserRoutines(userId);
    }

    public LiveData<List<WorkoutExercise>> getExercisesForRoutine(int routineId) {
        return routineDao.getExercisesForRoutine(routineId);
    }

    public LiveData<List<ExerciseLibrary>> getAllLibraryExercises() {
        return exerciseLibraryDao.getAllExercises();
    }

    public interface InsertCallback {
        void onComplete(long id);
    }

    public void insertRoutine(Routine routine, InsertCallback callback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            long id = routineDao.insertRoutine(routine);
            if (callback != null) {
                callback.onComplete(id);
            }
        });
    }

    public void insertWorkoutExercise(WorkoutExercise exercise) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            routineDao.insertWorkoutExercise(exercise);
        });
    }

    public LiveData<List<ExerciseLibrary>> searchExercisesByName(String query) {
        return exerciseLibraryDao.searchExercisesByName(query);
    }

    public void deleteRoutine(Routine routine) {
        AppDatabase.databaseWriteExecutor.execute(() -> routineDao.delete(routine));
    }
}