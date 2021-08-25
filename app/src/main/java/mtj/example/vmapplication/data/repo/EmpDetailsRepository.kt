package mtj.example.vmapplication.data.repo

import androidx.lifecycle.MutableLiveData
import mtj.example.vmapplication.data.remote.NetworkService
import mtj.example.vmapplication.data.remote.response.PeopleResponse

class EmpDetailsRepository (private val networkService: NetworkService){

    suspend fun getAllEmps() = networkService.getEmployeeDetails()

}