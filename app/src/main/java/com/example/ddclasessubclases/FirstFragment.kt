package com.example.ddclasessubclases

import android.app.Application
import android.content.Context
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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.PreferenceManager
import com.example.ddclasessubclases.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

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

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
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
            if(!isTablet()) {
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_FirstFragment_to_clases_details, args)
            }else{
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_firstFragment_self2, args)
            }

        }
        val rol = PreferenceManager.getDefaultSharedPreferences(context as Context)
        val opcion = rol.getString("rol","Todos")
        Log.d("XXXX",opcion.toString())
        val model = ViewModelProvider(this)[ClaseViewModel::class.java]
        model.clases.observe(viewLifecycleOwner){result ->
            Log.d("XXX",result.toString())
            adapterClass.clear()
            if(opcion != "Todos" || opcion == null)
                adapterClass.addAll(result.stream().filter{a -> a.rol.lowercase() == opcion?.lowercase()}.toList())
            else
                adapterClass.addAll(result)
        }
        binding.lstDnd.adapter = adapterClass
        super.onStart()
    }
    private fun restart(){
        val claseViewModel = ClaseViewModel(app = Application())
        lifecycleScope.launch {
            claseViewModel.reload()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        Log.d("XXXMenu", id.toString())
        if(id==R.id.action_settings){
            val i = Intent(requireContext(),SettingsActivity::class.java )
            startActivity(i)
            return true
        }
        if(id == R.id.btnRefresh){
            Log.d("XXX","REFESH")
            Toast.makeText(context,"Cargando datos...",Toast.LENGTH_LONG).show()
            restart()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    fun isTablet():Boolean{
        return resources.getBoolean(R.bool.large)
    }

}