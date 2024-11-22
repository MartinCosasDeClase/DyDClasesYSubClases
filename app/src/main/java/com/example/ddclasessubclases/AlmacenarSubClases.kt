package com.example.ddclasessubclases

import java.io.Serializable

class AlmacenarSubClases : Serializable{
    var clase = Clase()
    var subClases = ArrayList<SubClase>()
}