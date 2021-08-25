package mtj.example.vmapplication.Base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment :  Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BaseFragment","OnCreate")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("BaseFragment","onCreateView")
         return inflater.inflate(provideLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("BaseFragment","onViewCreated")
        setupView(view)
       //
    }
    @LayoutRes
    protected abstract fun provideLayoutId(): Int
    protected abstract fun setupView(view: View)
    protected abstract fun setupObservers()

    //fun showMessage(message: String) = context?.let { Toast.makeText(context,message,Toast.LENGTH_LONG) }

}