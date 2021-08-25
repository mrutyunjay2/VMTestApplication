package mtj.example.vmapplication.UI.roomBookingModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mtj.example.vmapplication.data.remote.NetworkHelper
import mtj.example.vmapplication.data.repo.RoomBookingDetailsRepository

class RoomDetailsViewModelFactory (private val roomBookingDetailsRepository: RoomBookingDetailsRepository,
                                    private val networkHelper: NetworkHelper
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RoombookingViewmodel(roomBookingDetailsRepository,networkHelper) as T
    }
}