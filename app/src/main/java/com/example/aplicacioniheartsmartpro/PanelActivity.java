package com.example.aplicacioniheartsmartpro;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class PanelActivity extends AppCompatActivity{

    private Button btnIrCalcularIMC;
    private Button btnIrGimnasio;
    private Button btnIrNutricionista;
    private Button btnIrContadorPasos;
    private Button btnIrAlimentos;
    private Button btnIrCronometro;
    private Button btnIrControlPeso;
    private Button btnRegresarInicio;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_activity);

        btnIrCalcularIMC = findViewById(R.id.btnIrCalcularIMC);
        btnIrGimnasio = findViewById(R.id.btnIrGimnasio);
        btnIrNutricionista = findViewById(R.id.btnIrNutricionista);
        btnIrContadorPasos = findViewById(R.id.btnIrContadorPasos);
        btnIrAlimentos = findViewById(R.id.btnIrAlimentos);
        btnIrControlPeso = findViewById(R.id.btnIrControlPeso);
        btnIrCronometro = findViewById(R.id.btnIrCronometro);
        btnRegresarInicio = findViewById(R.id.btnRegresarInicio);
        progressBar = findViewById(R.id.progressBar);

        //Nos llevara a la vista Calucular IMC
        btnIrCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra el ProgressBar y oculta el botón
                btnIrCalcularIMC.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                // Simula una acción (por ejemplo, un retraso de 2 segundos) y luego cambia a MainActivity
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PanelActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Cierra la actividad actual
                    }
                }, 500); // 2 segundos de retraso
            }
        });
        //Nos llevara a la vista Gimansio
        btnIrGimnasio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra el ProgressBar y oculta el botón
                btnIrGimnasio.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                // Simula una acción (por ejemplo, un retraso de 2 segundos) y luego cambia a MainActivity
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PanelActivity.this, GimnasioActivity.class);
                        startActivity(intent);
                        finish(); // Cierra la actividad actual
                    }
                }, 500); // 2 segundos de retraso
            }
        });
        //Nos llevara a la vista Nutricionista
        btnIrNutricionista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra el ProgressBar y oculta el botón
                btnIrNutricionista.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                // Simula una acción (por ejemplo, un retraso de 2 segundos) y luego cambia a MainActivity
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PanelActivity.this, NutricionistaActivity.class);
                        startActivity(intent);
                        finish(); // Cierra la actividad actual
                    }
                }, 500); // 2 segundos de retraso
            }
        });
        //Nos llevara a la vista Contador de pasos
        btnIrContadorPasos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra el ProgressBar y oculta el botón
                btnIrContadorPasos.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                // Simula una acción (por ejemplo, un retraso de 2 segundos) y luego cambia a MainActivity
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PanelActivity.this, ContadorActivity.class);
                        startActivity(intent);
                        finish(); // Cierra la actividad actual
                    }
                }, 500); // 2 segundos de retraso
            }
        });
        //Nos llevara a la vista Alimentos
        btnIrAlimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra el ProgressBar y oculta el botón
                btnIrAlimentos.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                // Simula una acción (por ejemplo, un retraso de 2 segundos) y luego cambia a MainActivity
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PanelActivity.this, AlimentosActivity.class);
                        startActivity(intent);
                        finish(); // Cierra la actividad actual
                    }
                }, 1000); // 2 segundos de retraso
            }
        });

        //Nos llevara a la vista Control de peso
        btnIrControlPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra el ProgressBar y oculta el botón
                btnIrControlPeso.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                // Simula una acción (por ejemplo, un retraso de 2 segundos) y luego cambia a MainActivity
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PanelActivity.this, ControlPesoActivity.class);
                        startActivity(intent);
                        finish(); // Cierra la actividad actual
                    }
                }, 500); // 2 segundos de retraso
            }
        });


        //Nos llevara a la vista Cronometro
        btnIrCronometro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra el ProgressBar y oculta el botón
                btnIrCronometro.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                // Simula una acción (por ejemplo, un retraso de 2 segundos) y luego cambia a MainActivity
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PanelActivity.this, CronometroActivity.class);
                        startActivity(intent);
                        finish(); // Cierra la actividad actual
                    }
                }, 500); // 2 segundos de retraso
            }
        });

        //Nos llevara a la vista Welcome
        btnRegresarInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra el ProgressBar y oculta el botón
                btnRegresarInicio.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                // Simula una acción (por ejemplo, un retraso de 2 segundos) y luego cambia a MainActivity
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PanelActivity.this, WelcomeActivity.class);
                        startActivity(intent);
                        finish(); // Cierra la actividad actual
                    }
                }, 500); // 2 segundos de retraso
            }
        });


    }
}
