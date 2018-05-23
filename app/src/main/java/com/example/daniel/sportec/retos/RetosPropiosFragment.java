package com.example.daniel.sportec.retos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.daniel.sportec.R;
import com.example.daniel.sportec.equipos.EquipoVacioFragment;

public class RetosPropiosFragment extends Fragment {
    public static RetosPropiosFragment newInstance() {
        RetosPropiosFragment fragmentoRetosPropios = new RetosPropiosFragment();
        return fragmentoRetosPropios;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.retos_propios_fragment, container, false);




        return view;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
