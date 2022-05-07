package academy.nouri.sajadcourse.database

import academy.nouri.sajadcourse.R
import academy.nouri.sajadcourse.database.db.NoteDatabase
import academy.nouri.sajadcourse.database.db.NoteEntity
import academy.nouri.sajadcourse.databinding.ActivityAddNoteBinding
import academy.nouri.sajadcourse.databinding.ActivityRoomBinding
import academy.nouri.sajadcourse.utils.NOTE_DATABASE
import academy.nouri.sajadcourse.utils.moneySeparating
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class AddNoteActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityAddNoteBinding

    //Database
    private val noteDb: NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    //Other
    private lateinit var note: NoteEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Save
            saveBtn.setOnClickListener {
                val title = titleEdt.text.toString()
                val info = infoEdt.text.toString()

                if (title.isNotEmpty() && info.isNotEmpty()) {
                    note = NoteEntity(0, title, info)
                    noteDb.noteDao().insertNote(note)
                    finish()
                } else {
                    Snackbar.make(it, "Title and Info cannot be empty!", Snackbar.LENGTH_SHORT).show()
                }
            }

            textView.text = 154089000.moneySeparating()
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }
}