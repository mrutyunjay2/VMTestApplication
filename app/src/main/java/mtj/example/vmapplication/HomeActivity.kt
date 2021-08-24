package mtj.example.vmapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import mtj.example.vmapplication.UI.EmpDetailsModule.EmpDetailsFragment


class HomeActivity : AppCompatActivity() {
    lateinit var homeViewModel: HomeViewModel
    private var activeFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        if((application as VirginMoneyApp).checkInternet()) {
            homeViewModel.onEmployeeSelected()
            setObserver()
            navigationDetails()
        }else {
            val snack = Snackbar.make(rootLayout,"No internet connection...Please try again",Snackbar.LENGTH_LONG)
            snack.show()
        }

    }

    fun navigationDetails(){
        bottomNavigation.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.itemPeople ->{
                        homeViewModel.onEmployeeSelected()
                        true
                    }
                    R.id.bottomNavigation ->{
                        homeViewModel.onRoomBookSelected()
                        true
                    }
                    else ->false
                }
            }
        }

    }
        fun setObserver(){
            homeViewModel.employeeNavigation.observe(this, Observer {
                it.getIfNotHandled()?.run {
                    showEmployeeDetails()
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
}