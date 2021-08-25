package mtj.example.vmapplication.data.remote


import mtj.example.vmapplication.data.remote.response.PeopleResponse
import mtj.example.vmapplication.data.remote.response.RoomResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface NetworkService {

    @GET(Endpoints.PEOPLE)
    suspend fun getEmployeeDetails() : Response<List<PeopleResponse>>

    @GET(Endpoints.ROOMS)
    suspend  fun getRoombokingDetails(): Response<List<RoomResponse>>
}