package cd.transgo.transgo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import cd.transgo.transgo.app.navigation.SetupNavGraph
import cd.transgo.transgo.data.utils.FacebookUtil
import cd.transgo.transgo.ui.theme.TransGoTheme
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        FacebookUtil.callbackManager.onActivityResult(requestCode, resultCode, data)
//        super.onActivityResult(requestCode, resultCode, data)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContent {
            TransGoTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SetupNavGraph(navController, viewModel = viewModel)
                }
            }
        }
    }
}