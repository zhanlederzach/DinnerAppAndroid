package kz.myfood.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*
import kz.myfood.R
import kz.myfood.ui.login.AuthViewModel
import kz.myfood.utils.FragmentNavigator
import kz.myfood.utils.isFragmentAddedAndVisible
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val viewModel: AuthViewModel by inject()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setData()
    }

    private fun setData() {
        viewModel.checkForAuth()
        viewModel.liveData.observe(this, Observer { result ->
            when(result){
                is AuthViewModel.AuthResult.UserRegistered -> {
                    if(!result.isRegistered){
                        initRegistrationGraph()
                    }else{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, MainHostFragment())
                            .commit()
                    }
                }
            }
        })
    }

    override fun onBackPressed() {
        val hostFragment = supportFragmentManager.findFragmentByTag(MainHostFragment::class.java.simpleName)
        if (hostFragment.isFragmentAddedAndVisible() && hostFragment is FragmentNavigator) {
            hostFragment.onBackPressed()
        }else {
            super.onBackPressed()
        }
    }

    private fun initRegistrationGraph() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val myNavHostFragment: NavHostFragment = nav_host_fragment as NavHostFragment
        val inflater = myNavHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph_main)
        myNavHostFragment.navController.graph = graph
    }

}