package com.redeyesncode.write.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redeyesncode.write.databinding.NoteCardBinding
import com.redeyesncode.write.notedatabase.NoteDetailTable

class DocumentAdapter(var context:Context,var documents:List<NoteDetailTable>, var onClickAdapter: onClick):RecyclerView.Adapter<DocumentAdapter.MyViewHolder>() {

    private lateinit var binding:NoteCardBinding
    private var onClickDocument = onClickAdapter


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = NoteCardBinding.inflate(LayoutInflater.from(context))


        return MyViewHolder(binding)



    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        // Setting up the elements in the card view for note date , note pages and note content.
        var noteDetailTable = documents.get(position)

        holder.cardBinding.tvNoteDate.text = noteDetailTable.date
        holder.cardBinding.tvNotePages.text = noteDetailTable.pages.toString()+ "pages."
        holder.cardBinding.tvTitle.text = noteDetailTable.title

        // This Card Does not have any content.

        holder.cardBinding.mainLayout.setOnClickListener {
            onClickDocument.onViewNote(noteDetailTable)
        }
        holder.cardBinding.btnDeleteNote.setOnClickListener {
            onClickDocument.onDeleteClick(position,noteDetailTable)
        }


    }

    override fun getItemCount(): Int {
        return documents.size

    }

    class MyViewHolder(var cardBinding: NoteCardBinding):RecyclerView.ViewHolder(cardBinding.root)

    interface onClick{
        fun onDeleteClick(position: Int, noteDetailTable: NoteDetailTable)
        fun onViewNote(noteDetailTable: NoteDetailTable)
    }
}