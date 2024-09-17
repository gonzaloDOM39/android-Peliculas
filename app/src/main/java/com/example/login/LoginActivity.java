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

import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity  extends AppCompatActivity {
    UserService userService;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView sinCuenta;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        EditText txtUserName = findViewById(R.id.textUserName);
        EditText txtPassword = findViewById(R.id.txtPassword);
        Button btnLogIn = findViewById(R.id.btnLogIn);
        TextView sinCuenta = findViewById(R.id.sinCuenta); // Cambiado a TextView si es un enlace a otra actividad

        // Configurar el clic del botón de inicio de sesión
        btnLogIn.setOnClickListener(v -> {
            Log.i("MAIN ACTIVITY ARIS", "entra al boton ");

            // Obtener valores de los campos
            String username = txtUserName.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();

            // Validar campos
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Crear objeto de usuario
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            Log.i("MAIN ACTIVITY ARIS", "el usuario es: " + user.getUsername());
            LogInUser(user);
        });


        sinCuenta.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);

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


      /*  Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual*/

}
