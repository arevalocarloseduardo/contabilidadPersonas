package com.jhon.caear.contabilidadpersonas

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
//Activity principal
abstract class BaseActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)


        setContentView(getLayout())//recibo el layout que mostrara en pantalla
        activityOnCreate(savedInstanceState)
    }

    abstract fun activityOnCreate(savedInstanceState: Bundle?)
    abstract fun getLayout(): Int
}