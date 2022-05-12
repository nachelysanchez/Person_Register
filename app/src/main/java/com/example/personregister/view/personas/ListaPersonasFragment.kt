package com.example.personregister.view.personas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.personregister.R

class ListaPersonasFragment : Fragment() {
    companion object {
        fun newInstance() = ListaPersonasFragment()
    }

    private lateinit var viewModel: ListaPersonasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_personas_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListaPersonasViewModel::class.java)
        // Use the ViewModel
    }
}