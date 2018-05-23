package com.example.daniel.sportec.retos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daniel.sportec.R;

public class RetosEquiposFragment extends Fragment{
    public static RetosEquiposFragment newInstance() {
        RetosEquiposFragment fragmentoRetosEquipos = new RetosEquiposFragment();
        return fragmentoRetosEquipos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.retos_equipos_fragment, container, false);




        return view;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
