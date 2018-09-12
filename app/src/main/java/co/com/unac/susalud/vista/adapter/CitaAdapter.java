package co.com.unac.susalud.vista.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.com.unac.susalud.R;
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

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.citas_activity, parent,false);
            return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    Cita item= items.get(position);
    holder.type.setText(item.getType());
    holder.medico.setText(item.getMedic());
    holder.pacient.setText(item.getPacient());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder{
         public TextView type;
         public TextView medico;
         public TextView pacient;

    public ViewHolder (final View itemView){
        super(itemView);
        type=(TextView) itemView.findViewById(R.id.tipoCita);
        medico=(TextView) itemView.findViewById(R.id.nombreMedico);
        pacient=(TextView) itemView.findViewById(R.id.horaCita);
    }
    }
}
