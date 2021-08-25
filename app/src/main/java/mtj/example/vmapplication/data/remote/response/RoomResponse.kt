package mtj.example.vmapplication.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoomResponse(

    @Expose
    @SerializedName("id")
    var id: String,
    /*@Expose
    @SerializedName("created_at")
    var created_at:DateTimeFormatter,*/
    @Expose
    @SerializedName("name")
    var name: String,

    @Expose
    @SerializedName("max_occupancy")
    var max_occupancy: Int,

    @Expose
    @SerializedName("is_occupied")
    var is_occupied: Boolean,

)
