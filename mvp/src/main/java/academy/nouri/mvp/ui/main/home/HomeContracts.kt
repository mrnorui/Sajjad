package academy.nouri.mvp.ui.main.home

import academy.nouri.mvp.data.model.home.ResponseGenresList
import academy.nouri.mvp.data.model.home.ResponseMoviesList
import academy.nouri.mvp.ui.base.BasePresenter
import academy.nouri.mvp.ui.base.BaseView

interface HomeContracts {
    interface View : BaseView {
        fun loadMoviesList(data: List<ResponseMoviesList.Data?>)
        fun loadGenresList(data: List<ResponseGenresList.ResponseGenresListItem?>)
    }

    interface Presenter : BasePresenter {
        fun callMoviesList(page: Int)
        fun callGenresList()
    }
}