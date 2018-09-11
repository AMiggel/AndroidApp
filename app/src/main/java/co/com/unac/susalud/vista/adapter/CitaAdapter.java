package co.com.unac.susalud.vista.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import co.com.unac.susalud.modelo.Cita;
import co.com.unac.susalud.vista.CitasActivity;

public class CitaAdapter extends RecyclerView.Adapter<CitaAdapter.ViewHolder> {

    private static List<Cita> items;
    private Context context;
    private CitasActivity a;

    public CitaAdapter (Context context, List<Cita> items, CitasActivity a){
        this.context = context;
        this.items= items;
        this.a = a;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewTypeCita){

    }
}
