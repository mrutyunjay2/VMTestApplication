package mtj.example.vmapplication.Base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mtj.example.vmapplication.data.remote.NetworkHelper

abstract class BaseViewModel(private val networkHelper: NetworkHelper) :ViewModel() {

    val messageString: MutableLiveData<String> = MutableLiveData()
    override fun onCleared() {
        super.onCleared()
    }


    protected fun checkInternetConnection(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageString.postValue("No Internet connection..Please try again..")
            false
        }

    abstract fun onCreate()
}

