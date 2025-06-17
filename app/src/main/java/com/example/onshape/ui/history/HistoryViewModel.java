package com.example.onshape.ui.history;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onshape.data.HistoryRepository;
import com.example.onshape.data.SessionExercise;
import com.example.onshape.data.WorkoutSession;
import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private HistoryRepository repository;

    public HistoryViewModel(Application application) {
        super(application);
        repository = new HistoryRepository(application);
    }

    public LiveData<List<WorkoutSession>> getAllSessions(int userId) {
        return repository.getAllSessionsForUser(userId);
    }

    public LiveData<List<SessionExercise>> getHistoryForExercise(String name, int userId) {
        return repository.getHistoryForExercise(name, userId);
    }

    public LiveData<List<String>> getUniqueExerciseNames(int userId) {
        return repository.getUniqueCompletedExerciseNames(userId);
    }

    public void insertSession(WorkoutSession session, HistoryRepository.SessionInsertCallback callback) {
        repository.insertSession(session, callback);
    }

    public void insertSessionExercise(SessionExercise sessionExercise) {
        repository.insertSessionExercise(sessionExercise);
    }
}