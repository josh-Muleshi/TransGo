package cd.transgo.transgo.data.utils

import com.facebook.CallbackManager

object FacebookUtil {
    val callbackManager by lazy {
        CallbackManager.Factory.create()
    }
}