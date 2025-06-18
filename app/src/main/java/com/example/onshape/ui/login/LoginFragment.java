package com.example.onshape.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.onshape.R;
import com.example.onshape.data.AppDatabase;
import com.example.onshape.data.User;
import com.example.onshape.ui.UserViewModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginFragment extends Fragment {

    private UserViewModel userViewModel;
    private EditText editTextEmail, editTextPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        editTextEmail = view.findViewById(R.id.edit_text_email);
        editTextPassword = view.findViewById(R.id.edit_text_password);
        Button buttonLogin = view.findViewById(R.id.button_login);
        Button buttonGoToRegister = view.findViewById(R.id.button_go_to_register);

        buttonLogin.setOnClickListener(v -> loginUser());
        buttonGoToRegister.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment);
        });

        return view;
    }

    private void loginUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "E-mail e senha são obrigatórios", Toast.LENGTH_SHORT).show();
            return;
        }

        AppDatabase.databaseWriteExecutor.execute(() -> {
            User user = userViewModel.findByEmail(email);
            String passwordHash = hashPassword(password);

            getActivity().runOnUiThread(() -> {
                if (user != null && user.passwordHash.equals(passwordHash)) {

                    android.content.SharedPreferences prefs = requireActivity().getSharedPreferences("OnShape_prefs", android.content.Context.MODE_PRIVATE);
                    prefs.edit().putInt("LOGGED_IN_USER_ID", user.id).apply();

                    Toast.makeText(getContext(), "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_routineListFragment);
                } else {
                    Toast.makeText(getContext(), "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2) h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}