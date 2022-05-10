package com.example.personregister.viewmodel

import androidx.lifecycle.ViewModel
import com.example.personregister.data.PersonaDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonaViewModel @Inject constructor(val personaDao: PersonaDao): ViewModel(){


}