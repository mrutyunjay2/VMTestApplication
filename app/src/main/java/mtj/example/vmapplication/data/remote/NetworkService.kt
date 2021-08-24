package mtj.example.vmapplication.data.remote


import androidx.lifecycle.LiveData
import mtj.example.vmapplication.data.remote.response.EmpResponse
import mtj.example.vmapplication.data.remote.response.PeopleResponse
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @GET(Endpoints.PEOPLE)
    fun getEmployeeDetails(): Call<List<PeopleResponse>>
}