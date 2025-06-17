package com.example.onshape.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ExerciseLibraryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ExerciseLibrary> exercises);

    @Query("SELECT * FROM exercise_library ORDER BY name ASC")
    LiveData<List<ExerciseLibrary>> getAllExercises();

    @Query("SELECT * FROM exercise_library WHERE muscle = :muscleGroup ORDER BY name ASC")
    LiveData<List<ExerciseLibrary>> getExercisesByMuscle(String muscleGroup);

    @Query("SELECT * FROM exercise_library WHERE name LIKE '%' || :query || '%'")
    LiveData<List<ExerciseLibrary>> searchExercisesByName(String query);
}