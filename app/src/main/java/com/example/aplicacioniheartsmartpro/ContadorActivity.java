package com.example.aplicacioniheartsmartpro;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast; // Agrega esta línea para los mensajes de confirmación

public class ContadorActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private TextView stepCountTextView;
    private Button btnRegresarInicio, btnReiniciarPasos;
    private int steps = 0;
    private int initialSteps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contador_activity);

        stepCountTextView = findViewById(R.id.stepCountTextView);
        btnRegresarInicio = findViewById(R.id.btnRegresarInicio);
        btnReiniciarPasos = findViewById(R.id.btnReiniciarPasos); // Vincula el botón al layout

        // Configuración del Sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }

        if (stepCounterSensor == null) {
            stepCountTextView.setText("Sensor de pasos no disponible en este dispositivo.");
        }

        btnRegresarInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresarInicio();
            }
        });

        btnReiniciarPasos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarPasos();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stepCounterSensor != null) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (stepCounterSensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            if (initialSteps == 0) {
                initialSteps = (int) event.values[0];
            }
            steps = (int) event.values[0] - initialSteps;
            stepCountTextView.setText("Pasos: " + steps);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No es necesario implementar en este caso.
    }

    private void reiniciarPasos() {
        initialSteps = 0;
        steps = 0;
        stepCountTextView.setText("Pasos: " + steps);
        Toast.makeText(this, "Contador reiniciado", Toast.LENGTH_SHORT).show(); // Mensaje para el usuario
    }

    private void regresarInicio() {
        Intent intent = new Intent(ContadorActivity.this, PanelActivity.class);
        startActivity(intent);
        finish();
    }
}
