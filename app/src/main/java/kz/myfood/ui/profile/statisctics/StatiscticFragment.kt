package kz.myfood.ui.profile.statisctics


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController

import kz.myfood.R
import kz.myfood.model.Constants.PROFILE_STATISCTIC
import kz.myfood.model.StatisticsInfo

/**
 * A simple [Fragment] subclass.
 */
class StatiscticFragment : Fragment() {

    private lateinit var tvNumberOfPerformances: TextView
    private lateinit var btnDetails: TextView

    private var statisctic: StatisticsInfo? = null

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        statisctic = args?.getParcelable(PROFILE_STATISCTIC)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statisctic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setData()
    }

    private fun bindViews(view: View) {
        tvNumberOfPerformances = view.findViewById(R.id.tvNumberOfPerformances)
        btnDetails = view.findViewById(R.id.btnDetails)

        btnDetails.setOnClickListener {

            val bundle = Bundle().apply {
                putString("FIELD_TYPE", statisctic?.field_type)
            }
            findNavController().navigate(R.id.action_details_statistic, bundle)
        }
    }

    private fun setData() {
        tvNumberOfPerformances.setText(statisctic?.numberOfExcellentPerfomances.toString())
    }

}
