package com.redeyesncode.write

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.room.Room
import androidx.room.RoomDatabase
import com.redeyesncode.write.dashboard.DashboardActivity
import com.redeyesncode.write.databinding.ActivitySplashBinding
import com.redeyesncode.write.notedatabase.AppDatabase
import com.redeyesncode.write.notedatabase.NoteDetailTable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySplashBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Starting the handler for the basic activity now.



        Handler().postDelayed({
            var intentDashboard = Intent(this@SplashActivity,DashboardActivity::class.java )
            intentDashboard.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intentDashboard)
        },2000)




    }

    private fun initRoomDatabase() {
        GlobalScope.launch {
            // Here we are starting the Room Database.
            val db = Room.databaseBuilder(
                this@SplashActivity,AppDatabase::class.java, "write_.db"
            ).build()

            // Inserting Sample Data into the database.
            //
            db.noteDetailsDao().addNoteInsert(NoteDetailTable(title = "SAMPLE_TITLE", content = "SAMPLE_NOTE_DETAILS",pages=0, date = "SAMPLE_DATE."))





        }



    }
}