package cd.transgo.transgo.presentation.auth

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cd.transgo.transgo.R
import cd.transgo.transgo.app.navigation.Screen
import cd.transgo.transgo.data.utils.FacebookUtil
import cd.transgo.transgo.presentation.auth.business.AuthState
import cd.transgo.transgo.presentation.auth.business.AuthViewModel
import cd.transgo.transgo.ui.theme.Back1
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun AuthScreen(navController: NavHostController, viewModel: AuthViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val token: String = context.getString(R.string.webclient_id)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(state) {
        when (state) {
            is AuthState.Success -> {
                navController.navigate(Screen.Translator.route)
            }
            is AuthState.Error -> {
                snackbarHostState.showSnackbar((state as AuthState.Error).errorMessage)
            }
            else -> {}
        }
    }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            viewModel.signWithGoogleCredential(credential)
        } catch (e: ApiException) {
            Log.w("TAG", "failed",e)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painterResource(id = R.drawable.ic_translate),"login", modifier = Modifier.size(140.dp))

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            value = email,
            onValueChange = { email = it },
            shape = RoundedCornerShape(16.dp),
            label = {
                Text(text = "Email")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            value = password,
            onValueChange = { password = it },
            shape = RoundedCornerShape(16.dp),
            visualTransformation = PasswordVisualTransformation('*'),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            label = {
                Text(text = "Password")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            enabled = email.isNotBlank() && password.isNotBlank() && state != AuthState.Loading,
            onClick = { viewModel.register(email = email.trim(), password = password.trim()) },
            contentPadding = PaddingValues(8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Back1,
                contentColor = Color.White
            ),
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Sing in", style = MaterialTheme.typography.button)
                Spacer(modifier = Modifier.width(4.dp))
                AnimatedVisibility(visible = state == AuthState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.Red)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),horizontalArrangement = Arrangement.SpaceEvenly) {
            Image(painterResource(id = R.drawable.fb_black),"login", modifier = Modifier
                .size(50.dp)
                .clickable {

                })
            Image(painterResource(id = R.drawable.gmail_black),"login", modifier = Modifier
                .size(53.dp)
                .clickable {
                    val gso = GoogleSignInOptions
                        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()

                    val googleSignInClient = GoogleSignIn.getClient(context, gso)
                    launcher.launch(googleSignInClient.signInIntent)
                })
        }

        CustomFacebookButton()
    }
}


@Composable
fun CustomFacebookButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onSuccess: (LoginResult) -> Unit,
    onCancel: () -> Unit,
    onError: (FacebookException?) -> Unit,
) {

    val callbackManager = FacebookUtil.callbackManager
    val loginText = stringResource(R.string.txt_connect_with_facebook)
    AndroidView(
        modifier = modifier.fillMaxWidth().height(50.dp),
        factory = ::LoginButton,
        update = { button ->
            button.setLoginText(loginText)
            //button.setPermissions("email")
            button.setReadPermissions("email")
            button.isEnabled = enabled

            button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    onSuccess(result)
                    //Timber.e("Login : ${result.accessToken}")
                    Log.e("Login : ", "${result.accessToken}")
                }

                override fun onCancel() {
                    onCancel()
                    //Timber.e("Login : On Cancel")
                    Log.e("Login : ", "On Cancel")
                }

                override fun onError(error: FacebookException?) {
                    onError(error)
                    //Timber.e("Login : ${error?.localizedMessage}")
                    Log.e("Login : ", "${error?.localizedMessage}")
                }
            })
        }
    )
}