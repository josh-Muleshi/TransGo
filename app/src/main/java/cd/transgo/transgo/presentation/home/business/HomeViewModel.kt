package cd.transgo.transgo.presentation.home.business

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.transgo.transgo.data.repository.AdviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val adviceRepository: AdviceRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _state = MutableStateFlow<ConnectionState>(ConnectionState.Uninitialized)
    val state: StateFlow<ConnectionState>
        get() = _state

    private val _data = MutableStateFlow<HomeState>(HomeState.Uninitialized)
    val data: StateFlow<HomeState>
        get() = _data

    init {
        viewModelScope.launch {
            _state.emit(ConnectionState.Loading)
            if (sharedPreferences.getBoolean("is-auth", false)) {
                _state.emit(ConnectionState.Success(true))
            } else {
                _state.emit(ConnectionState.Success(false))
            }
        }
    }

    fun translate(source: String) = viewModelScope.launch {
        _data.emit(HomeState.Loading)
        try {
            val advice = adviceRepository.getAdvice(source)
            _data.emit(HomeState.Success(advice))
        } catch (t: Throwable) {
            _data.emit(HomeState.Error(t.message.toString()))
        }
    }
}