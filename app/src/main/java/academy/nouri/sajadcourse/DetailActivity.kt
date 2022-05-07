package academy.nouri.sajadcourse

import academy.nouri.sajadcourse.databinding.ActivityDetailBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class DetailActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityDetailBinding

    //Other
    private val housesList: MutableList<HousesModel> = mutableListOf()
    private val housesAdapter: HousesAdapter by lazy { HousesAdapter(housesList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Load data
        fakeLoadData()
        //InitViews
        binding.apply {
            myRecycler.apply {
                layoutManager = LinearLayoutManager(this@DetailActivity)

                adapter = housesAdapter
            }
        }
    }

    private fun fakeLoadData() {
        housesList.add(HousesModel(R.drawable.house1, 100000, "Tehran"))
        housesList.add(HousesModel(R.drawable.house2, 40000, "Shiraz"))
        housesList.add(HousesModel(R.drawable.house3, 675000, "Esfahan"))
        housesList.add(HousesModel(R.drawable.house4, 785000, "Tabriz"))
        housesList.add(HousesModel(R.drawable.house5, 320000, "Mashhad"))
        housesList.add(HousesModel(R.drawable.house3, 700000, "Astara"))
    }
}