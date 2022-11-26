package com.example.sgi.panel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sgi.R;
import com.example.sgi.utils.Ticket;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class AdapterTickets extends RecyclerView.Adapter<AdapterTickets.MyViewHolder> {

    private LayoutInflater mInflater;
    final AdapterTickets.OnItemClickListener listener;
    TextView titulo, aula, equipo;

    private List<Ticket> ticketNuevoList;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Ticket item);
    }

    public AdapterTickets(List<Ticket> itemList, Context context, AdapterTickets.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.ticketNuevoList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.from(parent.getContext())
                .inflate(R.layout.item_list_tickets,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
        holder.bindData(ticketNuevoList.get(position));
    }

    @Override
    public int getItemCount(){return ticketNuevoList.size();}

    public void setItems(List<Ticket> items) {
        ticketNuevoList = items;}

    public class MyViewHolder extends RecyclerView.ViewHolder{
        MaterialCardView cv;
        MyViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.ilt_titulo);
            equipo = itemView.findViewById(R.id.ilt_equipo);
            aula = itemView.findViewById(R.id.ilt_clase);
            cv = itemView.findViewById(R.id.ilt_cv);
        }

        void bindData(final Ticket item) {
            titulo.setText((item.getTitulo()));
            equipo.setText(item.getEquipo());
            aula.setText(item.getAula().getAula());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
