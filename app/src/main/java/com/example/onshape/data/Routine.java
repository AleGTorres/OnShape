package com.example.onshape.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "routines",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = {"userId"})})
public class Routine implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int userId;

    public Routine(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }
}