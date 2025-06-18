package com.example.onshape.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_exercises",
        foreignKeys = @ForeignKey(entity = Routine.class,
                parentColumns = "id",
                childColumns = "routineId",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = {"routineId"})})
public class WorkoutExercise {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int routineId;
    public String name;
    public String muscle;
    public int sets;
    public int reps;

    public WorkoutExercise(int routineId, String name, String muscle, int sets, int reps) {
        this.routineId = routineId;
        this.name = name;
        this.muscle = muscle;
        this.sets = sets;
        this.reps = reps;
    }
}