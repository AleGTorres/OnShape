package com.example.onshape.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index; // Importe a classe Index
import androidx.room.PrimaryKey;

@Entity(tableName = "routines",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = {"userId"})})
public class Routine {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int userId;

    public Routine(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }
}