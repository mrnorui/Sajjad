package academy.nouri.sajadcourse

import academy.nouri.sajadcourse.databinding.ItemHousesBinding
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class HousesAdapter constructor(private val items: MutableList<HousesModel>) :
    RecyclerView.Adapter<HousesAdapter.ViewHolder>() {

    private lateinit var binding: ItemHousesBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HousesAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        binding = ItemHousesBinding.inflate(inflater, parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: HousesAdapter.ViewHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: HousesModel) {
            binding.apply {
                itemImg.setImageResource(item.img)
                itemPriceTxt.text = "${item.price}$"
                itemAddressTxt.text = item.address

                var count = 0
                itemPlus.setOnClickListener {
                    count += 1
                    itemCount.text = count.toString()
                }
                itemMinus.setOnClickListener {
                    count -= 1
                    itemCount.text = count.toString()
                }
                //Click
                root.setOnClickListener {
                    Toast.makeText(context, adapterPosition.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}