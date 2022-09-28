import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cd.transgo.transgo.R
import cd.transgo.transgo.ui.theme.Purple500

@Composable
fun ToolbarWidget() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = White
            )
        },
        backgroundColor = Purple500,
        contentColor = White,
        elevation = 2.dp
    )
}