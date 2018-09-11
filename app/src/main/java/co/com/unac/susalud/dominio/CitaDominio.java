package co.com.unac.susalud.dominio;

import android.view.View;

import java.util.List;

import co.com.unac.susalud.R;
import co.com.unac.susalud.datasource.CitaRemoteDataSource;
import co.com.unac.susalud.modelo.Cita;
import co.com.unac.susalud.modelo.CitaS;

public class CitaDominio {

    private View view;
    private CitaRemoteDataSource citaRemoteDataSource;

    public CitaDominio(View view, CitaRemoteDataSource citaRemoteDataSource) {
        this.view = view;
        this.citaRemoteDataSource = citaRemoteDataSource;
    }

    public void consultarCitas() {
        citaRemoteDataSource.consultarCita(new CitaRemoteDataSource.GetCitaCallback() {
            @Override
            public void onCitaLoaded(List<Cita> cita) {
                view.showCitas(cita);
            }

            @Override
            public void onError() {
                view.showMensaje(R.string.error_generico);
            }
        });
    }
    public void guardarCitas(CitaS cita) {
        citaRemoteDataSource.guardarCita(new CitaRemoteDataSource.PostCitaCallback() {
            @Override
            public void guardarCita() {
                view.showMensaje(R.string.mensaje_generico_guardar);
            }

            @Override
            public void onError() {
                view.showMensaje(R.string.error_generico);
            }
        }, cita);
    }



    public interface View {
        void showCitas(List<Cita> citas);

        void showMensaje(int mensaje);
    }
}
