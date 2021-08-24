package mtj.example.vmapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mtj.example.vmapplication.commmonUtils.Event

class HomeViewModel( ) : ViewModel() {

    val roomNavigation = MutableLiveData<Event<Boolean>>()
    val employeeNavigation = MutableLiveData<Event<Boolean>>()

    fun onEmployeeSelected() {
        employeeNavigation.postValue(Event(true))
    }

    fun onRoomBookSelected() {
        employeeNavigation.postValue(Event(true))
    }


}