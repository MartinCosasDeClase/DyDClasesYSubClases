package com.example.ddclasessubclases

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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
        val clases = ArrayList<Clase>()
        val retroFT = RetrofitServiceFactory.makeRetrofitService()
        val adapter = ClasesAdapter(
            requireContext(),
            R.layout.lst_item,
            clases
        );
        binding.lstDnd.setOnItemClickListener { adapter, _,position, _ ->
            val clase = adapter.getItemAtPosition(position) as Clase
            val args = Bundle().apply {
                putSerializable("item",clase)
            }

            NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_clases_details, args)

        }

        val model = ViewModelProvider(this).get(ClaseView::class.java)
        model.clases.observe(viewLifecycleOwner){result ->
            adapter.clear()
            adapter.addAll(result)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {

        super.onStart()
    }

}