package cd.transgo.transgo.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cd.transgo.transgo.app.navigation.Screen
import cd.transgo.transgo.presentation.splash.business.SplashViewModel
import cd.transgo.transgo.ui.theme.Back1
import cd.transgo.transgo.ui.theme.Back2
import cd.transgo.transgo.ui.theme.Back3
import cd.transgo.transgo.ui.theme.FugazOne
import kotlinx.coroutines.delay
import cd.transgo.transgo.R

@Composable
fun SplashScreen(navController: NavHostController, splashViewModel: SplashViewModel = hiltViewModel()) {

    val state by splashViewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state) {
        delay(3000)
        navController.navigate(Screen.Home.route)
    }

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        backgroundColor = Color.Transparent,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Back1,
                    Back2,
                    Back3
                ),
                startY = 100.0f,
                endY = 2100f
            )
        )
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            val offset = Offset(5.0f, 10.0f)
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FugazOne,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    shadow = Shadow(
                        color = Color.DarkGray,
                        offset = offset,
                        blurRadius = 0.1f
                    )
                )
            )

        }
    }
}