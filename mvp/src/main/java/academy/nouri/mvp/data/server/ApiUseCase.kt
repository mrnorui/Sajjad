package academy.nouri.mvp.data.server

import academy.nouri.mvp.data.model.detail.ResponseMovieDetail
import academy.nouri.mvp.data.model.home.ResponseGenresList
import academy.nouri.mvp.data.model.home.ResponseMoviesList
import academy.nouri.mvp.data.model.register.BodyRegister
import academy.nouri.mvp.data.model.register.ResponseRegister
import io.reactivex.Single
import retrofit2.Response

open class ApiUseCase(private val apiService: ApiService) {

    open fun getMoviesList(page: Int): Single<Response<ResponseMoviesList>> {
        return apiService.moviesList(page)
    }

    open fun getGenresList(): Single<Response<ResponseGenresList>> {
        return apiService.genresList()
    }

    open fun postRegisterUser(body: BodyRegister): Single<Response<ResponseRegister>> {
        return apiService.registerUser(body)
    }

    open fun getMovieDetail(id: Int): Single<Response<ResponseMovieDetail>> {
        return apiService.movieDetail(id)
    }
}