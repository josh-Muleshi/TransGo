import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cd.transgo.transgo.MainViewModel
import cd.transgo.transgo.R
import cd.transgo.transgo.app.navigation.Screen
import cd.transgo.transgo.presentation.home.business.ConnectionState
import cd.transgo.transgo.presentation.home.business.HomeViewModel
import cd.transgo.transgo.ui.theme.Purple500
import kotlinx.coroutines.launch

@Composable
fun ToolbarWidget(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val state by homeViewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    TopAppBar(
        backgroundColor = Purple500,
        contentColor = White,
        elevation = 2.dp
    ){
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                color = White,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "auth",
                tint = White,
                modifier = Modifier
                    .padding(8.dp)
                    .size(30.dp)
                    .clickable {
                        coroutineScope.launch {
                            when (state) {
                                is ConnectionState.Success -> {
                                    if ((state as ConnectionState.Success).isAuth) {
                                        navController.navigate(Screen.Translator.route)
                                    } else {
                                        navController.navigate(Screen.Auth.route)
                                    }
                                }
                                is ConnectionState.Error -> {
                                    snackbarHostState.showSnackbar((state as ConnectionState.Error).errorMessage)
                                }
                                else -> {}
                            }
                        }
                    }
            )
        }
    }
}