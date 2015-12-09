package com.example.chrno.contactofragmento;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 2dam on 30/09/2015.
 */
public class Adaptador extends ArrayAdapter<ContactoBueno> {

    private Context contexto;  // la actividad que va a contener el listview
    private int recurso;      // el layout que se usa para rellenar cada uno de los item del liestview
    private List<ContactoBueno> lista; // lista de valores que va a mosrtrar
    private TextView tv,tv2; // textview donde van los datos (nombre y telefono)
    private ImageView iv; // Imagen del contacto

    private LayoutInflater i;

    public Adaptador(Context contexto, List<ContactoBueno> lista) {
        super(contexto, R.layout.item,lista);

        this.contexto=contexto;
        this.recurso=R.layout.item;
        this.lista=lista;
        Log.v("CONSTRUCTOR","ADAPTADOR");
        //Genera el espacio y lo llena con el layout recurso
        i = (LayoutInflater) contexto.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        //colocamos i en el constructor para ahorrar recursos y lo declaramos de instancia
    }
    static class ViewHolder{
        private TextView tv,tv2;
        private ImageView iv;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.v("ADAPTADOR", "Lamada posicion: " + position);

        //Lo llevamos al constructor para ahorrar recursos
        // LayoutInflater i = (LayoutInflater) contexto.getSystemService
        //        (Context.LAYOUT_INFLATER_SERVICE);

        //Estando mal optimizado
        // convertView = i.inflate(recurso, null);
        ViewHolder vh;
        if (convertView==null){
            vh=new ViewHolder();//Se crea una clase para almacenar cosas
            convertView = i.inflate(recurso, null);
            Log.v("ADAPTADOR", "Inflando");
            tv = (TextView) convertView.findViewById(R.id.tvNombre);
            tv2 = (TextView) convertView.findViewById(R.id.tvTelefonos);
            iv= (ImageView) convertView.findViewById(R.id.imageView3);

            vh.tv=tv;
            vh.tv2=tv2;
            vh.iv= iv;
            convertView.setTag(vh);
        }else
            vh= (ViewHolder) convertView.getTag();

        //accedemos a los elementos de layout

        ContactoBueno aux=lista.get(position);
        tv.setText(aux.getNombre());
        tv2.setText(aux.getStringTelefonos());
        //iv.setImageURI();
        //devolver lo generado
        return convertView;

    }



}
