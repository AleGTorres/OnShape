package com.example.onshape.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class HistoryRepository {
    private HistoryDao historyDao;

    public interface SessionInsertCallback {
        void onSessionInserted(long sessionId);
    }

    public HistoryRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        historyDao = db.historyDao();
    }

    public void insertSession(WorkoutSession session, SessionInsertCallback callback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            long id = historyDao.insertSession(session);
            callback.onSessionInserted(id);
        });
    }

    public void insertSessionExercise(SessionExercise sessionExercise) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            historyDao.insertSessionExercise(sessionExercise);
        });
    }

    public LiveData<List<WorkoutSession>> getAllSessionsForUser(int userId) {
        return historyDao.getAllSessionsForUser(userId);
    }

    public LiveData<List<SessionExercise>> getHistoryForExercise(String exerciseName, int userId) {
        return historyDao.getHistoryForExercise(exerciseName, userId);
    }

    public LiveData<List<String>> getUniqueCompletedExerciseNames(int userId) {
        return historyDao.getUniqueCompletedExerciseNames(userId);
    }
}