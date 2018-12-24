package com.jhon.caear.contabilidadpersonas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_inicio.*

class MainActivity : AppCompatActivity() {

    lateinit var myBtnGuardar:Button
    lateinit var myEtGrandes:EditText
    lateinit var myEtninos:EditText
    lateinit var myEtbebes:EditText
    lateinit var mybtnMostrar:Button
    lateinit var listReuniones: MutableList<Reunion>
    lateinit var myRvlist:RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        mybtnMostrar=btn_mostrar
        myBtnGuardar=btn_enviar
        myEtGrandes=et_grandes
        myEtninos=et_ninos
        myEtbebes=et_bebes
        listReuniones= mutableListOf()
        myRvlist=rvList



        myRvlist.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        val mi2Adapter= AdapterDatos(listReuniones)
        myRvlist.adapter = mi2Adapter





        mybtnMostrar.setOnClickListener {

            myRvlist.adapter = mi2Adapter
        }
        myBtnGuardar.setOnClickListener {

            var B =myEtbebes.text.toString()
            var G =myEtGrandes.text.toString()
            var N =myEtninos.text.toString()
            if (verificarDatos(B,G,N)){
                crearNuevaReunion(B,G,N)
                myEtGrandes.setText("")
                myEtbebes.setText("")
                myEtninos.setText("")
            }
        }
    }

    private fun mostrarActivity() {



        val intent2 = Intent(this, MostrarDatos::class.java)
        // intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent2)

}

    private fun verificarDatos(b: String, g: String, n: String): Boolean {
        if (b==""||g==""||n==""){
            Toast.makeText(this,"los campos no tienen que estar vacios", Toast.LENGTH_LONG).show()
            return false

        }
        if(b.length>5||g.length>5||n.length>5){
            Toast.makeText(this,"solo podes poner max 5 caracteres", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun crearNuevaReunion(b: String, g: String, n: String) {
        val reu = Reunion(b,g,n)
        listReuniones.add(reu)
        agregarBaseDatos(reu)
    }

    private fun agregarBaseDatos(reu: Reunion) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("reuniones")

        myRef.setValue(reu)
    }

}
