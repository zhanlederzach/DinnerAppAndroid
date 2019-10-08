package kz.myfood.ui.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login_validation.*
import kz.myfood.R
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class LoginValidationFragment : Fragment() {

    private val viewModel: AuthViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_validation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        btnDone.setOnClickListener {
            viewModel.registerUser()
            findNavController().navigate(R.id.action_go_to_home_fragment)
        }
    }

}
