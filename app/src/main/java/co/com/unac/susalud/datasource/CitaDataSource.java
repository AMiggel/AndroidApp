package co.com.unac.susalud.datasource;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.UnsupportedEncodingException;
import java.util.List;

import co.com.unac.susalud.modelo.Cita;
import co.com.unac.susalud.modelo.CitaS;

public class CitaDataSource implements CitaRemoteDataSource {

    private final static String URL_CITA = "http://api-movil.herokuapp.com/api/cite";
    private Context context;
    private RequestQueue requestQueue;
    private static CitaRemoteDataSource INSTANCE = null;
    public String body;

    private CitaDataSource(Context context){
        this.context= context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public static CitaRemoteDataSource getInstance(Context context){
        if (INSTANCE== null){
            INSTANCE = new CitaDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void consultarCita(final GetCitaCallback callback) {
        try {
            JsonArrayRequest request = new JsonArrayRequest(
                    Request.Method.GET,
                    URL_CITA, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        Gson gson = new Gson();
                        List<Cita> citas = gson.fromJson(response.toString(), new TypeToken<List<Cita>>() {

                        }.getType());
                        callback.onCitaLoaded(citas);
                    } catch (Exception e) {
                        callback.onError();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onError();
                }
            });
            requestQueue.add(request);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void guardarCita(final PostCitaCallback callback,final CitaS cita) {
        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, "http://demo4122942.mockable.io",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            callback.guardarCita();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            callback.onError();
                        }
                    }
            ){
                public String getBodyContentType(){
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    Gson gson = new GsonBuilder().create();
                    String json= gson.toJson(cita);
                    byte[] body= new byte[0];
                    try{
                        body = json.getBytes("utf-8");
                    }catch (UnsupportedEncodingException e){
                        body = new byte[0];
                    }
                    return body;
                }
                protected Response<String> parseNetworkResponse(NetworkResponse response){
                    String responseString="";
                    if (response != null){
                        responseString = String.valueOf(response.statusCode);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }

            };
            requestQueue.add(postRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
