package academy.nouri.mvp.ui.detail

import academy.nouri.mvp.data.model.register.BodyRegister
import academy.nouri.mvp.data.server.ApiClient
import academy.nouri.mvp.ui.base.BasePresenterImpl
import academy.nouri.mvp.ui.register.RegisterContracts
import academy.nouri.mvp.utils.applyIoScheduler

class DetailPresenter constructor(private val view: DetailContracts.View) :
    DetailContracts.Presenter, BasePresenterImpl() {

    override fun callMovieDetail(id: Int) {
        if (view.checkNetworkConnection()) {
            view.showLoading()
            disposable = ApiClient.getInstance().apiUseCase()
                .getMovieDetail(id)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoading()
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let { itBody ->
                                view.loadMovieDetail(itBody)
                            }
                        }
                        in 400..499 -> {
                            view.responseError("پیام مربوط به ارورهای 400")
                        }
                        in 500..599 -> {
                            view.responseError("خطایی پیش آمده، مجددا تلاش نمایید.")
                        }
                    }

                }, { error ->
                    view.hideLoading()
                    view.responseError(error.message.toString())
                })
        } else {
            view.networkConnectionError()
        }
    }
}