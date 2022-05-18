package com.example.personregister.view.personas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.personregister.R
import com.example.personregister.databinding.ListaPersonasFragmentBinding
import com.example.personregister.model.Ocupacion
import com.example.personregister.model.Persona
import com.example.personregister.viewmodel.PersonaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListaPersonasFragment : Fragment() {

    private val viewModel: PersonaViewModel by viewModels()
    private lateinit var binding: ListaPersonasFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListaPersonasFragmentBinding.inflate(inflater, container, false)

        val adapter = PersonasAdapter(::openPersonasFragment)

        binding.personasRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.personas.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect { lista -> adapter.submitList(lista) }
        }

        binding.agregarButton.setOnClickListener {
            openPersonasFragment()
        }



        return binding.root
    }

    fun openPersonasFragment(persona: Persona?=null)  {
        val action = ListaPersonasFragmentDirections.actionListaPersonasFragmentToPersonasFragment(persona)
        findNavController().navigate(action)
    }




}