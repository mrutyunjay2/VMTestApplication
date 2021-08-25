package mtj.example.vmapplication.UI.EmpDetailsModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mtj.example.vmapplication.data.remote.NetworkHelper
import mtj.example.vmapplication.data.repo.EmpDetailsRepository

class EmpDetailsViewModelFactory (
                                  private val empDetailsRepository: EmpDetailsRepository,
                                  private val networkHelper: NetworkHelper
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EmpDetailsViewModel(empDetailsRepository,networkHelper) as T
    }
}