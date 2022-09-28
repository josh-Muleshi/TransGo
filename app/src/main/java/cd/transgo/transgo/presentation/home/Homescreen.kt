package cd.transgo.transgo.presentation.home

import ToolbarWidget
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import cd.transgo.transgo.ui.theme.BackGray

@Composable
fun Homescreen(navController: NavHostController) {

    val context = LocalContext.current
    //val posts by viewModel.data.collectAsState()

    BackHandler(enabled = true) {
        (context as? Activity)?.finish()
    }

    Scaffold(
        topBar = {
            ToolbarWidget()
        }
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(BackGray)
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                Text(text = "Hello world !")
            }
        }
    }
}