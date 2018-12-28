package com.jhon.caear.contabilidadpersonas

import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_inicio.*

class MainActivity : BaseActivity(),UserSelection {

    lateinit var myBtnGuardar:Button
    lateinit var myEtGrandes:EditText
    lateinit var myEtninos:EditText
    lateinit var myEtbebes:EditText
    lateinit var mybtnMostrar:Button
    lateinit var listReuniones: MutableList<Reunion>
    lateinit var myRvlist:RecyclerView

    override fun onUserSelected(user: Reunion) {
        Toast.makeText(this, user.grandes, Toast.LENGTH_SHORT).show()
    }
    override fun getLayout(): Int = R.layout.activity_inicio
    override fun activityOnCreate(savedInstanceState: Bundle?) {
        mybtnMostrar=btn_mostrar
        listReuniones= mutableListOf()
        myRvlist=rvList
        myEtbebes=et_bebes
        myEtninos=et_ninos
        myEtGrandes=et_grandes
        myBtnGuardar=btn_enviar

        setRecycler(myRvlist,listReuniones)

        mybtnMostrar.setOnClickListener {
            setRecycler(myRvlist,listReuniones)
        }
        myBtnGuardar.setOnClickListener {
            var G =myEtGrandes.text.toString()
            var N =myEtninos.text.toString()
            var B =myEtbebes.text.toString()

val context = this
            if (verificarDatos(G,N,B)){
               val admin =BaseDatos(this,"administrador",null,1)
                val bd = admin.writableDatabase
                val registro= ContentValues()
                registro.put("codigo", G)
                registro.put("descripcion",N)
                registro.put("precio",B)
                bd.insert("articulos",null,registro)
                bd.close()
                crearNuevaReunion(G,N,B)
                myEtGrandes.setText("")
                myEtninos.setText("")
                myEtbebes.setText("")

            }
        }
    }

    private fun setRecycler(myRecycler: RecyclerView?, lista: MutableList<Reunion>) {
        //recibo por parametro la lista de objetos y el contenedor
        myRecycler?.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        val userAdapter = AdapterDatos(lista, this)
        myRecycler?.adapter = userAdapter
    }

    private fun mostrarActivity() {



        val intent2 = Intent(this, MostrarDatos::class.java)
        // intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent2)

    }

    private fun verificarDatos(g: String, n: String, b: String): Boolean {
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

    private fun crearNuevaReunion(g: String, n: String, b: String) {
        val reu = Reunion(g.toInt(),n.toInt(),b.toInt())
        listReuniones.add(reu)
        agregarBaseDatos(reu)
    }

    private fun agregarBaseDatos(reu: Reunion) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("reuniones")

        myRef.setValue(reu)
    }

}

