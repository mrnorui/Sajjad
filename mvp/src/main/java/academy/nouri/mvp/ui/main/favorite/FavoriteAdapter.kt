package academy.nouri.mvp.ui.main.favorite

import academy.nouri.mvp.data.database.MoviesEntity
import academy.nouri.mvp.data.model.home.ResponseMoviesList
import academy.nouri.mvp.databinding.ItemMoviesBinding
import academy.nouri.mvp.ui.detail.DetailActivity
import academy.nouri.mvp.ui.main.home.adapters.MoviesAdapter
import academy.nouri.mvp.utils.MOVIE_ID
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load

class FavoriteAdapter constructor(private val items: MutableList<MoviesEntity>) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var binding: ItemMoviesBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        binding = ItemMoviesBinding.inflate(inflater, parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: MoviesEntity) {
            binding.apply {
                //Coil
                moviePosterImg.load(item.image) {
                    crossfade(true)
                    crossfade(800)
                }
                //Title
                movieTitleTxt.text = item.name
                //Click
                root.setOnClickListener {
                    Intent(context, DetailActivity::class.java).also {
                        it.putExtra(MOVIE_ID, item.id)
                        context.startActivity(it)
                    }
                }
            }
        }
    }
}