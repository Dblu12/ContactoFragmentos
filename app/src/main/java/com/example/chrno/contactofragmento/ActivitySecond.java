package com.example.chrno.contactofragmento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivitySecond extends AppCompatActivity {
    private ContactoBueno aux;
    private TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv1= (TextView)findViewById(R.id.textView);
        tv2= (TextView)findViewById(R.id.textView2);

//        Log.v("MOSTRAR"," LIST VIEW");
        Bundle b=getIntent().getExtras();
        aux=(ContactoBueno) b.getSerializable("aux");
        tv1.setText(aux.getNombre());
        tv2.setText("");
        for(String s: aux.getTelefonos()){
            tv2.append(s+ "\n");
        }
//        FragmentoLista fragmento = (FragmentoLista) getFragmentManager().findFragmentById(R.id.fragmentoLista);
//        fragmento.setDato(aux);
    }
}
