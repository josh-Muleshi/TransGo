package cd.transgo.transgo.presentation.translator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cd.transgo.transgo.presentation.translator.business.TopActionBar
import cd.transgo.transgo.ui.theme.*

@Composable
fun TranslatorScreen(navController: NavController) {
    var title by remember { mutableStateOf("") }
    val focusRequest = remember {
        FocusRequester()
    }
    Scaffold(
        topBar = {
            TopActionBar(navController)
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
                Text(
                    text = "Mbote na bino",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold
                )
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
                }
            }
        }
    }
}