package cd.transgo.transgo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Slip(
    val advice: String?,
    val date: String?,
    val id: Int?
) : Parcelable {
    fun toTranslate(): Translate {
        return Translate(
            word = null,
            translate = advice,
            date = date,
            id = id
        )
    }
}

fun List<Slip>.toJobs() : List<Translate> = this.map { it.toTranslate() }