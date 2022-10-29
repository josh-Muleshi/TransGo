package cd.transgo.transgo.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor(private val firebaseAuth: FirebaseAuth) {
    suspend fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun signWithGoogleCredential(credential: AuthCredential){
        firebaseAuth.signInWithCredential(credential).await()
    }
}