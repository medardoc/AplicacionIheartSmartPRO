package com.example.aplicacioniheartsmartpro;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

public class CronometroActivity extends AppCompatActivity {
    private Chronometer cronometro;
    private Button btnIniciar, btnPausar, btnReiniciar, btnRegresarInicio;
    private long pausaOffset; // Para guardar el tiempo pausado
    private boolean cronometroCorriendo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cronometro_activity);

        // Vinculación de vistas
        cronometro = findViewById(R.id.cronometro);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnPausar = findViewById(R.id.btnPausar);
        btnReiniciar = findViewById(R.id.btnReiniciar);
        btnRegresarInicio = findViewById(R.id.btnRegresarInicio);

        // Listener para iniciar el cronómetro
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cronometroCorriendo) {
                    cronometro.setBase(SystemClock.elapsedRealtime() - pausaOffset);
                    cronometro.start();
                    cronometroCorriendo = true;
                }
            }
        });

        // Listener para pausar el cronómetro
        btnPausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cronometroCorriendo) {
                    cronometro.stop();
                    pausaOffset = SystemClock.elapsedRealtime() - cronometro.getBase();
                    cronometroCorriendo = false;
                }
            }
        });

        // Listener para reiniciar el cronómetro
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cronometro.setBase(SystemClock.elapsedRealtime());
                pausaOffset = 0;
                if (cronometroCorriendo) {
                    cronometro.start();
                }
            }
        });

        // Listener para regresar al inicio
        btnRegresarInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresarInicio();
            }
        });
    }

    // Método para regresar al panel principal
    private void regresarInicio() {
        Intent intent = new Intent(CronometroActivity.this, PanelActivity.class);
        startActivity(intent);
        finish();
    }
}
