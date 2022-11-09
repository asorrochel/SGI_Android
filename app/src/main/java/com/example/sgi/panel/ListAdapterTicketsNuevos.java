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

public class ListAdapterTicketsNuevos extends RecyclerView.Adapter<ListAdapterTicketsNuevos.ViewHolder> {
    private List<Ticket> mData;
    private LayoutInflater mInflater;
    private Context context;
    final ListAdapterTicketsNuevos.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Ticket item);
    }

    public ListAdapterTicketsNuevos(List<Ticket> itemList,Context context, ListAdapterTicketsNuevos.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListAdapterTicketsNuevos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.from(parent.getContext()).inflate(R.layout.item_list_tickets,parent,false);
        return new ListAdapterTicketsNuevos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterTicketsNuevos.ViewHolder holder, final int position) {
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount(){return mData.size();}

    public void setItems(List<Ticket> items) {mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo, aula, equipo;
        MaterialCardView cv;
        ViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.ilt_titulo);
            equipo = itemView.findViewById(R.id.ilt_equipo);
            aula = itemView.findViewById(R.id.ilt_clase);
            cv = itemView.findViewById(R.id.ilt_cv);
        }

        void bindData(final Ticket item) {
            titulo.setText((item.getTitulo()));
            equipo.setText(item.getEquipo());
            aula.setText(item.getAula());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
