package com.example.chrno.contactofragmento;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Chrno on 02/12/2015.
 */
public class FragmentoContacto extends Fragment {
    ContactoBueno aux;
    View vistaFragmento;
    TextView tvNombre,tvTelefonos;
    public FragmentoContacto(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vistaFragmento = inflater.inflate(R.layout.principal_land, container, false);
        tvNombre=(TextView)vistaFragmento.findViewById(R.id.tvNombre);
        tvTelefonos=(TextView)vistaFragmento.findViewById(R.id.tvTelefonos);
        return vistaFragmento;
    }

    public void setDato(ContactoBueno aux){
        this.aux=aux;
        tvNombre.setText(this.aux.getNombre());
        tvTelefonos.setText(this.aux.getStringTelefonos());
    }


}
