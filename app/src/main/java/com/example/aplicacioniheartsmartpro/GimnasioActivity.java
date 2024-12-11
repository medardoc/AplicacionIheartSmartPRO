package com.example.aplicacioniheartsmartpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.preference.PreferenceManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

// Importaciones para VideoView y WebView
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GimnasioActivity extends AppCompatActivity {

    private Button btnRegresarInicio;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gimnasio_activity);

        // Cargar configuraciones de mapa y video
        configurarMapa();
        configurarVideoWebView();

        // Configurar botón de regreso
        btnRegresarInicio = findViewById(R.id.btnRegresarInicio);
        btnRegresarInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresarInicio();
            }
        });
    }

    private void configurarMapa() {
        // Configuración del mapa
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        // Configurar marcadores
        GeoPoint IPSmartFitPoint = new GeoPoint(-33.4493141, -70.552699);
        mapView.getController().setZoom(15.0);
        mapView.getController().setCenter(IPSmartFitPoint);

        agregarMarcador(IPSmartFitPoint, "Smart Fit, Chile", "El mejor Gimnasio para tu salud.");
        GeoPoint parquePoint = new GeoPoint(-33.4625076, -70.6600286);
        agregarMarcadorConIcono(parquePoint, "Parque O´Higgins", "Aquí asaltan", R.drawable.puntomapa);

        // Crear línea entre los puntos
        Polyline linea = new Polyline();
        linea.addPoint(IPSmartFitPoint);
        linea.addPoint(parquePoint);
        linea.setColor(0xFF0000FF);
        linea.setWidth(5);
        mapView.getOverlayManager().add(linea);

        // Configuración de Spinner
        Spinner mapTypeSpinner = findViewById(R.id.mapTypeSpinner);
        String[] mapType = {"Mapa normal", "Mapa de transporte", "Mapa Topografico"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mapType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mapTypeSpinner.setAdapter(adapter);
        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambiarFuenteMapa(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se hace nada
            }
        });
    }

    private void agregarMarcador(GeoPoint point, String titulo, String descripcion) {
        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setTitle(titulo);
        marker.setSnippet(descripcion);
        mapView.getOverlays().add(marker);
    }

    private void agregarMarcadorConIcono(GeoPoint point, String titulo, String descripcion, int iconoResId) {
        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setTitle(titulo);
        marker.setSnippet(descripcion);
        marker.setIcon(getResources().getDrawable(iconoResId));
        mapView.getOverlays().add(marker);
    }

    private void cambiarFuenteMapa(int position) {
        switch (position) {
            case 0:
                mapView.setTileSource(TileSourceFactory.MAPNIK);
                break;
            case 1:
                mapView.setTileSource(new XYTileSource("PublicTransport", 0, 18, 256, ".png", new String[]{"https://tile.memomaps.de/tilegen/"}));
                break;
            case 2:
                mapView.setTileSource(new XYTileSource("USGS_Satellite", 0, 18, 256, ".png", new String[]{
                        "https://a.tile.opentopomap.org/",
                        "https://b.tile.opentopomap.org/",
                        "https://c.tile.opentopomap.org/"
                }));
                break;
        }
    }

    private void configurarVideoWebView() {
        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        String videoUrl = "https://www.youtube.com/embed/wNNu0pkTjPQ";
        webView.loadUrl(videoUrl);
    }


    private void regresarInicio() {
        Intent intent = new Intent(GimnasioActivity.this, PanelActivity.class);
        startActivity(intent);
        finish();
    }
}



