package com.example.personregister.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personregister.data.OcupacionDao
import com.example.personregister.model.Ocupacion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OcupacionViewModel @Inject constructor(val ocupacionDao: OcupacionDao): ViewModel() {
    val ocupaciones : Flow<List<Ocupacion>>
        get() =  ocupacionDao.getList()

    private val _guardado = MutableLiveData(false)
    val guardado: LiveData<Boolean> get() = _guardado

    fun guardar(ocupacion : Ocupacion){
        viewModelScope.launch {
            ocupacionDao.insertar(ocupacion)
            _guardado.value=true
        }
    }
}