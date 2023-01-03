package com.redeyesncode.write.dashboard.documents

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.android.material.snackbar.Snackbar
import com.redeyesncode.write.R
import com.redeyesncode.write.dashboard.adapter.DocumentAdapter
import com.redeyesncode.write.dashboard.createnote.CreateNoteActivity
import com.redeyesncode.write.dashboard.viewnote.ViewNoteActivity
import com.redeyesncode.write.databinding.FragmentDocumentsBinding
import com.redeyesncode.write.notedatabase.AppDatabase
import com.redeyesncode.write.notedatabase.NoteDetailTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DocumentsFragment : Fragment() , DocumentAdapter.onClick {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentDocumentsBinding
    private lateinit var fragmentContext:Context
    private lateinit var db:AppDatabase
    private lateinit var documentAdapter:DocumentAdapter
    var documents:List<NoteDetailTable> = listOf()
    private lateinit var onDocumentAdapterClick :DocumentAdapter.onClick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.fragmentContext = context

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onDocumentAdapterClick = this
        updateNotesAdapter();


    }



    override fun onResume() {
        super.onResume()
        // Calling to update the notes list if coming from CreateNotesActivity.

        updateNotesAdapter()

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDocumentsBinding.inflate(layoutInflater,container,false);
        onDocumentAdapterClick = this
       documentAdapter = DocumentAdapter(fragmentContext,documents,this)
        initClicks()

        return binding.root
    }

    override fun onDeleteClick(position: Int, noteDetailTable: NoteDetailTable) {
        deleteNote(noteDetailTable)
    }


    override fun onViewNote(noteDetailTable: NoteDetailTable) {
        var viewNoteIntent = Intent(fragmentContext,ViewNoteActivity::class.java)
        viewNoteIntent.putExtra("NOTE_ID",noteDetailTable.note_id)
        startActivity(viewNoteIntent)


    }
    private fun deleteNote(noteDetailTable: NoteDetailTable) {
        GlobalScope.launch {
            // Here we are starting the Room Database.
            val db = Room.databaseBuilder(
                fragmentContext, AppDatabase::class.java, "write_.db"
            ).build()

            //AppliedCode For inputting the samepl data in the retrofit saved instance.
            // Fetching the stored list data.

            // Passing the adapter noteDetail Into the Dao.

            db.noteDetailsDao().deleteNote(noteDetailTable)

            // Basic Context Switching in Fragment.
            // Coroutines Context Switching for updating the UI on Main Thread.
            // This means we are switching from the Background Thread to the main thread.

            launch (Dispatchers.Main){
                binding.recvNoteCards.layoutManager = GridLayoutManager(fragmentContext,2)
                binding.recvNoteCards.adapter = DocumentAdapter(fragmentContext,documents,this@DocumentsFragment)

            }

        }
        Snackbar.make(binding.root,"Deleted Note Successfully !", Snackbar.LENGTH_LONG).show()
        updateNotesAdapter()

    }

    private fun updateNotesAdapter() {
        // Reset the list as well.
        documents = listOf()
        GlobalScope.launch {
            // Here we are starting the Room Database.
            val db = Room.databaseBuilder(
                fragmentContext, AppDatabase::class.java, "write_.db"
            ).build()

            //AppliedCode For inputting the samepl data in the retrofit saved instance.
            // Fetching the stored list data.

            documents = db.noteDetailsDao().getAllNotes()

            // Basic Context Switching in Fragment.
            // Coroutines Context Switching for updating the UI on Main Thread.
            // This means we are switching from the Background Thread to the main thread.

            launch (Dispatchers.Main){
                binding.recvNoteCards.layoutManager = GridLayoutManager(fragmentContext,2)
                binding.recvNoteCards.adapter = DocumentAdapter(fragmentContext,documents,this@DocumentsFragment)

                if(documents.isEmpty()){
                    binding.recvNoteCards.visibility = View.GONE
                    binding.tvNoNotes.visibility=View.VISIBLE
                }else{
                    binding.recvNoteCards.visibility = View.VISIBLE
                    binding.tvNoNotes.visibility=View.GONE
                }
            }

        }

    }

    private fun initClicks() {
        binding.fabCreateNote.setOnClickListener {
            var createNoteIntent = Intent(fragmentContext,CreateNoteActivity::class.java)
            startActivity(createNoteIntent)
        }
        binding.tvAddNote.setOnClickListener {
            var createNoteIntent = Intent(fragmentContext,CreateNoteActivity::class.java)
            startActivity(createNoteIntent)
        }
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DocumentsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}