package com.example.practicanavegacion.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicanavegacion.R
import com.example.practicanavegacion.databinding.FragmentFirstBinding

class PersonAdapter(private val onClick: ((Person, Boolean) -> Unit)?) :
    ListAdapter<Person, PersonAdapter.PersonViewHolder>(PersonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = FragmentFirstBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)
        holder.bind(person)
    }

    inner class PersonViewHolder(private val binding: FragmentFirstBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.nameTextView.text = person.name

            // Usar ViewPager2 para mostrar la galería de imágenes
            val galleryAdapter = GalleryAdapter(person.photos)
            binding.viewPagerGallery.adapter = galleryAdapter

            // Si onClick es null, estamos en el fragmento de Likes, y ocultamos los botones
            if (onClick == null) {
                binding.likeButton.visibility = View.GONE
                binding.dislikeButton.visibility = View.GONE
                binding.btnGoToSecondFragment.visibility = View.GONE
            } else {
                // En el fragment primero se muestran los botones
                binding.likeButton.visibility = View.VISIBLE
                binding.dislikeButton.visibility = View.VISIBLE
                binding.btnGoToSecondFragment.visibility = View.VISIBLE

                binding.likeButton.setOnClickListener {
                    onClick.invoke(person, true)  // Si el botón de "Like" es presionado
                }

                binding.dislikeButton.setOnClickListener {
                    onClick.invoke(person, false)  // Si el botón de "Dislike" es presionado
                }
            }
        }
    }
}





class GalleryAdapter(private val photoList: List<Int>) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val photoResId = photoList[position]
        // Cargar imagen usando Glide o cualquier otra herramienta
        Glide.with(holder.itemView.context)
            .load(photoResId)
            .centerCrop()
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = photoList.size

    inner class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewPerson)
    }
}




