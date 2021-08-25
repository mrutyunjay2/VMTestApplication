package mtj.example.vmapplication.UI.EmpDetailsModule


import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import mtj.example.vmapplication.Base.BaseViewModel
import mtj.example.vmapplication.data.remote.NetworkHelper
import mtj.example.vmapplication.data.remote.response.PeopleResponse
import mtj.example.vmapplication.data.repo.EmpDetailsRepository

class EmpDetailsViewModel(private val empDetailsRepository: EmpDetailsRepository,
                          networkhelper :NetworkHelper): BaseViewModel(networkhelper){
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val errorMsg: MutableLiveData<String> = MutableLiveData()
    val EmpLiveData: MutableLiveData<List<PeopleResponse>> = MutableLiveData()
    var job: Job? = null
    fun getAllEmpDetails() {
        if(checkInternetConnection()) {
            job = CoroutineScope(Dispatchers.IO).launch {
                val response = empDetailsRepository.getAllEmps()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        loading.value = false
                        EmpLiveData.postValue(response.body())
                    } else {
                        onError("Error : ${response.message()} ")
                    }
                }
            }
        }
        else{
            errorMsg.value ="No Internet Connection..Please try after sometime.."
        }


    }
    private fun onError(message: String) {
        loading.value = false
        errorMsg.value = message

    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    override fun onCreate() {

    }
}