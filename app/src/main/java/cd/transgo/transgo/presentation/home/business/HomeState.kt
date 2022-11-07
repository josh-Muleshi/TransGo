package cd.transgo.transgo.presentation.home.business

import cd.transgo.transgo.data.model.Advice
import cd.transgo.transgo.data.model.Translate

sealed class HomeState {
    object Uninitialized: HomeState()
    object Loading : HomeState()
    data class Error(val message: String) : HomeState()
    data class Success(val advice: Translate): HomeState()
    data class InvalidWord(val message: String) : HomeState()
}