package com.example.ddclasessubclases

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
@Entity
class Clase : Serializable{
    @PrimaryKey
    @JsonProperty("id") var id:Int = 0
    @JsonProperty("nombre") var nombre:String = ""
    @JsonProperty("descripcion") var descripcion:String = ""
    @JsonProperty("imagen") var imagen:String = ""
    @JsonProperty("Background") var fondo:String = ""
    @JsonProperty("rol")var rol:String = ""

    override fun toString(): String {
        return "Clase(nombre='$nombre', descripcion='$descripcion', imagen='$imagen', id=$id)"
    }


}