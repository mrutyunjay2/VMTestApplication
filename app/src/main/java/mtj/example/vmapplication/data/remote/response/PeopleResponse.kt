package mtj.example.vmapplication.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PeopleResponse(

    @Expose
    @SerializedName("id")
    var id: String,
    @Expose
    @SerializedName("avatar")
    var avatar: String,

    @Expose
    @SerializedName("phone")
    var phone: String,

    @Expose
    @SerializedName("firstName")
    var firstName: String,

    @Expose
    @SerializedName("lastName")
    var lastName: String,

    @Expose
    @SerializedName("latitude")
    var latitude: String,

    @Expose
    @SerializedName("longitude")
    var longitude: String,

    @Expose
    @SerializedName("email")
    var email: String,

    @Expose
    @SerializedName("favouriteColor")
    var favouriteColor: String,

    @Expose
    @SerializedName("jobTitle")
    var jobTitle: String,

    @Expose
    @SerializedName("createdAt")
    var createdAt: String,

    @Expose
    @SerializedName("statusCode")
    var statusCode: String,
)
