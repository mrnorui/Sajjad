package academy.nouri.sajadcourse.database.db

import academy.nouri.sajadcourse.utils.NOTE_TABLE
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity)

    @Update
    fun updateNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM $NOTE_TABLE ORDER BY noteId DESC")
    fun getAllNotes(): MutableList<NoteEntity>

    @Query("SELECT * FROM $NOTE_TABLE WHERE noteId LIKE :id")
    fun getNote(id: Int): NoteEntity

    @Query("SELECT * FROM $NOTE_TABLE WHERE note_title LIKE :title")
    fun searchNote(title: String): MutableList<NoteEntity>
}