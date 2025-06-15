package com.example.onshape.ui;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.example.onshape.data.User;
import com.example.onshape.data.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    public UserViewModel(Application application) {
        super(application);
        mRepository = new UserRepository(application);
    }

    public void insert(User user) {
        mRepository.insert(user);
    }

    public User findByEmail(String email) {
        return mRepository.findByEmail(email);
    }
}