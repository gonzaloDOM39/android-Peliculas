package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.login.model.User;
import com.example.login.utils.Apis;
import com.example.login.utils.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity  extends AppCompatActivity {
    UserService userService;
    List<User> listUsers = new ArrayList<>(); // Puedes usar ArrayList<> en lugar de ArrayList<User>()
    ListView listView;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);  // Cambia esto a R.layout.user_layout

        TextView userName = findViewById(R.id.userName);
        EditText txtUserName = findViewById(R.id.textUserName);
        TextView password = findViewById(R.id.password);
        EditText txtPassword = findViewById(R.id.txtPassword);
        Button btnLogIn = findViewById(R.id.btnLogIn);

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
                    Toast.makeText(LoginActivity.this, "Ingreso exitoso", Toast.LENGTH_LONG).show();
                    // Redireccionar al HomeActivity
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish(); // Cierra la actividad actual
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }


}
