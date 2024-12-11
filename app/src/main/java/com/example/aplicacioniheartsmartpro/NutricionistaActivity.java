package com.example.aplicacioniheartsmartpro;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.preference.PreferenceManager; // Importa PreferenceManager para gestionar las preferencias
import android.widget.AdapterView; // Importa AdapterView para manejar eventos en vistas adaptables.
import android.widget.ArrayAdapter; // Importa ArrayAdapter para adaptar arrays a vistas como Spinners.
import android.widget.Spinner; // Importa Spinner, que es una lista desplegable en Android.
import org.osmdroid.config.Configuration; // Importa la clase Configuration para configurar el mapa.
import org.osmdroid.tileprovider.tilesource.TileSourceFactory; // Importa TileSourceFactory para definir los tipos de mapas disponibles
import org.osmdroid.tileprovider.tilesource.XYTileSource; // Importa XYTileSource para crear un proveedor de azulejos específico para mapas personalizados.
import org.osmdroid.util.GeoPoint; // Importa GeoPoint, que representa coordenadas geográficas (latitud y longitud).
import org.osmdroid.views.MapView; // Importa MapView, que es el componente visual del mapa.
import org.osmdroid.views.overlay.Marker; // Importa Marker para agregar marcadores en el mapa.
import org.osmdroid.views.overlay.Polyline; // Importa Polyline para dibujar líneas en el mapa.
import androidx.appcompat.app.AppCompatActivity;


public class NutricionistaActivity extends AppCompatActivity {
    private Button btnRegresarInicio;

    @Override

    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.nutricionista_activity);
        //Carga la configuración del mapa usando las preferencias predeterminadas.
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        //Obtiene la referencia al componente MapView del layout
        MapView mapView = findViewById(R.id.mapView);

        //Establece la fuente de azules del mapa a MAPNIK
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        //Activa los controles de zoom
        mapView.setBuiltInZoomControls(true);

        //Activa el control multitactil
        mapView.setMultiTouchControls(true);

        //Coordenadas del Smart Fit
        double ipRedSaludLatitud = -33.397912;
        double ipRedSaludLongitud = -70.670779;

        //Crear objetos Geopoint para la coordenadas definidas.
        GeoPoint IPRedSaludPoint = new GeoPoint(ipRedSaludLatitud, ipRedSaludLongitud);
        //Configura la vista centrada en el IP
        mapView.getController().setZoom(15.0);
        //Centra el mapa en el punto
        mapView.getController().setCenter(IPRedSaludPoint);

        //Crear marcador para ip Smart fit
        Marker marcadorRedSalud = new Marker(mapView);
        //Establecer posición
        marcadorRedSalud.setPosition(IPRedSaludPoint);
        //Establece ancla del marcador
        marcadorRedSalud.setTitle("Red Salud, Chile");
        //Establece una descripción
        marcadorRedSalud.setSnippet("La mejor red de salud.");

        //Agregar los marcadores del mapa
        mapView.getOverlays().add(marcadorRedSalud);

        //Coordenadas del parque
        double parqueLatitud = -33.4625076;
        double parqueLongitud = -70.6600286;

        //Crear un objeto point
        GeoPoint parquePoint = new GeoPoint(parqueLatitud, parqueLongitud);
        //Crear un marcador para el parque
        Marker marcadorParque = new Marker(mapView);
        //Establece el ancla del marcador y los valores  se pueden ajustar la con el marcador
        marcadorParque.setAnchor(0.2f,0.4f);
        //Ajusta la posición del titulo y la descripción
        marcadorParque.setInfoWindowAnchor(0.2f,0.0f);
        //Establece el titulo
        marcadorParque.setTitle("Parque O´Higgins");
        //Establece descripción
        marcadorParque.setSnippet("Aquí asaltan");
        //Establecemos un incono al marcador
        marcadorParque.setIcon(getResources().getDrawable(R.drawable.puntomapa));

        //Agregar los marcadores del mapa
        mapView.getOverlays().add(marcadorParque);

        //Crear linea que una a los marcadores
        Polyline linea = new Polyline();
        //Añade el punto del IP y el parque
        linea.addPoint(IPRedSaludPoint);
        linea.addPoint(parquePoint);
        //Establece el color de la linea en azul ARGB
        linea.setColor(0xFF0000FF);
        //Establece el ancho
        linea.setWidth(5);
        //añadir la linea al mapa
        mapView.getOverlayManager().add(linea);

        //Configuración del spinner
        Spinner mapTypeSpinner = findViewById(R.id.mapTypeSpinner);
        //Define un array con tipos de mapas
        String[] mapType = {"Mapa normal", "Mapa de transporte", "Mapa Topografico"};

        //Creamos un arrayAdpter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mapType);
        //Establece el layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        //Asigna el adaptador al spinner
        mapTypeSpinner.setAdapter(adapter);

        //Listener para detectar cambios en la selección
        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mapView.setTileSource(TileSourceFactory.MAPNIK);
                        break;
                    case 1:
                        mapView.setTileSource(new XYTileSource(
                                "PublicTransport",
                                0, 18, 256, ".png", new String[]{
                                "https:tile.memomaps.de/tilegen/"}));
                        break;
                    case 2:
                        mapView.setTileSource(new XYTileSource(
                                "USGS_Satellite", 0, 18, 256, ".png", new String[]{
                                "https:/a.tile.opentopomap.org/",
                                "https:/b.tile.opentopomap.org/",
                                "https:/c.tile.opentopomap.org/"}));
                        break;
                }
            }

            //No se hace nada mas
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        btnRegresarInicio = findViewById(R.id.btnRegresarInicio);

        btnRegresarInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresarInicio();
            }
        });
    }

    // Método para regresar a al menu
    private void regresarInicio() {
        Intent intent = new Intent(NutricionistaActivity.this, PanelActivity.class);
        startActivity(intent);
        finish(); // Opcional: Cierra la actividad actual para que el usuario no pueda regresar a ella con el botón de retroceso
    }
}
