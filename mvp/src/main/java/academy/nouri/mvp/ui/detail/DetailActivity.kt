package academy.nouri.mvp.ui.detail

import academy.nouri.mvp.R
import academy.nouri.mvp.data.database.MovieDatabase
import academy.nouri.mvp.data.database.MoviesEntity
import academy.nouri.mvp.data.model.detail.ResponseMovieDetail
import academy.nouri.mvp.databinding.ActivityDetailBinding
import academy.nouri.mvp.ui.base.BaseActivity
import academy.nouri.mvp.ui.detail.adapters.ImagesAdapter
import academy.nouri.mvp.utils.MOVIES_DATABASE
import academy.nouri.mvp.utils.MOVIES_TABLE
import academy.nouri.mvp.utils.MOVIE_ID
import academy.nouri.mvp.utils.isNetworkAvailable
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import coil.load

class DetailActivity : BaseActivity(), DetailContracts.View {
    //Binding
    private lateinit var binding: ActivityDetailBinding

    //Other
    private var movieId = 0
    private val presenter by lazy { DetailPresenter(this) }
    private val imagesList: MutableList<String> = mutableListOf()
    private val imagesAdapter by lazy { ImagesAdapter(imagesList) }
    private lateinit var entity: MoviesEntity

    //Database
    private val movieDatabase: MovieDatabase by lazy {
        Room.databaseBuilder(this, MovieDatabase::class.java, MOVIES_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Get data
        intent.extras?.let {
            movieId = it.getInt(MOVIE_ID)
            //Call api
            if (movieId > 0) {
                presenter.callMovieDetail(movieId)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun loadMovieDetail(data: ResponseMovieDetail) {
        binding.apply {
            //Poster
            posterImg.load(data.poster) {
                crossfade(true)
                crossfade(800)
            }
            //Text
            movieTitle.text = data.title
            movieDesc.text = "${data.actors}\n\n${data.writer}\n\n${data.country}"
            //Load other images
            imagesList.clear()
            imagesList.addAll(data.images)
            imagesRecycler.apply {
                layoutManager = LinearLayoutManager(
                    this@DetailActivity, LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = imagesAdapter
            }
            //Show fav
            if (movieDatabase.dao().existsMovie(data.id)) {
                favImg.setImageResource(R.drawable.ic_round_favorite_24)
            } else {
                favImg.setImageResource(R.drawable.ic_round_favorite_border_24)
            }
            //Save
            favImg.setOnClickListener {
                if (movieDatabase.dao().existsMovie(data.id)) {
                    entity = MoviesEntity(data.id, data.poster, data.title)
                    movieDatabase.dao().deleteMovie(entity)
                    favImg.setImageResource(R.drawable.ic_round_favorite_border_24)
                } else {
                    entity = MoviesEntity(data.id, data.poster, data.title)
                    movieDatabase.dao().saveMovie(entity)
                    favImg.setImageResource(R.drawable.ic_round_favorite_24)
                }
            }
        }
    }

    override fun checkNetworkConnection(): Boolean {
        return isNetworkAvailable()
    }

    override fun networkConnectionError() {
        Toast.makeText(this, "اینترنت خود را چک کنید", Toast.LENGTH_SHORT).show()
    }

    override fun responseError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        binding.movieLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.movieLoading.visibility = View.GONE
    }
}