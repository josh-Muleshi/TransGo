package cd.transgo.transgo

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cd.transgo.transgo.data.repository.TranslateRepository
import cd.transgo.transgo.presentation.home.business.HomeState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _data = MutableStateFlow<HomeState>(HomeState.Uninitialized)
    val data: StateFlow<HomeState>
        get() = _data

    private val repository = TranslateRepository(application)

    @OptIn(FlowPreview::class)
    fun translate(source: String) = viewModelScope.launch {
        _data.emit(HomeState.Loading)
        try {
            repository.getTranslate(source).debounce(7000).collect { advice ->
                Log.e("see", advice.translate.toString())
                _data.emit(HomeState.Success(advice))
            }
        } catch (t: Throwable) {
            Log.e("see", "faild : ${t.message.toString()}")
            _data.emit(HomeState.Error(t.message.toString()))
        }
    }
}