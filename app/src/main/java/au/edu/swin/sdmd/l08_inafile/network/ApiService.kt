package au.edu.swin.sdmd.l08_inafile.network

import au.edu.swin.sdmd.l08_inafile.model.NumberFact
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "http://numbersapi.com/"

/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

/**
 * Retrofit service object for creating api calls
 */
interface ApiService {
    @GET("random/year?json")
    suspend fun getFacts(): NumberFact

    @GET("{n}/math?json")
    suspend fun getFact(@Path("n") n: Int): NumberFact
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object NumberApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}