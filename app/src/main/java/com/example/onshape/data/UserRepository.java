package com.example.onshape.data;

import android.app.Application;

public class UserRepository {
    private UserDao mUserDao;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.userDao();
    }

    public void insert(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }

    public User findByEmail(String email) {
        try {
            return AppDatabase.databaseWriteExecutor.submit(() -> mUserDao.findByEmail(email)).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}