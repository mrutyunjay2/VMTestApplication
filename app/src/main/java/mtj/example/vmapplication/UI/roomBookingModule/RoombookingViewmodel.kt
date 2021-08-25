package mtj.example.vmapplication.UI.roomBookingModule

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import mtj.example.vmapplication.Base.BaseViewModel
import mtj.example.vmapplication.data.remote.NetworkHelper
import mtj.example.vmapplication.data.remote.response.RoomResponse
import mtj.example.vmapplication.data.repo.RoomBookingDetailsRepository

class RoombookingViewmodel(
    private val roomBookingDetailsRepository: RoomBookingDetailsRepository
    ,networkHelper: NetworkHelper
):BaseViewModel(networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val errorMsg: MutableLiveData<String> = MutableLiveData()
    val roomLivedata: MutableLiveData<List<RoomResponse>> = MutableLiveData()
    private var job: Job? = null
        fun getRoombookDetails(){
            if(checkInternetConnection()) {
                loading.value = true
                job = CoroutineScope(Dispatchers.IO).launch {
                    val response = roomBookingDetailsRepository.getRoombookingDeatils()
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            roomLivedata.postValue(response.body())
                            loading.value = false
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
        errorMsg.value = message
        loading.value = false
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    override fun onCreate() {
        Log.d("RoomBookingViewmodel","OnCreateViewmodel")
    }

}