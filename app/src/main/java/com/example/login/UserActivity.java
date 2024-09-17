package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.model.User;
import com.example.login.utils.Apis;
import com.example.login.utils.UserService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    UserService userService;
    private TextInputEditText userNameEditText, emailEditText, passwordEditText, ConfPasswordEditText, lastNameEditText, firstNameEditText;
    private Button btnSaveUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        // Inicialización de vistas
        userNameEditText = findViewById(R.id.addUserName);
        emailEditText = findViewById(R.id.addEmail);
        passwordEditText = findViewById(R.id.addPassword);
        ConfPasswordEditText = findViewById(R.id.confirmPassword);
        lastNameEditText = findViewById(R.id.addlastName);
        firstNameEditText = findViewById(R.id.addfirstName);
        btnSaveUser = findViewById(R.id.btnSaveUser); // Asegúrate de que este ID sea el correcto

        btnSaveUser.setOnClickListener(v -> {
            Log.i("MAIN ACTIVITY ARIS", "entra al boton ");

            // Validación básica
            if (isInputValid()) {
                User user = new User();
                user.setEmail(emailEditText.getText().toString());
                user.setUsername(userNameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());
                user.setLastName(lastNameEditText.getText().toString());
                user.setFirstName(firstNameEditText.getText().toString()); // Corregido de username a firstName

                Log.i("MAIN ACTIVITY ARIS", "el usuario es: " + user.getUsername());
                addUser(user);
            } else {
                Toast.makeText(UserActivity.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isInputValid() {
        return !userNameEditText.getText().toString().trim().isEmpty()
                && !emailEditText.getText().toString().trim().isEmpty()
                && !passwordEditText.getText().toString().trim().isEmpty()
                && !ConfPasswordEditText.getText().toString().trim().isEmpty()
                && !lastNameEditText.getText().toString().trim().isEmpty()
                && !firstNameEditText.getText().toString().trim().isEmpty()
                && passwordEditText.getText().toString().equals(ConfPasswordEditText.getText().toString()); // Asegúrate de que las contraseñas coincidan
    }

    public void addUser(User user) {
        userService = Apis.getUserService();
        Call<User> call = userService.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User createdUser = response.body();
                    if (createdUser != null) {
                        Toast.makeText(getApplicationContext(), "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No se recibieron datos del usuario", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error en la creación del usuario: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
