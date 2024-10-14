package com.example.practicanavegacion.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicanavegacion.R


/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    private lateinit var personViewModel: PersonViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PersonLikesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewLikes)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)  // 2 columnas

        // Usamos el nuevo adaptador exclusivo para el segundo fragmento
        adapter = PersonLikesAdapter()
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personViewModel = ViewModelProvider(requireActivity()).get(PersonViewModel::class.java)

        // Observamos la lista de likes
        personViewModel.likesList.observe(viewLifecycleOwner) { likesList ->
            adapter.submitList(likesList)
        }
    }
}