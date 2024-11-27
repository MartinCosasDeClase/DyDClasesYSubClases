package com.example.ddclasessubclases

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.ddclasessubclases.databinding.FragmentClasesDetailsBinding
import com.example.ddclasessubclases.databinding.FragmentSubClasesDetailsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SubClases_details.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubClases_details : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentSubClasesDetailsBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentSubClasesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SubClases_details.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SubClases_details().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args: Bundle? = arguments
        if(args != null) {
            val item: SubClase? = args.getSerializable("item") as SubClase?
            if (item != null) {
                updateUi(item)
            }
            super.onViewCreated(view, savedInstanceState)
        }
    }

     private fun updateUi(subClase: SubClase){
        binding.txtNombre.text = subClase.nombre
        binding.txtDescription.text = subClase.descripcion

        val contexto: Context = context as Context

        Glide.with(contexto).load(
            subClase    .imagen
        ).into(binding.imgSubclase)
    }
}