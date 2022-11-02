package cd.transgo.transgo.data.repository

import android.util.Log
import cd.transgo.transgo.app.service.ApiService
import cd.transgo.transgo.data.model.Advice
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AdviceRepository @Inject constructor(
    private val service: ApiService
) {
    fun getAdvice(source: String) = flow {
        emit(service.getAdvice(source))
    }
}