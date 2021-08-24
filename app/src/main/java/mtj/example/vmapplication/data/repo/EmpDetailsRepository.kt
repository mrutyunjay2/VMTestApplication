package mtj.example.vmapplication.data.repo

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mtj.example.vmapplication.commmonUtils.Resource
import mtj.example.vmapplication.data.remote.NetworkService
import mtj.example.vmapplication.data.remote.response.PeopleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmpDetailsRepository (private val networkService: NetworkService){

    var EmpDetailsData: MutableLiveData<List<PeopleResponse>>?= MutableLiveData()
    var error: String? = null
    var loading:Boolean=true

    fun fetchEmpDetails_demo() {
        loading = true
        val call: Call<List<PeopleResponse>> =networkService.getEmployeeDetails();
        call.enqueue(object :Callback<List<PeopleResponse>>{
            override fun onFailure(call: Call<List<PeopleResponse>>, t: Throwable) {
                Log.d("MainActivity","EmpDetails : "+t)
                error = t.toString()
            }
            override fun onResponse(call: Call<List<PeopleResponse>>,
                response: Response<List<PeopleResponse>>) {
                loading = false
                if (response.isSuccessful){
                    EmpDetailsData?.postValue(response.body())
                }
                else {
                    error = response.message()
                }
            }

        })

    }

}