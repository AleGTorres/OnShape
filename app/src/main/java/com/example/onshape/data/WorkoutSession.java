package com.example.onshape.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_sessions",
        foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = "userId")})
public class WorkoutSession {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int userId;
    public String routineName;
    public long timestamp;

    public WorkoutSession(int userId, String routineName, long timestamp) {
        this.userId = userId;
        this.routineName = routineName;
        this.timestamp = timestamp;
    }
}