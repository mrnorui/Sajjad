package academy.nouri.mvp.data.model.base

import com.google.gson.annotations.SerializedName

data class ResponseError(
    @SerializedName("errors")
    val errors: String?,
    @SerializedName("message")
    val message: String?
)
