package com.example.personregister.view.ocupaciones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.personregister.databinding.OcupacionesFragmentBinding
import com.example.personregister.databinding.PersonasFragmentBinding
import com.example.personregister.model.Ocupacion
import com.example.personregister.model.Persona
import com.example.personregister.viewmodel.OcupacionViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OcupacionesFragment : Fragment(){
    private val viewModel: OcupacionViewModel by viewModels()
    private lateinit var binding: OcupacionesFragmentBinding

    // get the arguments
    private val args : OcupacionesFragmentArgs by navArgs()

    private var ocupacionId: Int =0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OcupacionesFragmentBinding.inflate(inflater, container, false)

        LlenarCampos()

        binding.guardarButton.setOnClickListener {
            viewModel.guardar(
                Ocupacion(
                    ocupacionId,
                    binding.ocupacionEditText.text.toString()
                )
            )
        }

        viewModel.guardado.observe(viewLifecycleOwner) {
            if (it) {
                Snackbar.make(binding.ocupacionEditText, "Esta ocupacion ha sido guardada", Snackbar.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        }

        return binding.root
    }

    fun LlenarCampos(){
        val ocupacion : Ocupacion? = args.ocupacion

        ocupacion?.let {
            ocupacionId = it.OcupacionId
            binding.ocupacionEditText.setText(it.Nombres)
        }
    }
}