package com.redeyesncode.write.notedatabase.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// We are creating the here in the kotlin table. Remember
// we create class in JAVA & data class in KOTLIN.


@Entity(tableName = "note_detail")
data class NoteDetailTable (
    // For auto-generating id.

    @PrimaryKey(autoGenerate = true)
    var note_id:Int,

    @ColumnInfo(name = "title") var title:String,
    @ColumnInfo(name = "content") var content:String
)





