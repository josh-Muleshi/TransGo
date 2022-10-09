package cd.transgo.transgo.presentation.home.business

import cd.transgo.transgo.data.model.Advice

sealed class HomeState {
    object Uninitialized: HomeState()
    object Loading : HomeState()
    data class Error(val message: String) : HomeState()
    data class Success(val message: String) : HomeState()
    data class Suc(val advice: Advice): HomeState()
    //object Success/*(val student: Student?)*/ : HomeState()
}