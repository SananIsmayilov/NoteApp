package com.sananismayilov.noteapp.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColor
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sananismayilov.noteapp.R
import com.sananismayilov.noteapp.adapter.Adapter
import com.sananismayilov.noteapp.databinding.FragmentNoteBinding
import com.sananismayilov.noteapp.model.Note
import com.sananismayilov.noteapp.roomdb.NoteDao
import com.sananismayilov.noteapp.roomdb.NoteDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class NoteFragment : Fragment(), CoroutineScope {
    private lateinit var binding: FragmentNoteBinding
    private lateinit var note: List<Note>
    lateinit var adapter: Adapter
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() =  job + Dispatchers.Main


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = GridLayoutManager(binding.root.context,1)
        ItemTouchHelper(swipe).attachToRecyclerView(binding.recyclerView)
        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(it).navigate(NoteFragmentDirections.notetonoteadd())
        }
     getData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
    }

    private val swipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {

            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            launch (Dispatchers.IO){
            val position = viewHolder.layoutPosition
            val ob = note[position]
            NoteDb(binding.root.context).noteDao().deleteNote(ob)
                
            }
            getData()

        }

    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun getData(){
        launch {
            val notelist = withContext(Dispatchers.IO){ NoteDb(binding.root.context).noteDao().getallnote()}
            adapter = Adapter(binding.root.context, notelist)
            note = notelist
            binding.recyclerView.adapter = adapter
        }
    }


}