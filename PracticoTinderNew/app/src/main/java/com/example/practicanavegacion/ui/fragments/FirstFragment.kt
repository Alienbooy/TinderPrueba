package com.example.practicanavegacion.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.practicanavegacion.R


/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class FirstFragment : Fragment() {

    private lateinit var btnGotoSecondFragment : Button
    private lateinit var personViewModel: PersonViewModel
    private lateinit var likeButton: Button
    private lateinit var dislikeButton: Button
    private lateinit var viewPagerGallery: ViewPager2
    private lateinit var nameTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        likeButton = view.findViewById(R.id.likeButton)
        dislikeButton = view.findViewById(R.id.dislikeButton)
        viewPagerGallery = view.findViewById(R.id.viewPagerGallery)
        nameTextView = view.findViewById(R.id.nameTextView)
        personViewModel = ViewModelProvider(requireActivity()).get(PersonViewModel::class.java)
        btnGotoSecondFragment = view.findViewById(R.id.btnGoToSecondFragment)

        return view
    }

    private fun setupEventListeners(person: Person) {

        // Acción para el botón "Like"
        likeButton.setOnClickListener {
            personViewModel.likePerson(person)
            updateNextPerson()
        }

        // Acción para el botón "Dislike"
        dislikeButton.setOnClickListener {
            personViewModel.dislikePerson(person)
            updateNextPerson()
        }

        //  el botón para "ir al segundo fragment"
        btnGotoSecondFragment.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observamos la lista de personas
        personViewModel.personList.observe(viewLifecycleOwner) { personList ->
            if (personList.isNotEmpty()) {
                val person = personList[0] // Obtenemos la primera persona
                val galleryAdapter = GalleryAdapter(person.photos)
                viewPagerGallery.adapter = galleryAdapter
                nameTextView.text = person.name

                // Configurar listeners de los botones pasando la persona actual
                setupEventListeners(person)
            } else {
                nameTextView.text = "No hay más personas"
                viewPagerGallery.adapter = null
            }
        }
    }

    // Función para actualizar a la siguiente persona
    private fun updateNextPerson() {
        val updatedList = personViewModel.personList.value ?: emptyList()
        if (updatedList.isNotEmpty()) {
            val nextPerson = updatedList[0]
            val galleryAdapter = GalleryAdapter(nextPerson.photos)
            viewPagerGallery.adapter = galleryAdapter
            nameTextView.text = nextPerson.name
        } else {
            nameTextView.text = "No hay más personas"
            viewPagerGallery.adapter = null
        }
    }
}