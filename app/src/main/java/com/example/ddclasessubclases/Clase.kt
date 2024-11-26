package com.example.ddclasessubclases

import androidx.room.Entity
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
@Entity
class Clase : Serializable{
    @JsonProperty("nombre") val nombre:String = ""
    @JsonProperty("descripcion") val descripcion:String = ""
    @JsonProperty("imagen") val imagen:String = ""
    @JsonProperty("id") val id:Int = 0
}