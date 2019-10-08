package kz.myfood.ui.home.todolist_types


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_history_details.*
import kotlinx.android.synthetic.main.fragment_history_to_do.*

import kz.myfood.R

/**
 * A simple [Fragment] subclass.
 */
class HistoryToDoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
    }

    private fun bindViews(view: View) {
        btnHistoryDetails.setOnClickListener {
            findNavController().navigate(R.id.goToHistoryDetails)
        }
    }


}
