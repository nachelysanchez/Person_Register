package com.example.personregister.view.personas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.personregister.R
import com.example.personregister.databinding.PersonasFragmentBinding
import com.example.personregister.model.Ocupacion
import com.example.personregister.model.Persona
import com.example.personregister.viewmodel.OcupacionViewModel
import com.example.personregister.viewmodel.PersonaViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class PersonasFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private val viewModel: PersonaViewModel by viewModels()
    private val viewModelO: OcupacionViewModel by viewModels()
    private lateinit var binding: PersonasFragmentBinding

    // get the arguments
    private val args : PersonasFragmentArgs by navArgs()

    private var personaId: Int =0
    private var ocupacionId: Int =0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PersonasFragmentBinding.inflate(inflater, container, false)

        LlenarCampos()

        val aaOcupaciones = ArrayAdapter<String>(inflater.context,
            android.R.layout.simple_spinner_dropdown_item)

        binding.Spinner.onItemSelectedListener = this

        binding.Spinner.adapter = aaOcupaciones

        lifecycleScope.launch{
            viewModelO.ocupaciones.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect {
                lista -> lista.forEach {
                    aaOcupaciones.add(it.Nombres)
                }
            }
        }

        //val aaOcupaciones = ArrayAdapter<Ocupacion>(inflater.context, )

        binding.guardarButton.setOnClickListener {
            viewModel.guardar(
                Persona(
                    personaId,
                    binding.personaEditText.text.toString(),
                    binding.emailEditText.text.toString(),
                    binding.Spinner.id,
                    binding.balanceEditText.floatValue()
                )
            )
        }

        viewModel.guardado.observe(viewLifecycleOwner) {
            if (it) {
                Snackbar.make(binding.balanceEditText, "Esta persona ha sido guardada", Snackbar.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        }

        if (args.persona == null){
            binding.eliminarButton.visibility = View.GONE
        }

        binding.eliminarButton.setOnClickListener {
            args.persona?.let { it -> viewModel.eliminar(it) }
            Snackbar.make(binding.balanceEditText, "Persona eliminada", Snackbar.LENGTH_LONG).show()
            findNavController().navigateUp()
        }

        binding.agregarOcupacionButton.setOnClickListener{
            openOcupacionesFragment()
        }

        return binding.root
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        ocupacionId = position
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    fun LlenarCampos(){
        val persona: Persona? = args.persona

        persona?.let {
            personaId = it.PersonaId
            binding.personaEditText.setText(it.Nombres)
            binding.emailEditText.setText(it.Email)
            binding.Spinner.setSelection(ocupacionId)
            binding.balanceEditText.setText(it.Salario.toString())
        }
    }

    fun openOcupacionesFragment(ocupacion : Ocupacion?=null){
        val action = PersonasFragmentDirections.actionPersonasFragmentToOcupacionesFragment(ocupacion)
        findNavController().navigate(action)
    }

    fun TextInputEditText.floatValue() = text.toString().toFloatOrNull() ?: 0.0f

}