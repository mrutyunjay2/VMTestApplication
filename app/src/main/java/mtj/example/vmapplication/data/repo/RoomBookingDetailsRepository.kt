package mtj.example.vmapplication.data.repo

import mtj.example.vmapplication.data.remote.NetworkService

class RoomBookingDetailsRepository (private val networkService: NetworkService){
    suspend fun getRoombookingDeatils()  = networkService.getRoombokingDetails()

}