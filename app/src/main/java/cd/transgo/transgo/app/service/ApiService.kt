package cd.transgo.transgo.app.service

import cd.transgo.transgo.data.model.Advice
import cd.transgo.transgo.data.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("advice/search/{source}")
    suspend fun getAdvice(@Path("source") source: String): Advice

}