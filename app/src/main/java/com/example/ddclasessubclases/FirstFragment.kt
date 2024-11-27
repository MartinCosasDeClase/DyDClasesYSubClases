package com.example.ddclasessubclases

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.withStarted
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
import java.util.concurrent.Executors

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


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        val clases = ArrayList<Clase>()
        val adapterClass = ClasesAdapter(
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
            Log.d("XXX",result.toString())
            adapterClass.clear()
            adapterClass.addAll(result)
        }
        binding.lstDnd.adapter = adapterClass
        val executors = Executors.newSingleThreadExecutor()
        executors.execute {
            restart()
        }
        super.onStart()
    }
    private fun restart(){
        val claseView = ClaseView(app = Application())
        lifecycleScope.launch {
            claseView.reload()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        Log.d("XXXMenu", id.toString())
        if(id==R.id.action_settings){
            val i = Intent(requireContext(),SettingsActivity::class.java )
            startActivity(i)
            return true
        }
        if(id == R.id.btnRefresh){
            val classModel = ClaseView(app = Application())
            lifecycleScope.launch {
                classModel.reload()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}