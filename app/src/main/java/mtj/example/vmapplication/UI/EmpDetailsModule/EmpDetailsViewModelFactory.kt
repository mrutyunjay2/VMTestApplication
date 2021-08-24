package mtj.example.vmapplication.UI.EmpDetailsModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mtj.example.vmapplication.data.repo.EmpDetailsRepository

class EmpDetailsViewModelFactory (
                                  private val empDetailsRepository: EmpDetailsRepository
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EmpDetailsViewModel(empDetailsRepository) as T
    }
}