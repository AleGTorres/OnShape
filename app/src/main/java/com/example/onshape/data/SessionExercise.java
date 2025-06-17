package com.example.onshape.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "session_exercises",
        foreignKeys = @ForeignKey(entity = WorkoutSession.class, parentColumns = "id", childColumns = "sessionId", onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = "sessionId")})
public class SessionExercise {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int sessionId;
    public String name;
    public int sets;
    public int reps;
    public double weight;

    public SessionExercise(int sessionId, String name, int sets, int reps, double weight) {
        this.sessionId = sessionId;
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }
}