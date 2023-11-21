package cl.rogarciap.marketapp.share.response

sealed class BaseResponse<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Successful<T>(data: T?) : BaseResponse<T>(data)

    class Error<T>(message: String, data: T? = null) : BaseResponse<T>(data, message)

    class Loading<T>(data: T? = null) : BaseResponse<T>(data)
}
