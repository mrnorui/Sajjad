package academy.nouri.sajadcourse.database

import academy.nouri.sajadcourse.database.db.NoteEntity
import academy.nouri.sajadcourse.databinding.ItemNotesBinding
import academy.nouri.sajadcourse.utils.BUNDLE_NOTE_ID
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter constructor(private val items: MutableList<NoteEntity>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private lateinit var binding: ItemNotesBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        binding = ItemNotesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: ItemNotesBinding) : RecyclerView.ViewHolder(itemView.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: NoteEntity) {
            binding.apply {
                itemTitle.text = item.noteTitle
                itemDesc.text = item.noteInfo
                //Click
                root.setOnClickListener {
                    Intent(context, UpdateNotesActivity::class.java).also {
                        it.putExtra(BUNDLE_NOTE_ID, item.noteId)
                        context.startActivity(it)
                    }
                }
            }
        }
    }
}