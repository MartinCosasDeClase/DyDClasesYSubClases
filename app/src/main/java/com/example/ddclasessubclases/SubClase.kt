package com.example.ddclasessubclases

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@Entity
class SubClase : Serializable{
    @PrimaryKey
    @JsonProperty("id") var id:Int = 0
    @JsonProperty("id_clase") var idClase:Int = 0
    @JsonProperty("nombre") var nombre:String = ""
    @JsonProperty("descripcion") var descripcion:String = ""
    @JsonProperty("imagen") var imagen:String = ""


    override fun toString(): String {
        return "SubClase(id=$id, idClase=$idClase, nombre='$nombre', descripcion='$descripcion', imagen='$imagen')"
    }
}