package mtj.example.vmapplication.UI.EmpDetailsModule


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import mtj.example.vmapplication.commmonUtils.Resource
import mtj.example.vmapplication.data.remote.NetworkHelper
import mtj.example.vmapplication.data.remote.NetworkService
import mtj.example.vmapplication.data.remote.response.PeopleResponse
import mtj.example.vmapplication.data.repo.EmpDetailsRepository

class EmpDetailsViewModel(
                          private val empDetailsRepository: EmpDetailsRepository): ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val errorMsg: MutableLiveData<String> = MutableLiveData()

    fun getEmpDetails(): MutableLiveData<List<PeopleResponse>>? {


           loading.postValue(empDetailsRepository.loading)
           empDetailsRepository.fetchEmpDetails_demo()
           if (empDetailsRepository.error != null) {
               loading.postValue(false)
               errorMsg.postValue("Something went Wrong....")
           }

       return empDetailsRepository.EmpDetailsData
    }


}