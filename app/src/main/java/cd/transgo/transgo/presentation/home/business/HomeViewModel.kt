package cd.transgo.transgo.presentation.home.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.joshMuleshi.ecodim.data.repository.ResultatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(/*private val resultatRepository: ResultatRepository*/) : ViewModel() {

    private val _data = MutableStateFlow<HomeState>(HomeState.Uninitialized)
    val data: StateFlow<HomeState>
        get() = _data

    init {
        getResult()
    }

    @ExperimentalCoroutinesApi
    private fun getResult() = viewModelScope.launch {
        _data.emit(HomeState.Loading)
        try {
            //resultatRepository.getResult().collect {
                //_data.emit(HomeState.Success(it))
            //}
        } catch (t: Throwable) {
            _data.emit(HomeState.Error(t.message.toString()))
        }
    }
}