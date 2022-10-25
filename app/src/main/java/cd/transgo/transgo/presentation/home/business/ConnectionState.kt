package cd.transgo.transgo.presentation.home.business

sealed class ConnectionState {
    object Uninitialized : ConnectionState()
    object Loading : ConnectionState()
    data class Error(val errorMessage: String) : ConnectionState()
    data class Success(val isAuth: Boolean) : ConnectionState()
}