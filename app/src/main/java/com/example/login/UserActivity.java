package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.model.User;
import com.example.login.utils.Apis;
import com.example.login.utils.UserService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    UserService userService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);
        TextView userName =  findViewById(R.id.userName);
        EditText txtUserName =  findViewById(R.id.textUserName);
        TextView password =  findViewById(R.id.password);
        EditText txtPassword = findViewById(R.id.txtPassword);

        Button btnLogIn =  findViewById(R.id.btnLogIn);

        btnLogIn.setOnClickListener(v -> {
            Log.i("MAIN ACTIVITY ARIS", "entra al boton ");
            User user = new User();
            user.setUsername(txtUserName.getText().toString());
            user.setPassword(txtPassword.getText().toString());
            Log.i("MAIN ACTIVITY ARIS", "el usuario es:"+ user.getUsername());
            LogInUser(user);
        });
    }

    public void LogInUser(User user) {
        userService = Apis.getUserService();
        Call<User> call = userService.validateLogin(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UserActivity.this, "Ingreso exitoso", Toast.LENGTH_LONG).show();
                    // Redireccionar al HomeActivity
                    Intent intent = new Intent(UserActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish(); // Cierra la actividad actual
                } else {
                    Toast.makeText(UserActivity.this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
