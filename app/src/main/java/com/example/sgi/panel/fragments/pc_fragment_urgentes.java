package com.example.sgi.panel.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.sgi.R;

public class pc_fragment_urgentes extends Fragment {

    // Declaración de Variables.
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflamos el layout para este fragment.
        view = inflater.inflate(R.layout.fragment_pc_urgentes, container, false);
        return view;
    }
}