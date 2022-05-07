package academy.nouri.mvp.ui.main.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.nouri.mvp.R
import academy.nouri.mvp.data.database.MovieDatabase
import academy.nouri.mvp.data.database.MoviesEntity
import academy.nouri.mvp.databinding.FragmentFavoriteBinding
import academy.nouri.mvp.utils.MOVIES_DATABASE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room

class FavoriteFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentFavoriteBinding

    //Database
    private val movieDatabase: MovieDatabase by lazy {
        Room.databaseBuilder(requireContext(), MovieDatabase::class.java, MOVIES_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    //Other
    private val favoriteList: MutableList<MoviesEntity> = mutableListOf()
    private val favAdapter by lazy { FavoriteAdapter(favoriteList) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initViews
        binding.apply {
            //Get data
            if (movieDatabase.dao().getAllMovies().isNotEmpty()) {
                favoriteList.clear()
                favoriteList.addAll(movieDatabase.dao().getAllMovies())
                favoriteRecycler.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = favAdapter
                }
            }
        }
    }
}