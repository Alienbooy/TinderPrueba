package com.example.practicanavegacion.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicanavegacion.databinding.ItemGalleryLikesBinding

class PersonLikesAdapter :
    ListAdapter<Person, PersonLikesAdapter.PersonViewHolder>(PersonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        // Usamos un layout específico para los likes (con imágenes más pequeñas)
        val binding = ItemGalleryLikesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)
        holder.bind(person)
    }

    inner class PersonViewHolder(private val binding: ItemGalleryLikesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.nameTextViewLikes.text = person.name
            Glide.with(binding.imageViewPersonLikes.context)
                .load(person.photos.first())
                .centerCrop()
                .into(binding.imageViewPersonLikes)
        }
    }
}
