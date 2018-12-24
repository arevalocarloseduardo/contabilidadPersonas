package com.jhon.caear.contabilidadpersonas

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.datos.view.*

class AdapterDatos(var list: MutableList<Reunion>): RecyclerView.Adapter<AdapterDatos.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.datos,parent,false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: AdapterDatos.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        lateinit var grandes: TextView
        lateinit var bebes: TextView
        lateinit var ninos: TextView

        fun bindItems(data: Reunion){

            val grandes=itemView.txtGrandes.text
            val ninos=itemView.txtBebes.text
            val bebes=itemView.txtNinos.text

            data.bebes



        }
    }
}