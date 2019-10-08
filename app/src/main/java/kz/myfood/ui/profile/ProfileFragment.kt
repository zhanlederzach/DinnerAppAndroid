package kz.myfood.ui.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kz.myfood.R
import kz.myfood.model.Constants.PROFILE_STATISCTIC
import kz.myfood.model.ProfileInfo
import kz.myfood.ui.login.AuthViewModel
import org.koin.android.ext.android.inject

/**
 * created by Zhanel 26.09.2019
 */

class ProfileFragment : Fragment() {

    private lateinit var btnLogout: Button
    private lateinit var tvNameProfile: TextView
    private lateinit var btnStatisctic: TextView
    private lateinit var btnAboutApp: TextView

    private var profile: ProfileInfo? = null

    private val authViewModel: AuthViewModel by inject()
    private val viewModel: ProfileViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setData()
    }

    private fun bindViews(view: View) {
        tvNameProfile = view.findViewById(R.id.tvNameProfile)
        btnStatisctic = view.findViewById(R.id.btnStatisctic)
        btnAboutApp = view.findViewById(R.id.btnAboutApp)

        btnLogout = view.findViewById(R.id.btnLogout)

        btnLogout.setOnClickListener {
            authViewModel.unregisterUser()
            activity?.finish()
            findNavController().navigate(R.id.logout)
        }

        btnStatisctic.setOnClickListener {
            val bundle = Bundle().apply {
                putParcelable(PROFILE_STATISCTIC, profile?.statisctics)
            }
            findNavController().navigate(R.id.action_profile_statistics, bundle)
        }

        btnAboutApp.setOnClickListener {
            findNavController().navigate(R.id.action_about_app)
        }
    }

    private fun setData() {
        viewModel.getProfileInfo()
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            when(it) {
                is ProfileViewModel.ProfileData.ShowLoading -> { }
                is ProfileViewModel.ProfileData.HideLoading -> { }
                is ProfileViewModel.ProfileData.Result -> {
                    profile = it.profileInfo
                    tvNameProfile.setText(it.profileInfo.name)
                }
                is ProfileViewModel.ProfileData.Error -> { }
            }
        })
    }

}
