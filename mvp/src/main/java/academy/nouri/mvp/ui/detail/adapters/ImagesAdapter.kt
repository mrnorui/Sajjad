package academy.nouri.mvp.ui.detail.adapters

import academy.nouri.mvp.R
import academy.nouri.mvp.data.model.home.ResponseGenresList
import academy.nouri.mvp.databinding.ItemGenresBinding
import academy.nouri.mvp.databinding.ItemImagesBinding
import academy.nouri.mvp.ui.main.home.adapters.GenresAdapter
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class ImagesAdapter constructor(private val items: MutableList<String>) :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    private lateinit var binding: ItemImagesBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        binding = ItemImagesBinding.inflate(inflater, parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ImagesAdapter.ViewHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: String) {
            binding.apply {
                itemMovieImage.load(item) {
                    crossfade(true)
                    crossfade(500)
                }
                //Click
                root.setOnClickListener {
                    val dialog = Dialog(context)
                    dialog.setContentView(R.layout.dialog_images)

                    val image = dialog.findViewById<ImageView>(R.id.dialogImage)
                    image.load(item) {
                        crossfade(true)
                        crossfade(500)
                    }

                    dialog.show()
                }
            }
        }
    }
}