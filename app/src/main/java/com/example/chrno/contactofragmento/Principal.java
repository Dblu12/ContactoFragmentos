package com.example.chrno.contactofragmento;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Principal extends AppCompatActivity implements onFragmentoInteraccionListener {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
    }

    @Override
    public void onInteraccion(ContactoBueno aux) {
//        tostada(aux);
        FragmentoContacto fragmento = (FragmentoContacto) getFragmentManager().
                findFragmentById(R.id.fragmentoContacto);
        if (fragmento == null || !fragmento.isInLayout()) {
            //Vertical
            //tostada("VER");
            Intent i = new Intent(this,ActivitySecond.class);
            Bundle b=new Bundle();
            b.putSerializable("aux", aux);
            i.putExtras(b);
            startActivity(i);
        }else{
            //Horizontal
            //tostada("HOR");
//            FragmentoContacto fc=(FragmentoContacto) getFragmentManager().
//                    findFragmentById(R.id.fragmentoContacto);
            fragmento.setDato(aux);
        }
    }
    public void tostada(ContactoBueno s){
        Toast.makeText(this,s.toString(),Toast.LENGTH_SHORT).show();
    }
    public void tostada(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    public void anadir(View view){
        Intent in= new Intent(this, AnadirContacto.class);
        startActivityForResult(in, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                /*
                ContactoBueno  contacto = (ContactoBueno) data.getExtras().getSerializable("contacto");
                System.out.println(contacto);
                for(ContactoBueno c : l){
                    if(c.getId()==contacto.getId()) {
                        c.setTelefonos(contacto.getTelefonos());
                        c.setNombre(contacto.getNombre());
                        a.notifyDataSetChanged();
                        //a = new ClaseAdaptadorContactos(this, R.layout.activity_principal, (ArrayList<Contacto>) l);
                        //lv.setAdapter(a);
                    }
                    System.out.println(c);
                    */
                }

            }else if(resultCode == 2){

                ContactoBueno  contacto = (ContactoBueno) data.getExtras().getSerializable("contacto");
            FragmentoLista fragmento = (FragmentoLista) getFragmentManager().
                    findFragmentById(R.id.fragmentoLista);
                fragmento.anadirContacto(contacto);


            }
        }
    }


