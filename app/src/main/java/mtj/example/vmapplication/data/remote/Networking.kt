package mtj.example.vmapplication.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Networking {
    private const val NETWORK_CALL_TIMEOUT = 60

    fun create():NetworkService{
            return Retrofit.Builder()
                .baseUrl("https://5f7c2c8400bd74001690a583.mockapi.io/api/v1/")
                .client(OkHttpClient.Builder()
                    .readTimeout(NETWORK_CALL_TIMEOUT.toLong(),TimeUnit.SECONDS)
                    .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(),TimeUnit.SECONDS)
                .build())
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(NetworkService::class.java)
    }

}