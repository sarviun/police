package com.nuivras.police

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RecordDetailFragment : Fragment() {

    companion object {
        fun newInstance() = RecordDetailFragment()
    }

    private lateinit var viewModel: RecordDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.record_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecordDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}