package academy.nouri.mvp.data.server

import academy.nouri.mvp.data.model.detail.ResponseMovieDetail
import academy.nouri.mvp.data.model.home.ResponseGenresList
import academy.nouri.mvp.data.model.home.ResponseMoviesList
import academy.nouri.mvp.data.model.register.BodyRegister
import academy.nouri.mvp.data.model.register.ResponseRegister
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("movies")
    fun moviesList(@Query("page") page: Int): Single<Response<ResponseMoviesList>>

    @GET("genres")
    fun genresList(): Single<Response<ResponseGenresList>>

    @POST("register")
    fun registerUser(@Body body: BodyRegister): Single<Response<ResponseRegister>>

    @GET("movies/{movie_id}")
    fun movieDetail(@Path("movie_id") id: Int): Single<Response<ResponseMovieDetail>>
}