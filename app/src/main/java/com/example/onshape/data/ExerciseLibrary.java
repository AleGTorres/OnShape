package com.example.onshape.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "exercise_library")
public class ExerciseLibrary implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String muscle;
    public String equipment;
    public String instructions;

    public ExerciseLibrary(String name, String muscle, String equipment, String instructions) {
        this.name = name;
        this.muscle = muscle;
        this.equipment = equipment;
        this.instructions = instructions;
    }
}