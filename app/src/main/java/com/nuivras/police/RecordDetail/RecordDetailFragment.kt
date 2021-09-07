package com.nuivras.police.RecordDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.nuivras.police.databinding.RecordDetailFragmentBinding

class RecordDetailFragment : Fragment() {

    private var _binding: RecordDetailFragmentBinding? = null
    private val binding get() = _binding!!

    val args: RecordDetailFragmentArgs by navArgs()

    /* not used */
    private val viewModel: RecordDetailViewModel by viewModels {
        RecordDetailViewModelFactory(args.crime, requireNotNull(activity).application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = RecordDetailFragmentBinding.inflate(inflater, container, false)
        //binding.viewModel = viewModel
        binding.crime = args.crime
        return binding.root
    }
}