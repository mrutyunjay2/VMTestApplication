package mtj.example.vmapplication.UI.roomBookingModule

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.room_details_list_layout.*
import mtj.example.vmapplication.Base.BaseFragment
import mtj.example.vmapplication.R
import mtj.example.vmapplication.data.remote.NetworkHelper
import mtj.example.vmapplication.data.remote.NetworkService
import mtj.example.vmapplication.data.remote.Networking
import mtj.example.vmapplication.data.repo.RoomBookingDetailsRepository

class RoomFragment : BaseFragment() {
    lateinit var roombookingViewmodel: RoombookingViewmodel
    lateinit var networkService: NetworkService
    lateinit var networkHelper:NetworkHelper
    lateinit var roomBookingDetailsRepository: RoomBookingDetailsRepository
    companion object{
        const val TAG = "RoomFragment"

        fun newInstance(): RoomFragment {
            val args = Bundle()
            val fragment = RoomFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun provideLayoutId(): Int = R.layout.room_details_list_layout
    override fun setupView(view: View) {
        Log.d("RoomFragment","setupView")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("RoomFragment","onActivityCreated")
        networkHelper = NetworkHelper(this.requireContext())
        networkService = Networking.create()
        roomBookingDetailsRepository = RoomBookingDetailsRepository(networkService)
        roombookingViewmodel = ViewModelProvider(this,
            RoomDetailsViewModelFactory(roomBookingDetailsRepository,networkHelper)).get(RoombookingViewmodel::class.java)
        roombookingViewmodel.getRoombookDetails()
        setupObservers()
    }
    override fun setupObservers() {
        roombookingViewmodel.loading.observe(viewLifecycleOwner, Observer {
            room_progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        roombookingViewmodel.errorMsg.observe(viewLifecycleOwner, Observer {
            val snack = Snackbar.make(roomRoot,it, Snackbar.LENGTH_LONG)
            snack.show()
            room_errorMsg.visibility = View.VISIBLE
            room_errorMsg.text = it

        })
        roombookingViewmodel.roomLivedata.observe(viewLifecycleOwner, Observer {
            it?.run {
                Log.d(TAG, "RoomDetails : " + this)
                 val adapter = RoomBookingAdapter(this)
                val recyle = view?.findViewById<RecyclerView>(R.id.roomRecyle)
                Log.d("RoomFragment","data"+recyle)
                recyle?.layoutManager = LinearLayoutManager(activity)
                recyle?.adapter = adapter
            }
        })

    }
}