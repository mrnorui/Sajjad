package academy.nouri.mvp.ui.base

interface BaseView {

    fun checkNetworkConnection(): Boolean

    fun networkConnectionError()

    fun responseError(error: String)

    fun showLoading()

    fun hideLoading()

}