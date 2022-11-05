package cd.transgo.transgo.data.repository

import cd.transgo.transgo.app.service.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AdviceRepository @Inject constructor(
    private val service: ApiService
) {
    fun getAdvice(source: String) = flow {
        emit(service.getAdvice(source))
    }
}