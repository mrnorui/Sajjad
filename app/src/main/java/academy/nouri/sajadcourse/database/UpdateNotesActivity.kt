package academy.nouri.sajadcourse.database

import academy.nouri.sajadcourse.R
import academy.nouri.sajadcourse.database.db.NoteDatabase
import academy.nouri.sajadcourse.database.db.NoteEntity
import academy.nouri.sajadcourse.databinding.ActivityAddNoteBinding
import academy.nouri.sajadcourse.databinding.ActivityUpdateNotesBinding
import academy.nouri.sajadcourse.utils.BUNDLE_NOTE_ID
import academy.nouri.sajadcourse.utils.NOTE_DATABASE
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class UpdateNotesActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityUpdateNotesBinding

    //Database
    private val noteDb: NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    //Other
    private lateinit var note: NoteEntity
    private var id = 0
    private var defaultTitle = ""
    private var defaultInfo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Intent
        intent.extras?.let {
            id = it.getInt(BUNDLE_NOTE_ID)
        }
        //InitViews
        binding.apply {
            //Default items
            defaultTitle = noteDb.noteDao().getNote(id).noteTitle
            defaultInfo = noteDb.noteDao().getNote(id).noteInfo
            titleEdt.setText(defaultTitle)
            infoEdt.setText(defaultInfo)
            //Delete
            deleteBtn.setOnClickListener {
                note = NoteEntity(id, defaultTitle, defaultInfo)
                noteDb.noteDao().deleteNote(note)
                finish()
            }
            //Update
            updateBtn.setOnClickListener {
                val title = titleEdt.text.toString()
                val info = infoEdt.text.toString()

                if (title.isNotEmpty() && info.isNotEmpty()) {
                    note = NoteEntity(id, title, info)
                    noteDb.noteDao().updateNote(note)
                    finish()
                } else {
                    Snackbar.make(it, "Title and Info cannot be empty!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }
}