package com.example.sgi.panel.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sgi.R;
import com.example.sgi.utils.Usuario;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

    public class ListAdapterMnt extends RecyclerView.Adapter<ListAdapterMnt.MyViewHolder> implements  View.OnClickListener{

        private ArrayList<Usuario> userList;
        public ListAdapterMnt(ArrayList<Usuario> userList){
            this.userList = userList;
        }

        private View.OnClickListener listener;

        @Override
        public void onClick(View view) {
            if(listener!= null){
                listener.onClick(view);
            }
        }

        public void setOnClickListener(View.OnClickListener listener){
            this.listener = listener;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView titulo;
            MaterialCardView cv;

            public MyViewHolder(final View view){
                super(view);
                titulo = view.findViewById(R.id.ilm_nombre);
                cv = view.findViewById(R.id.ilm_cv);
            }
        }

        @NonNull
        @Override
        public ListAdapterMnt.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_mnt,parent,false);

            itemView.setOnClickListener(this);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ListAdapterMnt.MyViewHolder holder, int position) {
            String name = userList.get(position).getNombre();
            holder.titulo.setText(name);
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }
    }


