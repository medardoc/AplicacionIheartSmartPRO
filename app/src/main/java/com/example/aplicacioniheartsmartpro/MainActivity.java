package com.example.aplicacioniheartsmartpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPeso;
    private EditText editTextAltura;
    private TextView resultado;
    private Button btnCalcularIMC;
    private Button btnLimpiarCampos;
    private Button btnRegresarInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa los componentes de la interfaz
        editTextPeso = findViewById(R.id.editTextText2);
        editTextAltura = findViewById(R.id.editTextNumber2);
        resultado = findViewById(R.id.resultado);
        btnCalcularIMC = findViewById(R.id.btnCalcularIMC);
        btnLimpiarCampos = findViewById(R.id.btnLimpiarCampos);
        btnRegresarInicio = findViewById(R.id.btnRegresarInicio);

        // Configura el listener para el botón de calcular IMC
        btnCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIMC();
            }
        });

        // Configura el listener para el botón de limpiar campos
        btnLimpiarCampos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });

        // Configura el listener para el botón de regresar al inicio
        btnRegresarInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresarInicio();
            }
        });
    }

    private void calcularIMC() {
        // Obtiene el valor del peso y la altura desde los EditText
        String pesoStr = editTextPeso.getText().toString();
        String alturaStr = editTextAltura.getText().toString();

        // Verifica que los campos no estén vacíos
        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            resultado.setText("Por favor, ingresa ambos valores.");
            return;
        }

        try {
            // Convierte los valores a números decimales
            double peso = Double.parseDouble(pesoStr);
            double altura = Double.parseDouble(alturaStr);

            // Calcula el IMC
            double imc = peso / (altura * altura);

            // Determina la categoría del IMC
            String categoria = clasificarIMC(imc);

            // Muestra el resultado en el TextView
            resultado.setText(String.format("Tu IMC es %.2f\n%s", imc, categoria));
        } catch (NumberFormatException e) {
            // Muestra un mensaje de error si hay un problema con la conversión
            resultado.setText("Error en los valores ingresados.");
        }
    }

    // Método para clasificar el IMC en categorías
    private String clasificarIMC(double imc) {
        if (imc < 16) {
            return "Desnutrición severa";
        } else if (imc >= 16 && imc < 16.9) {
            return "Desnutrición moderada";
        } else if (imc >= 17 && imc < 18.4) {
            return "Desnutrición leve";
        } else if (imc >= 18.5 && imc < 24.9) {
            return "Peso normal";
        } else if (imc >= 25 && imc < 29.9) {
            return "Sobrepeso";
        } else if (imc >= 30 && imc < 34.9) {
            return "Obesidad grado 1";
        } else if (imc >= 35 && imc < 39.9) {
            return "Obesidad grado 2";
        } else {
            return "Obesidad grado 3";
        }
    }

    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        editTextPeso.setText("");
        editTextAltura.setText("");
        resultado.setText("AQUÍ APARECERÁ EL RESULTADO");
    }

    // Método para regresar a la pantalla de inicio
    private void regresarInicio() {
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish(); // Opcional: Cierra la actividad actual para que el usuario no pueda regresar a ella con el botón de retroceso
    }
}

