package cd.transgo.transgo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Translate(
    val word: String?,
    val translate: String?,
    val date: String?,
    val id: Int?
): Parcelable