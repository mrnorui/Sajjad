package academy.nouri.mvp.ui.main.home.adapters

import academy.nouri.mvp.data.model.home.ResponseGenresList.ResponseGenresListItem
import academy.nouri.mvp.databinding.ItemGenresBinding
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GenresAdapter constructor(private val items: MutableList<ResponseGenresListItem?>) :
    RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    private lateinit var binding: ItemGenresBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        binding = ItemGenresBinding.inflate(inflater, parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: GenresAdapter.ViewHolder, position: Int) {
        holder.setData(items[position]!!)
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: ResponseGenresListItem) {
            binding.apply {
                genreTitleTxt.text = item.name
            }
        }
    }
}