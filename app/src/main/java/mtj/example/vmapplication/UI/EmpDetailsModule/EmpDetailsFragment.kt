package mtj.example.vmapplication.UI.EmpDetailsModule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.employee_details_list_layout.*
import mtj.example.vmapplication.R
import mtj.example.vmapplication.data.remote.NetworkHelper
import mtj.example.vmapplication.data.remote.NetworkService
import mtj.example.vmapplication.data.remote.Networking
import mtj.example.vmapplication.data.repo.EmpDetailsRepository

class EmpDetailsFragment():Fragment() {
    lateinit var empDetailsViewModel: EmpDetailsViewModel;
    lateinit var networkHelper: NetworkHelper
    lateinit var networkService: NetworkService
    lateinit var empDetailsRepository: EmpDetailsRepository
    companion object{
        const val TAG = "EmployeeFragment"

        fun newInstance(): EmpDetailsFragment {
            val args = Bundle()
            val fragment = EmpDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(
            R.layout.employee_details_list_layout,
            container, false)
        Log.d(TAG,"onCreateView")
        val recyclerview =view.findViewById<RecyclerView>(R.id.empRecyle)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        networkService = Networking.create()
        empDetailsRepository = EmpDetailsRepository(networkService)
        empDetailsViewModel = ViewModelProvider(this,
            EmpDetailsViewModelFactory(empDetailsRepository)
        ).get(EmpDetailsViewModel::class.java)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getEmployeeDetails()


    }

    fun getEmployeeDetails(){
        empDetailsViewModel.loading.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        empDetailsViewModel.errorMsg.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
        empDetailsViewModel.getEmpDetails()?.observe(viewLifecycleOwner, Observer {
            it?.run {
                Log.d("MainActivity", "EmpDetails2 : " + this)
                val adapter = EmpDetailsAdapter(this)
                empRecyle.layoutManager
                empRecyle.adapter = adapter
            }
        })

    }



}