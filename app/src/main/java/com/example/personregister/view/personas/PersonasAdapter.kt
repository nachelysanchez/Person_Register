package com.example.personregister.view.personas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.personregister.databinding.RowPersonasBinding
import com.example.personregister.model.Persona
import com.google.android.material.textfield.TextInputEditText

class PersonasAdapter(
    private var onItemClicked: ((persona : Persona) -> Unit)
) : ListAdapter<Persona, PersonasAdapter.PersonasViewHolder>(PersonasDiffCallBack()) {
    override fun onBindViewHolder(holder: PersonasViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):PersonasViewHolder {
        val binding =
            RowPersonasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonasViewHolder(binding)
    }

    inner class PersonasViewHolder(private val binding: RowPersonasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Persona) {
            binding.PersonaIdTextView.text = item.PersonaId.toString()
            binding.NombreTextView.text = item.Nombres
            binding.emailTextView.text = item.Email
            binding.salarioTextView.text = item.Salario.toString();

            binding.rowPersonaConstraintlayout.setOnClickListener {
                val action = ListaPersonasFragmentDirections.actionListaPersonasFragmentToPersonasFragment(item)

                binding.root.findNavController().navigate(action)
            }
        }
    }
}
class PersonasDiffCallBack : DiffUtil.ItemCallback<Persona>() {
    override fun areItemsTheSame(oldItem: Persona, newItem: Persona): Boolean {
        return oldItem.PersonaId == newItem.PersonaId
    }

    override fun areContentsTheSame(oldItem: Persona, newItem: Persona): Boolean {
        return oldItem == newItem
    }
}