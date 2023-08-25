package com.sananismayilov.noteapp.view

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.sananismayilov.noteapp.R
import com.sananismayilov.noteapp.databinding.FragmentNoteAdBinding
import com.sananismayilov.noteapp.roomdb.NoteDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NoteAddFragment : Fragment(),CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default+ job


private lateinit var binding: FragmentNoteAdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteAdBinding.inflate(inflater, container, false)

        binding.fab.setOnClickListener {
            if(!TextUtils.isEmpty(binding.etxt1.text.toString()) && !TextUtils.isEmpty(binding.etxt2.text.toString())){
        binding.progressBar2.visibility = View.VISIBLE
        launch {

            val note = com.sananismayilov.noteapp.model.Note(binding.etxt1.text.toString(),binding.etxt2.text.toString())
            val dao = NoteDb(binding.root.context).noteDao().insertnote(note)
        }
                binding.progressBar2.visibility =View.GONE
                Navigation.findNavController(it).navigate(NoteAddFragmentDirections.actionNoteAddFragmentToNoteFragment())
            }


        }





        return binding.root
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }


}