package com.redeyesncode.write.notedatabase

import androidx.room.*


@Dao
interface NoteDetailsDao {
    // Query for getting all the list elements from the database.
    @Query("SELECT * FROM note_detail")
    fun getAllNotes(): List<NoteDetailTable>


    // Query for inserting th notes in into the table.
    @Insert
    fun addNoteInsert(vararg note_detail:NoteDetailTable)

    // Delete note from the table.

    @Delete
    fun deleteNote(vararg note_detail: NoteDetailTable)

    @Update
    fun updateTodo(vararg note_detail: NoteDetailTable)

}