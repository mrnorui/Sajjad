package academy.nouri.mvp.ui.base

import io.reactivex.disposables.Disposable

open class BasePresenterImpl : BasePresenter {
    var disposable: Disposable? = null

    override fun onStop() {
        disposable?.let {
            it.dispose()
        }
    }

}