package com.example.aplicacioniheartsmartpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Button btnEnterPanel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnEnterPanel = findViewById(R.id.btnEnterPanel);
        progressBar = findViewById(R.id.progressBar);

        btnEnterPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra el ProgressBar y oculta el botón
                btnEnterPanel.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                // Simula una acción (por ejemplo, un retraso de 1 segundos) y luego cambia a PanelActivity
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(WelcomeActivity.this, PanelActivity.class);
                        startActivity(intent);
                        finish(); // Cierra la actividad actual
                    }
                }, 500); // 1 segundos de retraso
            }
        });
    }
}