package com.redeyesncode.write.dashboard.readermode

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.redeyesncode.write.R
import com.redeyesncode.write.dashboard.adapter.DocumentAdapter
import com.redeyesncode.write.databinding.FragmentReaderBinding
import com.redeyesncode.write.notedatabase.AppDatabase
import com.redeyesncode.write.notedatabase.NoteDetailTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ReaderFragment : Fragment(), DocumentAdapter.onClick {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentReaderBinding
    private lateinit var fragmentContext:Context
    var documents:List<NoteDetailTable> = listOf()
    private lateinit var documentAdapter: DocumentAdapter

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
        updateNotesAdapter()
    }

    override fun onResume() {
        super.onResume()
        updateNotesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReaderBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        return binding.root
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
                binding.recvNoteCards.adapter = DocumentAdapter(fragmentContext,documents)
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

    override fun onDeleteClick(position: Int, noteDetailTable: NoteDetailTable) {
        TODO("Not yet implemented")
    }

    override fun onViewNote(noteDetailTable: NoteDetailTable) {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReaderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}