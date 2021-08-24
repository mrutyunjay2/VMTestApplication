package mtj.example.vmapplication

import android.app.Application
import mtj.example.vmapplication.data.remote.NetworkHelper
import mtj.example.vmapplication.data.remote.NetworkService
import mtj.example.vmapplication.data.remote.Networking

class VirginMoneyApp :Application() {
    lateinit var networkHelper: NetworkHelper
    lateinit var networkService: NetworkService
    override fun onCreate() {
        super.onCreate()
        networkService = Networking.create()
        networkHelper = applicationContext.let { NetworkHelper(it) }
    }

    fun checkInternet():Boolean= networkHelper.isNetworkConnected()


}