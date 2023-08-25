package com.sananismayilov.noteapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sananismayilov.noteapp.databinding.FragmentDetailBinding
import com.sananismayilov.noteapp.databinding.RecyclerrowBinding
import com.sananismayilov.noteapp.model.Note
import com.sananismayilov.noteapp.view.NoteFragmentDirections

class Adapter(val context: Context,val list : List<Note>) : RecyclerView.Adapter<Adapter.NoteHolder>(){

    inner class NoteHolder(val binding : RecyclerrowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val binding = RecyclerrowBinding.inflate(LayoutInflater.from(context),parent,false)
        return NoteHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.binding.notetitle.text = list.get(position).tittle
        holder.binding.card.setOnClickListener {
            val note = list[position]
            Navigation.findNavController(it).navigate(NoteFragmentDirections.actiondetail(note))
        }
    }
}