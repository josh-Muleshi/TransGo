package cd.transgo.transgo.presentation.translator.business

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cd.transgo.transgo.app.navigation.Screen
import cd.transgo.transgo.ui.theme.Purple500

@Composable
fun TopActionBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = "Traduire",
                color = Color.White, style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = {navController.navigate(Screen.Home.route)}) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        backgroundColor = Purple500,
        contentColor = Color.White,
        elevation = 2.dp
    )
}