package com.example.ddclasessubclases

import com.fasterxml.jackson.annotation.JsonProperty

class Clase {
    @JsonProperty("nombre") val nombre:String = ""
    @JsonProperty("descripcion") val descripcion:String = ""
    @JsonProperty("imagen") val imagen:String = ""
    @JsonProperty("id") val id:Int = 0
}