package com.example.ddclasessubclases

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.ddclasessubclases.databinding.FragmentClasesDetailsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
@SuppressLint("StaticFieldLeak")
private var _binding: FragmentClasesDetailsBinding? = null
private val binding get() = _binding!!

/**
 * A simple [Fragment] subclass.
 * Use the [Clases_details.newInstance] factory method to
 * create an instance of this fragment.
 */
class Clases_details : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClasesDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Clases_details.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Clases_details().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: Bundle? = arguments
        val subClase = ArrayList<SubClase>()
        val adapter = SubClassAdapter(
            requireContext(),
            R.layout.subclase_item,
            subClase
        )
        if(args != null){
            val item:Clase? = args.getSerializable("item") as Clase?
            if (item != null){
                updateUi(item)
            }

            val model = ViewModelProvider(this)[ClaseView::class.java]
            model.subClase.observe(viewLifecycleOwner){result ->
                Log.d("XXX",result.toString())
                adapter.clear()
                if (item != null) {
                    adapter.addAll(result.stream().filter{a -> a.idClase == item.id}.toList())
                }
            }
            binding.lstSubclase.adapter = adapter

            binding.lstSubclase.setOnItemClickListener(){ adapter, _,position, _ ->
                val subClase = adapter.getItemAtPosition(position) as SubClase
                val args = Bundle().apply {
                    putSerializable("item",subClase)
                }

                NavHostFragment.findNavController(this).navigate(R.id.action_clases_details_to_subClases_details, args)

            }

        }
    }
    @SuppressLint("SuspiciousIndentation")
    private fun updateUi(clase: Clase){
        binding.nombreClase.text = clase.nombre
        binding.txtDescripcion.text = clase.descripcion

        val contexto: Context = context as Context

        Glide.with(contexto).load(
            clase.imagen
        ).into(binding.imagenClase)

        Glide.with(contexto)
            .load(clase.fondo)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    binding.fondo.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })
    }
}




