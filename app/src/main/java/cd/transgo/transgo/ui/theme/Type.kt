package cd.transgo.transgo.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cd.transgo.transgo.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val FugazOne = FontFamily(
    Font(R.font.fugaz_one, FontWeight.Light),
    Font(R.font.fugaz_one, FontWeight.Normal),
    Font(R.font.fugaz_one, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.fugaz_one, FontWeight.Medium),
    Font(R.font.fugaz_one, FontWeight.Bold)
)