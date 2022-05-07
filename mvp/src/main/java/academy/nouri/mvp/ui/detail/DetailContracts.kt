package academy.nouri.mvp.ui.detail

import academy.nouri.mvp.data.model.detail.ResponseMovieDetail
import academy.nouri.mvp.ui.base.BasePresenter
import academy.nouri.mvp.ui.base.BaseView

interface DetailContracts {

    interface View : BaseView {
        fun loadMovieDetail(data: ResponseMovieDetail)
    }

    interface Presenter : BasePresenter {
        fun callMovieDetail(id: Int)
    }
}