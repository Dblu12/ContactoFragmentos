package com.example.chrno.contactofragmento;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chrno on 02/12/2015.
 */
public class FragmentoLista extends Fragment{
    private List<ContactoBueno> l=new ArrayList<>();
    private View fragView;
    private ListView lv;
    private Adaptador ad;
    private onFragmentoInteraccionListener listener;

    public FragmentoLista() {
/*
        List<String> telfs=new ArrayList<>();
        telfs.add("1234");
        telfs.add("5678");
        //BORRAR
        l.add(new ContactoBueno(1,"Fran",telfs));
        l.add(new ContactoBueno(2,"Ruben",telfs));
        l.add(new ContactoBueno(3,"Carmen", telfs));
        //
*/


    }

    public List<ContactoBueno> getListaContactos(){
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String proyeccion[] = null;
        String seleccion = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = ? and " +
                ContactsContract.Contacts.HAS_PHONE_NUMBER + "= ?";
        String argumentos[] = new String[]{"1","1"};
        String orden = ContactsContract.Contacts.DISPLAY_NAME + " collate localized asc";
        Cursor cursor = getActivity().getContentResolver().query(uri, proyeccion, seleccion, argumentos, orden);
        int indiceId = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int indiceNombre = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        List<ContactoBueno> lista = new ArrayList<>();
        ContactoBueno contacto;
        while(cursor.moveToNext()){
            contacto = new ContactoBueno();
            contacto.setId((int) cursor.getLong(indiceId));
            contacto.setNombre(cursor.getString(indiceNombre));
            lista.add(contacto);
        }
        return lista;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragView= inflater.inflate(R.layout.principal_port, container, false);
        inicio();
        return fragView;
    }
    private void inicio(){
        lv= (ListView) fragView.findViewById(R.id.lv);
        registerForContextMenu(lv);
        l= Metodos.getListaContactos(getActivity());
        for(ContactoBueno c: l){
            c.setTelefonos(Metodos.getListaTelefonos(getActivity(),c.getId()));

        }
        ad=new Adaptador(this.getActivity(),l);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Ahora lanzamos los tostada desde la actividad
                // Toast.makeText(FragmentoListView.this.getActivity(), "position " + position, Toast.LENGTH_LONG).show();
                listener.onInteraccion(l.get(position));
            }
        });
        lv.setAdapter(ad);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof onFragmentoInteraccionListener){
            listener= (onFragmentoInteraccionListener) context;
        }else{
            throw new ClassCastException("Solo acepto OnFragmentoInteraccionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof onFragmentoInteraccionListener){
            listener= (onFragmentoInteraccionListener) activity;
        }else{
            throw new ClassCastException("Solo acepto OnFragmentoInteraccionListener");
        }
    }

    public void anadir(View view){
        Intent in= new Intent(getActivity(), AnadirContacto.class);
        startActivityForResult(in, 1);
    }

    public List<ContactoBueno> getC(){
        return l;
    }

    public void setC(List<ContactoBueno> l){
        this.l= l;
        // Actualizar adaptador
        ad=new Adaptador(this.getActivity(),l);
        lv.setAdapter(ad);
    }

    public void anadirContacto(ContactoBueno aux){
        l.add(aux);
        ad=new Adaptador(this.getActivity(),l);
        lv.setAdapter(ad);
        //ad.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId()==R.id.lv) {
            MenuInflater inflater = getActivity().getMenuInflater();

            inflater.inflate(R.menu.menucontextual, menu);
        }
    }
    public boolean onContextItemSelected(MenuItem item) {

        long id = item.getItemId();
        AdapterView.AdapterContextMenuInfo vistaInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int posicion = vistaInfo.position;

        if (id == R.id.mnborrar) {


            l.remove(posicion);
            ad=new Adaptador(this.getActivity(),l);
            lv.setAdapter(ad);
            return true;
        }
        return super.onContextItemSelected(item);
    }

}

