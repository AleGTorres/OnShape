package com.example.onshape.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String email;
    public String passwordHash;
    public String photoUri;

    public User(String name, String email, String passwordHash, String photoUri) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.photoUri = photoUri;
    }
}