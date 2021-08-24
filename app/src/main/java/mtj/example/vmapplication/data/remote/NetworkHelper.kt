package mtj.example.vmapplication.data.remote

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException


class NetworkHelper constructor(private val context: Context) {

    companion object {
        private const val TAG = "NetworkHelper"
    }

    fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }

    fun castToNetworkError(throwable: Throwable): NetworkError {
        val defaultNetworkError = NetworkError()
        try {
            if (throwable is ConnectException) return NetworkError(0, "0")
            if (throwable !is HttpException) return defaultNetworkError
            val error = GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .fromJson(throwable.response()?.errorBody()?.string(), NetworkError::class.java)
            return NetworkError(throwable.code(), error.statusCode, error.message)
        } catch (e: IOException) {

        } catch (e: JsonSyntaxException) {

        } catch (e: NullPointerException) {

        }
        return defaultNetworkError
    }
}