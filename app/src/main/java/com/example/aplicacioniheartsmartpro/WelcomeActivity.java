package com.example.aplicacioniheartsmartpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
//Creamos la instancia al Layout activity_welcome.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome); // Asocia con el layout activity_welcome.xml
        //Declaramos nuestro elementos del Layout, ademas
        Button btnEnterPanel = findViewById(R.id.btnEnterPanel);
        //Procedemos a realizar la funcionalidad que nos permitira y a la vista Main
        btnEnterPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiar a MainActivity al hacer clic
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}