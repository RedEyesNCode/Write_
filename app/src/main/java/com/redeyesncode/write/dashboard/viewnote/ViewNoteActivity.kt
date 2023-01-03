package com.redeyesncode.write.dashboard.viewnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.redeyesncode.write.R
import com.redeyesncode.write.databinding.ActivityViewNoteBinding
import com.redeyesncode.write.notedatabase.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewNoteActivity : AppCompatActivity() {

    lateinit var binding:ActivityViewNoteBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClicks()
        getData()

    }

    private fun getData() {
    var noteId =     intent.getIntExtra("NOTE_ID",-1)

        if(noteId==-1){
            onBackPressed()
            Snackbar.make(binding.root,"Unable to fetch note details :: Note Id -1.", Snackbar.LENGTH_LONG).show()
        }else{

            GlobalScope.launch {
                val db = Room.databaseBuilder(this@ViewNoteActivity, AppDatabase::class.java, "write_.db"
                ).build()

                //AppliedCode For inputting the samepl data in the retrofit saved instance.
                // Fetching the stored list data.

                var noteDetailTable = db.noteDetailsDao().getNoteById(noteId)

                if(noteDetailTable!=null){
                    launch (Dispatchers.Main){
                        binding.tvTitle.text = noteDetailTable.title
                        binding.tvDescription.text = noteDetailTable.content
                    }

                }else{
                    launch (Dispatchers.Main){
                        onBackPressed()
                        Snackbar.make(binding.root,"Unable to fetch note details :: Note Id -1.", Snackbar.LENGTH_LONG).show()
                    }
                }



            }

        }


    }

    private fun initClicks() {
        binding.btnBackViewNote.setOnClickListener {
            onBackPressed()
        }
    }
}