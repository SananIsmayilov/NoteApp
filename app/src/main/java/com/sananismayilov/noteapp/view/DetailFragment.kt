package com.sananismayilov.noteapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.sananismayilov.noteapp.R
import com.sananismayilov.noteapp.databinding.FragmentDetailBinding
import com.sananismayilov.noteapp.model.Note
import com.sananismayilov.noteapp.roomdb.NoteDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailFragment : Fragment(), CoroutineScope {

    private lateinit var binding: FragmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        arguments?.let {
            val args: DetailFragmentArgs by navArgs()
            var note1 = args.noteobj

            if (note1 != null) {
                binding.detailnote.setText(note1.note)
                binding.detailtittle.setText(note1.tittle)
            }
            binding.fabdetail.setOnClickListener {
                launch(Dispatchers.IO) {
                    NoteDb(binding.root.context).noteDao().deleteNote(note1)

                }
                Navigation.findNavController(binding.fabdetail)
                    .navigate(DetailFragmentDirections.actionnoteFragment())
            }
        }

        return binding.root
    }


    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}