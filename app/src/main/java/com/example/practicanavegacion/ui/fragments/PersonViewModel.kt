package com.example.practicanavegacion.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicanavegacion.R

class PersonViewModel : ViewModel() {

    private val _personList = MutableLiveData<List<Person>>(emptyList())
    val personList: LiveData<List<Person>> get() = _personList

    private val _likesList = MutableLiveData<List<Person>>(emptyList())
    val likesList: LiveData<List<Person>> get() = _likesList

    init {
        // Inicializamos la lista de personas con imágenes de recursos locales
        _personList.value = listOf(
            Person("Angelo Gareca", listOf(R.drawable.photo1, R.drawable.photo2, R.drawable.photo3)),
            Person("Sebita rengel", listOf(R.drawable.photo4, R.drawable.photo5, R.drawable.photo6)),
            Person("Linda Pozo", listOf(R.drawable.photo7, R.drawable.photo8, R.drawable.photo9)),
            Person("Savlitfss", listOf(R.drawable.photo10, R.drawable.photo11, R.drawable.photo12)),
            Person("Annabel Lucinda", listOf(R.drawable.photo13, R.drawable.photo14, R.drawable.photo15)),
            Person("Sofia Suñiga", listOf(R.drawable.photo16, R.drawable.photo17, R.drawable.photo18)),
            Person("Katsumi pro", listOf(R.drawable.photo19, R.drawable.photo20, R.drawable.photo21)),
            Person("Alana Flores", listOf(R.drawable.photo22, R.drawable.photo23, R.drawable.photo24)),
            Person("Isabella Ferreira", listOf(R.drawable.photo25, R.drawable.photo26, R.drawable.photo27)),
            Person("Young Miko", listOf(R.drawable.photo28, R.drawable.photo29, R.drawable.photo30)),
            Person("Milica Pancha", listOf(R.drawable.photo31, R.drawable.photo32, R.drawable.photo33)),
            Person("Julia Allegretti", listOf(R.drawable.photo34, R.drawable.photo35, R.drawable.photo36)),
            Person("Nicky Nicole", listOf(R.drawable.photo37, R.drawable.photo38, R.drawable.photo39, R.drawable.photo40)),
            Person("Paola Ojj", listOf(R.drawable.photo41, R.drawable.photo42, R.drawable.photo43)),
            Person("Camila Cabello", listOf(R.drawable.photo44, R.drawable.photo45, R.drawable.photo46)),
        )
    }

    // Función para dar "Like" a una persona
    fun likePerson(person: Person) {
        _personList.value = _personList.value?.minus(person)  // Eliminamos de la lista original
        _likesList.value = _likesList.value?.plus(person)     // Agregamos a la lista de "Likes"
    }

    // Función para dar "Dislike" a una persona
    fun dislikePerson(person: Person) {
        _personList.value = _personList.value?.minus(person)  // Solo eliminamos de la lista original
    }
}

