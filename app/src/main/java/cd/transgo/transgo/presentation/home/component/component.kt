import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cd.transgo.transgo.R
import cd.transgo.transgo.app.navigation.Screen
import cd.transgo.transgo.ui.theme.Purple500

@Composable
fun ToolbarWidget(navController: NavController) {
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
            Image(
                imageVector = Icons.Default.Add,
                contentDescription = "settings",
                modifier = Modifier
                    .size(30.dp)
                    .clickable { navController.navigate(Screen.Translator.route) }
            )
        }
    }
}