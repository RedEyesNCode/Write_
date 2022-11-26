package com.redeyesncode.write.notedatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// We are creating the here in the kotlin table. Remember
// we create class in JAVA & data class in KOTLIN.


@Entity(tableName = "note_detail")
data class NoteDetailTable (
    // For auto-generating id.

    @PrimaryKey(autoGenerate = true)
    var note_id:Int=0,

    @ColumnInfo(name = "title") var title:String,
    @ColumnInfo(name = "content") var content:String,
    @ColumnInfo(name = "pages") var pages:Int,
    @ColumnInfo(name = "date") var date:String
)





