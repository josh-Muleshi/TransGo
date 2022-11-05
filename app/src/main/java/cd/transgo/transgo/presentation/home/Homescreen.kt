package cd.transgo.transgo.presentation.home

import ToolbarWidget
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cd.transgo.transgo.R
import cd.transgo.transgo.presentation.home.business.HomeState
import cd.transgo.transgo.presentation.home.business.HomeViewModel
import cd.transgo.transgo.ui.theme.*

@SuppressLint("ServiceCast")
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val data by homeViewModel.data.collectAsState()
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
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

    LaunchedEffect(title){
        homeViewModel.translate(title)
    }

    Scaffold(
        topBar = {
            ToolbarWidget(navController, homeViewModel)
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

                    if (title.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(16.dp)
                                .clickable { title = "" }
                        )
                    }
                }

                if(data is HomeState.Success  && title != "" ){
                    (data as HomeState.Success).advice.slips?.get(0)?.let { message ->
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

                            Row(modifier = Modifier
                                .align(Alignment.BottomEnd)) {
                                Icon(
                                    imageVector = Icons.Outlined.Share,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(
                                            top = 16.dp,
                                            end = 8.dp,
                                            bottom = 16.dp,
                                            start = 16.dp
                                        )
                                        .clickable {
                                            val sendIntent = Intent()
                                            sendIntent.action = Intent.ACTION_SEND
                                            sendIntent.putExtra(
                                                Intent.EXTRA_TEXT,
                                                message.advice.toString()
                                            )
                                            sendIntent.type = "text/plain"
                                            startActivity(context, sendIntent, null)
                                        }
                                )

                                Icon(
                                    painter = painterResource(id = R.drawable.ic_copy),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(
                                            top = 16.dp,
                                            end = 16.dp,
                                            bottom = 16.dp,
                                            start = 8.dp
                                        )
                                        .clickable {
                                            clipboardManager.setText(AnnotatedString((message.advice.toString())))
                                            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2)
                                                Toast
                                                    .makeText(context, "Copied", Toast.LENGTH_SHORT)
                                                    .show()
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


