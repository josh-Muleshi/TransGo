package cd.transgo.transgo.data.repository

import cd.transgo.transgo.app.service.ApiService
import cd.transgo.transgo.data.model.Advice
import javax.inject.Inject

class AdviceRepository @Inject constructor(
    private val service: ApiService
) {
    suspend fun getAdvice(source: String): Advice{
        return service.getAdvice(source)
    }
}