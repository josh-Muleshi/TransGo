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

    private val _state = MutableStateFlow<HomeState>(HomeState.Uninitialized)
    val state: StateFlow<HomeState>
        get() = _state

    private val repository = TranslateRepository(application)

    init {
        getTranslator()
    }

    @OptIn(FlowPreview::class)
    fun translate(source: String) = viewModelScope.launch {
        _data.emit(HomeState.Loading)
        try {
            repository.getTranslate(source).debounce(7000).collect { advice ->
                _data.emit(HomeState.Success(advice))
            }
        } catch (t: Throwable) {
            _data.emit(HomeState.Error(t.message.toString()))
        }
    }

    private fun getTranslator() = viewModelScope.launch {
        _state.emit(HomeState.Loading)
        try {
            repository.getForTranslator().collect { advice ->
                _state.emit(HomeState.Success(advice))
            }
        } catch (t: Throwable) {
            _state.emit(HomeState.Error(t.message.toString()))
        }
    }

    fun setTranslator(word: String) = viewModelScope.launch {
        _state.emit(HomeState.Loading)
        try {
            repository.setTranslate(word)
        } catch (t: Throwable) {
            _state.emit(HomeState.Error(t.message.toString()))
        }
    }
}