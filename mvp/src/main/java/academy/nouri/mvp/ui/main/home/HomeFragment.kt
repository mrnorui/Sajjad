package academy.nouri.mvp.ui.main.home

import academy.nouri.mvp.R
import academy.nouri.mvp.data.model.home.ResponseGenresList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.nouri.mvp.data.model.home.ResponseMoviesList
import academy.nouri.mvp.databinding.FragmentHomeBinding
import academy.nouri.mvp.ui.main.home.adapters.GenresAdapter
import academy.nouri.mvp.ui.main.home.adapters.MoviesAdapter
import academy.nouri.mvp.utils.isNetworkAvailable
import android.app.Dialog
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

class HomeFragment : Fragment(), HomeContracts.View {
    //Binding
    private lateinit var binding: FragmentHomeBinding

    //Other
    private val presenter by lazy { HomePresenter(this) }
    private val movieAdapter by lazy { MoviesAdapter(moviesList) }
    private val moviesList: MutableList<ResponseMoviesList.Data?> = mutableListOf()
    private val genresAdapter by lazy { GenresAdapter(genresList) }
    private val genresList: MutableList<ResponseGenresList.ResponseGenresListItem?> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Call api
        presenter.callMoviesList(2)
        presenter.callGenresList()
    }

    override fun loadMoviesList(data: List<ResponseMoviesList.Data?>) {
        //InitViews
        binding.apply {
            moviesList.clear()
            moviesList.addAll(data)

            homeMoviesList.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = movieAdapter
            }
        }
    }

    override fun loadGenresList(data: List<ResponseGenresList.ResponseGenresListItem?>) {
        //InitViews
        binding.apply {
            genresList.clear()
            genresList.addAll(data)

            homeGenresList.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = genresAdapter
            }
        }
    }

    override fun checkNetworkConnection(): Boolean {
        return requireContext().isNetworkAvailable()
    }

    override fun networkConnectionError() {
        Toast.makeText(requireContext(), "اینترنت خود را چک کنید", Toast.LENGTH_SHORT).show()
    }

    override fun responseError(error: String) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_base)

        val title = dialog.findViewById<TextView>(R.id.dialogTitle)
        val retry = dialog.findViewById<Button>(R.id.dialogRetry)

        title.text = error
        retry.setOnClickListener {
            presenter.callMoviesList(2)
            presenter.callGenresList()

            dialog.dismiss()
        }

        dialog.show()
    }

    override fun showLoading() {
        binding.homeMovieLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.homeMovieLoading.visibility = View.GONE
    }
}