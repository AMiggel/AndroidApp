package co.com.unac.susalud.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.com.unac.susalud.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void ingresar(View view){
        Intent iniciarSesion= new Intent(LoginActivity.this,WelcomeActivity.class);

    }
}
