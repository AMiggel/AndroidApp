package co.com.unac.susalud.datasource;

import java.util.List;

import co.com.unac.susalud.modelo.Cita;
import co.com.unac.susalud.modelo.CitaS;

public interface CitaRemoteDataSource {

    void consultarCita (GetCitaCallback callback);
    void guardarCita (PostCitaCallback callback, CitaS cita);

    public interface GetCitaCallback{
        void onCitaLoaded (List<Cita> cita);
        void onError();
    }
    public interface PostCitaCallback{
        void guardarCita();
        void onError();
    }
}
