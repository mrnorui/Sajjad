package academy.nouri.sajadcourse.database

import academy.nouri.sajadcourse.database.db.NoteDatabase
import academy.nouri.sajadcourse.database.db.NoteEntity
import academy.nouri.sajadcourse.databinding.ActivityRoomBinding
import academy.nouri.sajadcourse.utils.NOTE_DATABASE
import academy.nouri.sajadcourse.utils.initRecycler
import academy.nouri.sajadcourse.utils.showToast
import academy.nouri.sajadcourse.utils.showToast2
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Room
import io.github.inflationx.viewpump.ViewPumpContextWrapper


class RoomActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityRoomBinding

    //Database
    private val noteDb: NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    //Other
    private val noteAdapter by lazy { NotesAdapter(notesList) }
    private val notesList: MutableList<NoteEntity> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Click
            addNoteBtn.setOnClickListener {
                Intent(this@RoomActivity, AddNoteActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //Show items
        checkItem()
    }

    private fun checkItem() {
        binding.apply {
            if (noteDb.noteDao().getAllNotes().isNotEmpty()) {
                emptyText.visibility = View.GONE
                notesRecycler.visibility = View.VISIBLE

                setupRecycler()
            } else {
                emptyText.visibility = View.VISIBLE
                notesRecycler.visibility = View.GONE
            }
        }
    }

    private fun setupRecycler() {
        notesList.clear()
        notesList.addAll(noteDb.noteDao().getAllNotes())

        binding.notesRecycler.initRecycler(
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL),
            noteAdapter
        )
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }
}