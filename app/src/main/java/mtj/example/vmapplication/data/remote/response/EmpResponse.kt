package mtj.example.vmapplication.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmpResponse(

    var list: List<PeopleResponse>,
)