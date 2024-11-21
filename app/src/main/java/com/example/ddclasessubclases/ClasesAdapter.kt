package com.example.ddclasessubclases

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide




class ClasesAdapter : ArrayAdapter<Clase> {
    constructor(context: Context, resources: Int, objects: ArrayList<Clase>) : super(
            context,
            resources,
            objects
            )

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val clase = getItem(position)

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.lst_item,parent,false)
        val name:TextView = view.findViewById(R.id.txt_name)
        val img:ImageView = view.findViewById(R.id.img_lst)

        name.text = clase?.nombre

        Glide.with(context).load(
            clase?.imagen
        ).into(img)

        return view
    }

}