package cd.transgo.transgo.presentation.home.business

sealed class HomeState {
    object Uninitialized: HomeState()
    object Loading : HomeState()
    data class Error(val message: String) : HomeState()
    data class Success(val message: String) : HomeState()
    //object Success/*(val student: Student?)*/ : HomeState()
}