package com.example.onshape.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    long insertSession(WorkoutSession session);

    @Insert
    void insertSessionExercise(SessionExercise sessionExercise);

    @Query("SELECT * FROM workout_sessions WHERE userId = :userId ORDER BY timestamp DESC")
    LiveData<List<WorkoutSession>> getAllSessionsForUser(int userId);

    @Query("SELECT * FROM session_exercises WHERE name = :exerciseName AND sessionId IN (SELECT id FROM workout_sessions WHERE userId = :userId) ORDER BY sessionId ASC")
    LiveData<List<SessionExercise>> getHistoryForExercise(String exerciseName, int userId);

    @Query("SELECT DISTINCT name FROM session_exercises WHERE sessionId IN (SELECT id FROM workout_sessions WHERE userId = :userId)")
    LiveData<List<String>> getUniqueCompletedExerciseNames(int userId);
}