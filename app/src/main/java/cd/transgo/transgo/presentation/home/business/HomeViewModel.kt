package cd.transgo.transgo.presentation.home.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.transgo.transgo.data.model.Advice
import cd.transgo.transgo.data.repository.AdviceRepository
import cd.transgo.transgo.data.repository.TranslateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val adviceRepository: AdviceRepository
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Uninitialized)
    val state: StateFlow<HomeState>
        get() = _state

//    init {
//        viewModelScope.launch {
//            _state.emit(HomeState.Loading)
//            try {
//                val advice = adviceRepository.getAdvice()
//                _state.emit(HomeState.Suc(advice))
//            } catch (t: Throwable) {
//                _state.emit(HomeState.Error(t.message.toString()))
//            }
//        }
//    }

    fun translate(source: String) = viewModelScope.launch {
        _state.emit(HomeState.Loading)
        try {
            val advice = adviceRepository.getAdvice(source)
            _state.emit(HomeState.Suc(advice))
        } catch (t: Throwable) {
            _state.emit(HomeState.Error(t.message.toString()))
        }
    }
}