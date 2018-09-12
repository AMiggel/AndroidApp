package co.com.unac.susalud.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import co.com.unac.susalud.R;
import co.com.unac.susalud.datasource.CitaDataSource;
import co.com.unac.susalud.dominio.CitaDominio;
import co.com.unac.susalud.modelo.Cita;
import co.com.unac.susalud.vista.adapter.CitaAdapter;

public class CitasActivity extends AppCompatActivity implements CitaDominio.View {

    private CitaAdapter citaAdapter;
    private CitaDominio citaDominio;
    private RecyclerView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (RecyclerView) findViewById(R.id.lista);
        citaDominio = new CitaDominio(this, CitaDataSource.getInstance(this));
        consultarCita();
    }

    private void consultarCita(){
        citaDominio.consultarCitas();
    }

    @Override
    public void showCitas(List<Cita> citas){
        citaAdapter= new CitaAdapter(getApplicationContext(),citas,this);
        lista.setAdapter(citaAdapter);
    }

    @Override
    public void showMensaje(int mensaje){
        Log.d("Error", getString(mensaje));
    }
}