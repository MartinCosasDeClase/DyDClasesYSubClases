package com.example.ddclasessubclases

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.ddclasessubclases.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.Tag

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    private var _binding: FragmentFirstBinding? = null
    val alm = AlmacenarSubClases()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retroFT = RetrofitServiceFactory.makeRetrofitService()

        lifecycleScope.launch {
            val clases: ArrayList<Clase> = retroFT.listCLases("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImV5cmRrdnZyaGJjaWJubXljZnp3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjEwOTgsImV4cCI6MjA0NzIzNzA5OH0.pwMOzVqg5eDMn0ywTowo4HEakJCFFFozRiKJVKphpV4")
            val adapter = ClasesAdapter(
                requireContext(),
                R.layout.lst_item,
                clases
            )
            alm.subClases = retroFT.listaSubClases("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImV5cmRrdnZyaGJjaWJubXljZnp3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjEwOTgsImV4cCI6MjA0NzIzNzA5OH0.pwMOzVqg5eDMn0ywTowo4HEakJCFFFozRiKJVKphpV4")
            binding.lstDnd.adapter=adapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        binding.lstDnd.setOnItemClickListener { adapter, _,position, _ ->

            alm.clase = adapter.getItemAtPosition(position) as Clase
            val args = Bundle().apply {
                putSerializable("item",alm)
            }

            NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_clases_details, args)

        }
        super.onStart()
    }

}