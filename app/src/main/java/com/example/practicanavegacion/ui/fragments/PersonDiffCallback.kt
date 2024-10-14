package com.example.practicanavegacion.ui.fragments

import androidx.recyclerview.widget.DiffUtil

class PersonDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        // Define cómo saber si dos objetos son el mismo
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        // Define cómo saber si el contenido es el mismo
        return oldItem == newItem
    }
}
