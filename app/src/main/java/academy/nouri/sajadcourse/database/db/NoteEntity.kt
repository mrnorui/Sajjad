package academy.nouri.sajadcourse.database.db

import academy.nouri.sajadcourse.utils.NOTE_TABLE
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val noteId: Int,
    @ColumnInfo(name = "note_title")
    val noteTitle: String,
    val noteInfo: String
)