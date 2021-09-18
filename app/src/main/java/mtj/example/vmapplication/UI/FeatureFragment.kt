package mtj.example.vmapplication.UI

import android.os.Bundle
import android.view.View
import mtj.example.vmapplication.Base.BaseFragment
import mtj.example.vmapplication.R
import mtj.example.vmapplication.UI.roomBookingModule.RoomFragment

class FeatureFragment : BaseFragment(){


    companion object{
        const val TAG = "FeatureFragment"

        fun newInstance(): FeatureFragment {
            val args = Bundle()
            val fragment = FeatureFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun provideLayoutId(): Int = R.layout.extralayout

    override fun setupView(view: View) {
        TODO("Not yet implemented")
    }

    override fun setupObservers() {
        TODO("Not yet implemented")
    }


}