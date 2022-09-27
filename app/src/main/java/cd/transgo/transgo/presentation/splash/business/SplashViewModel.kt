package cd.transgo.transgo.presentation.splash.business

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _state = MutableStateFlow<SplashState>(SplashState.Uninitialized)
    val state: StateFlow<SplashState>
        get() = _state

    init {
        viewModelScope.launch {
            _state.emit(SplashState.Loading)
            if (sharedPreferences.getBoolean("is-auth", false)) {
                _state.emit(SplashState.Success(true))
            } else {
                _state.emit(SplashState.Success(false))
            }
        }
    }
}