package mtj.example.vmapplication.UI.EmpDetailsModule

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.employee_details_list_layout.*
import kotlinx.android.synthetic.main.employee_details_list_layout.progressBar
import mtj.example.vmapplication.Base.BaseFragment
import mtj.example.vmapplication.R
import mtj.example.vmapplication.data.remote.NetworkHelper
import mtj.example.vmapplication.data.remote.NetworkService
import mtj.example.vmapplication.data.remote.Networking
import mtj.example.vmapplication.data.repo.EmpDetailsRepository

class EmpDetailsFragment():BaseFragment() {
    lateinit var empDetailsViewModel: EmpDetailsViewModel
    lateinit var networkService: NetworkService
    lateinit var networkHelper: NetworkHelper

    lateinit var empDetailsRepository: EmpDetailsRepository
    companion object{
        const val TAG = "EmpDetailsFragment"

        fun newInstance(): EmpDetailsFragment {
            val args = Bundle()
            val fragment = EmpDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun provideLayoutId(): Int = R.layout.employee_details_list_layout
    override fun setupView(view: View) {
       Log.d(TAG,"ViewCreated")
    }

    override fun setupObservers() {
        empDetailsViewModel.loading.observe(viewLifecycleOwner, {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        empDetailsViewModel.errorMsg.observe(viewLifecycleOwner, {
            val snack = Snackbar.make(empDetailsRoot,it,Snackbar.LENGTH_LONG)
            snack.show()
            errorMsg.visibility=View.VISIBLE
            errorMsg.text = it
        })
        empDetailsViewModel.EmpLiveData.observe(viewLifecycleOwner,{
            it?.run {
                Log.d("MainActivity", "EmpDetails2 : " + this)
                val adapter = EmpDetailsAdapter(this)
                val emprecycle = view?.findViewById<RecyclerView>(R.id.empRecyle)
                emprecycle?.layoutManager = LinearLayoutManager(activity)
                emprecycle?.adapter = adapter
            }
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        networkService = Networking.create()
        networkHelper = NetworkHelper(this.requireContext())
        empDetailsRepository = EmpDetailsRepository(networkService)
        empDetailsViewModel = ViewModelProvider(this,
            EmpDetailsViewModelFactory(empDetailsRepository,networkHelper)
        ).get(EmpDetailsViewModel::class.java)
        empDetailsViewModel.getAllEmpDetails()
        //getEmployeeDetails()
        setupObservers()
    }

}