package cd.transgo.transgo.presentation.home

import ToolbarWidget
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cd.transgo.transgo.R
import cd.transgo.transgo.ui.theme.BackGray

@Composable
fun Homescreen(navController: NavHostController) {

    val context = LocalContext.current
    //val posts by viewModel.data.collectAsState()
    var isClicked by remember {
        mutableStateOf(false)
    }

    var title by remember { mutableStateOf("") }
    val focusRequest = remember {
        FocusRequester()
    }

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
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .background(BackGray)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    elevation = 1.dp
                ) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Row(
                            modifier = Modifier
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = if (isClicked) "Lingala" else "Français")
                            Icon(painter = painterResource(id = R.drawable.ic_sync_alt), contentDescription = "", modifier = Modifier
                                .padding(horizontal = 40.dp)
                                .clickable { isClicked = !isClicked })
                            Text(text = if (isClicked) "Français" else "Lingala")
                        }
                    }
                }
                
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("Saisissez ici...") },
                    textStyle = TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize, fontWeight = FontWeight.Medium),
                    singleLine = false,
                    modifier = Modifier
                        .padding(horizontal = 0.dp, vertical = 0.dp)
                        .fillMaxWidth()
                        .focusRequester(focusRequest),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BackGray,
                        focusedIndicatorColor = BackGray,
                        unfocusedIndicatorColor = BackGray
                    )
                )

                Spacer(modifier = Modifier.padding(24.dp))


                
                Text(
                    text = if (title.isNotEmpty()) title else "Traduction...",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                )
            }
        }
    }
}