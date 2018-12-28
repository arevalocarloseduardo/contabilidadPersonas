package com.jhon.caear.contabilidadpersonas

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.datos.view.*

//Esta clase se encarga de crear las Views necesarias para cada elemento de la lista

class AdapterDatos(var list: MutableList<Reunion>,val escuchador:UserSelection): RecyclerView.Adapter<AdapterDatos.ListaViewHolder>(){

// inicializo la posision de la lista para cada elemento
    override fun onBindViewHolder(holder: AdapterDatos.ListaViewHolder, position: Int) {
        holder.bindItems(list[position])
    }
    //indico el tamaño de la lista
    override fun getItemCount(): Int = list.size

// declaro el layout que va a inflar repetidamente
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.datos,parent,false)
        return ListaViewHolder(v,escuchador)
    }
    //asigno a cada texto el dato recibido
    class ListaViewHolder(itemView: View,val escuchador: UserSelection): RecyclerView.ViewHolder(itemView) {
        fun bindItems(data: Reunion){
           itemView.txtGrandes.text="Grandes:${data.grandes}"
           itemView.txtBebes.text="Bebes:${data.bebes}"
           itemView.txtNinos.text="Niños:${data.ninos}"
            itemView.setOnClickListener { escuchador.onUserSelected(data) }
        }
    }
}