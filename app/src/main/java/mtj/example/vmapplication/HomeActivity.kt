package mtj.example.vmapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_home.*
import mtj.example.vmapplication.UI.EmpDetailsModule.EmpDetailsFragment
import mtj.example.vmapplication.UI.roomBookingModule.RoomFragment


class HomeActivity : AppCompatActivity() {
    lateinit var homeViewModel: HomeViewModel
    private var activeFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
            homeViewModel.onEmployeeSelected()
            setObserver()
            navigationDetails()

    }

   private fun navigationDetails(){
        bottomNavigation.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.itemPeople ->{
                        homeViewModel.onEmployeeSelected()
                        true
                    }
                    R.id.itemBookRoom ->{
                        homeViewModel.onRoomBookSelected()
                        true
                    }
                    else ->false
                }
            }
        }

    }
       private fun setObserver(){
            homeViewModel.employeeNavigation.observe(this,{
                it.getIfNotHandled()?.run {
                    showEmployeeDetails()
                }
            })
            homeViewModel.roomNavigation.observe(this,{
                it.getIfNotHandled()?.run {
                    showRoomDetails()
                }
            })
        }
    private fun showEmployeeDetails() {
        if (activeFragment is EmpDetailsFragment) return
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(EmpDetailsFragment.TAG) as EmpDetailsFragment?

        if (fragment == null) {
            fragment = EmpDetailsFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, EmpDetailsFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }

    private fun showRoomDetails() {
        if (activeFragment is RoomFragment) return
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(RoomFragment.TAG) as RoomFragment?

        if (fragment == null) {
            fragment = RoomFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, RoomFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)
        fragmentTransaction.commit()
        activeFragment = fragment
    }
}