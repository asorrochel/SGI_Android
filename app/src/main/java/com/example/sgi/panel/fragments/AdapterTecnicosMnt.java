package com.example.sgi.panel.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sgi.R;
import com.example.sgi.utils.Usuario;

import java.util.List;

    public class AdapterTecnicosMnt extends RecyclerView.Adapter<AdapterTecnicosMnt.MyViewHolder>{

        //private View.OnClickListener listener;
        private List<Usuario> userList;
        private Context context;

        public AdapterTecnicosMnt(List<Usuario> userList, Context context){
            this.userList = userList;
            this.context = context;
        }
/*
        @Override
        public void onClick(View view) {
            if(listener!= null){
                listener.onClick(view);
            }
        }
*/
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_mnt,parent,false);
            //itemView.setOnClickListener(this);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.nombre.setText(userList.get(position).getNombre());
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }
/*
        public void setOnClickListener(View.OnClickListener listener){
            this.listener = listener;
        }
*/
        public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView nombre;
            //MaterialCardView cv;

            public MyViewHolder(View view){
                super(view);
                nombre = view.findViewById(R.id.ilm_nombre);
                //cv = view.findViewById(R.id.ilm_cv);
            }
        }
    }


