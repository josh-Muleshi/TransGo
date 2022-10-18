package cd.transgo.transgo.presentation.home

import ToolbarWidget
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cd.transgo.transgo.R
import cd.transgo.transgo.presentation.home.business.HomeState
import cd.transgo.transgo.presentation.home.business.HomeViewModel
import cd.transgo.transgo.ui.theme.*

@Composable
fun HomeScreen(navController: NavController ,homeViewModel: HomeViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val state by homeViewModel.state.collectAsState()
    var isClicked by remember {
        mutableStateOf(false)
    }

    var title by remember { mutableStateOf("") }
    var translatetxt by remember { mutableStateOf( "") }
    val focusRequest = remember {
        FocusRequester()
    }

    BackHandler(enabled = true) {
        (context as? Activity)?.finish()
    }

    Scaffold(
        topBar = {
            ToolbarWidget(navController)
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Purple500,
                onClick = {
                    homeViewModel.translate(title)
                }
            ) {
                Icon(painterResource(id = R.drawable.ic_translate),"login", tint = Color.White)
            }
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
                            Text(text = if (isClicked) "Français" else "Lingala")
                            Icon(painter = painterResource(id = R.drawable.ic_sync_alt), contentDescription = "", modifier = Modifier
                                .padding(horizontal = 40.dp)
                                .clickable { isClicked = !isClicked })
                            Text(text = if (isClicked) "Lingala" else "Français")
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(15.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(Black_ic)
                ) {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholder = { Text("Saisissez ici...") },
                        trailingIcon = {
                            if (title.isNotEmpty()) {
                                IconButton(modifier = Modifier.align(Alignment.TopEnd),onClick = { title = "" }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Close,
                                        contentDescription = null
                                    )
                                }
                            }
                        },
                        textStyle = TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize, fontWeight = FontWeight.Medium),
                        singleLine = false,
                        modifier = Modifier
                            .padding(horizontal = 0.dp, vertical = 0.dp)
                            .fillMaxSize()
                            .focusRequester(focusRequest),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Trans,
                            focusedIndicatorColor = Trans,
                            unfocusedIndicatorColor = Trans
                        )
                    )
                }

                if(state is HomeState.Success && title != ""){
                    (state as HomeState.Success).advice.slips?.get(0)?.let { message ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(15.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Back1,
                                            Back2
                                        )
                                    )
                                )
                        ) {
                            val customTextSelectionColors = TextSelectionColors(
                                handleColor = Color.White,
                                backgroundColor = Color.White.copy(alpha = 0.6f)
                            )

                            CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                                TextField(
                                    value = if (message.advice?.isNotEmpty() == true) message.advice else translatetxt,
                                    onValueChange = { translatetxt = it },
                                    textStyle = TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize, fontWeight = FontWeight.Medium),
                                    singleLine = false,
                                    readOnly = true,
                                    modifier = Modifier
                                        .padding(horizontal = 0.dp, vertical = 0.dp)
                                        .fillMaxSize()
                                        .focusRequester(focusRequest),
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = Trans,
                                        focusedIndicatorColor = Trans,
                                        unfocusedIndicatorColor = Trans
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}