package com.jhon.caear.contabilidadpersonas

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.util.prefs.PreferencesFactory

val DATABASE_NAME="MyDB"
val TABLE_NAME="Users"
val COL_ID="id"
val COL_BEBES="bebes"
val COL_GRANDES="grandes"
val COL_NINOS="ninos"

class BaseDatos(context: Context,name:String,factory: SQLiteDatabase.CursorFactory?, version:Int):SQLiteOpenHelper(context, name,factory,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table articulos(codigo int primary key, descripcion text, precio real)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
