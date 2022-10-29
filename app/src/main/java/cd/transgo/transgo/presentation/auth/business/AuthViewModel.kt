package cd.transgo.transgo.presentation.auth.business

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.transgo.transgo.data.repository.UserRepository
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Uninitialized)
    val state: StateFlow<AuthState>
        get() = _state

    fun register(email: String, password: String) = viewModelScope.launch {
        _state.emit(AuthState.Loading)
        try {
            userRepository.register(email,password)
            val editor = sharedPreferences.edit()
            editor.apply {
                putBoolean("is-auth", true)
            }.apply()
            _state.emit(AuthState.Success)
        } catch (e: Exception) {
            _state.emit(AuthState.Error(e.localizedMessage ?: e.message.toString()))
        }
    }

    fun signWithGoogleCredential(credential: AuthCredential) = viewModelScope.launch {
        try {
            _state.emit(AuthState.Loading)
            userRepository.signWithGoogleCredential(credential)
            val editor = sharedPreferences.edit()
            editor.apply {
                putBoolean("is-auth", true)
            }.apply()
            _state.emit(AuthState.Success)
        } catch (e: Exception) {
            _state.emit(AuthState.Error(e.localizedMessage ?: e.message.toString()))
        }
    }
}