package cd.transgo.transgo.presentation.home.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.transgo.transgo.data.repository.TranslateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val translateRepository: TranslateRepository) : ViewModel() {

    private val _data = MutableStateFlow<HomeState>(HomeState.Success("Traduction..."))
    val data: StateFlow<HomeState>
        get() = _data

    fun translate(source: String) = viewModelScope.launch {
        _data.emit(HomeState.Loading)
        try {
            val text = translateRepository.translate(source)
            _data.emit(HomeState.Success(text))
        } catch (t: Throwable) {
            _data.emit(HomeState.Error(t.message.toString()))
        }
    }
}