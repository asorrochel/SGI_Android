package com.example.sgi.crearTicket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sgi.R;
import com.example.sgi.utils.Aula;
import com.example.sgi.utils.Ticket;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class AdapterAulas  {

    private LayoutInflater mInflater;

    private List<Aula> aulaList;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Ticket item);
    }

    public AdapterAulas(List<Aula> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.aulaList = itemList;
    }
}
