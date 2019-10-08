package kz.myfood.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class StatisticsInfo(
    @SerializedName("number") val numberOfExcellentPerfomances: Int?,
    @SerializedName("field_type") val field_type: String?
) : Parcelable