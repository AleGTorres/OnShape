package com.example.onshape.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface RoutineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertRoutine(Routine routine);

    @Query("SELECT * FROM routines WHERE userId = :userId ORDER BY name ASC")
    LiveData<List<Routine>> getUserRoutines(int userId);

    @Insert
    void insertWorkoutExercise(WorkoutExercise exercise);

    @Query("SELECT * FROM workout_exercises WHERE routineId = :routineId")
    LiveData<List<WorkoutExercise>> getExercisesForRoutine(int routineId);
}