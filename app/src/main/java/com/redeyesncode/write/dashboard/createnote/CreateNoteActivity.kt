package com.redeyesncode.write.dashboard.createnote

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.redeyesncode.write.R
import com.redeyesncode.write.databinding.ActivityCreateNoteBinding
import com.redeyesncode.write.notedatabase.AppDatabase
import com.redeyesncode.write.notedatabase.NoteDetailTable
import com.redeyesncode.write.utils.AppUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CreateNoteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCreateNoteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateNoteBinding.inflate(layoutInflater)

        initClicks()

        setContentView(binding.root)
    }

    private fun initClicks() {
        binding.btnSaveNote.setOnClickListener {
            val noteTitle =
                binding.edtTitle.text.toString()
            val noteContent = binding.edtDescription.text.toString()
            GlobalScope.launch {
                // Here we are starting the Room Database.
                val db = Room.databaseBuilder(
                    this@CreateNoteActivity, AppDatabase::class.java, "write_.db"
                ).build()

                //AppliedCode For inputting the samepl data in the retrofit saved instance.

                // Inserting Sample Data into the database.
                db.noteDetailsDao().addNoteInsert(NoteDetailTable(title = noteTitle, content = noteContent, pages = 1, date = AppUtils().getCurrentDateAndroid()))

                // Coroutine Context switching here we are moving from the background thread to the main thread to display a snackbar.
//                launch (Dispatchers.Main){
//                    Snackbar.make(binding.root,"New Note Saved Successfully !",Snackbar.LENGTH_LONG).show()
//
//                }
            }
            binding.edtTitle.setText("")
            binding.edtDescription.setText("")
            Snackbar.make(binding.root,"New Note Saved Successfully !",Snackbar.LENGTH_LONG).show()
        }
        binding.btnShareNote.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.github_link))
                startActivity(Intent.createChooser(shareIntent, "Choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
        }

    }
}