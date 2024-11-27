package com.example.ddclasessubclases

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class SubClassAdapter : ArrayAdapter<SubClase> {
    constructor(context: Context, resources: Int, objects: ArrayList<SubClase>) : super(
        context,
        resources,
        objects
    )

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val subClase = getItem(position)

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.subclase_item,parent,false)
        val name: TextView = view.findViewById(R.id.txt_name)
        val img: ImageView = view.findViewById(R.id.img_lst)

        name.text = subClase?.nombre

        Glide.with(context).load(
            subClase?.imagen
        ).into(img)

        return view
    }
}