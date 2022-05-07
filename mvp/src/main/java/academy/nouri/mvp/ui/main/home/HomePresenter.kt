package academy.nouri.mvp.ui.main.home

import academy.nouri.mvp.data.server.ApiClient
import academy.nouri.mvp.ui.base.BasePresenterImpl
import academy.nouri.mvp.utils.applyIoScheduler
import android.widget.Toast

class HomePresenter constructor(private val view: HomeContracts.View) : HomeContracts.Presenter, BasePresenterImpl() {

    override fun callMoviesList(page: Int) {
        if (view.checkNetworkConnection()) {
            view.showLoading()
            disposable = ApiClient.getInstance().apiUseCase()
                .getMoviesList(page)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoading()
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let { itBody ->
                                itBody.data?.let { itData ->
                                    if (itData.isNotEmpty()) {
                                        view.loadMoviesList(itData)
                                    }
                                }
                            }
                        }
                        422 -> {
                            //view.
                        }
                        401 -> {

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

    override fun callGenresList() {
        if (view.checkNetworkConnection()) {
            view.showLoading()
            disposable = ApiClient.getInstance().apiUseCase()
                .getGenresList()
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoading()
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let { itBody ->
                                view.loadGenresList(itBody)
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