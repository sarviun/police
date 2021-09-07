package com.nuivras.police.RecordList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nuivras.police.R
import com.nuivras.police.StreetLevelCrime
import com.nuivras.police.databinding.RecordListFragmentBinding

class RecordListFragment : Fragment() {

    private val viewModel: RecordListViewModel by lazy {
        ViewModelProvider(this).get(RecordListViewModel::class.java)
    }

    private var _binding: RecordListFragmentBinding? = null
    private val binding get() = _binding!!

    val args: RecordListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RecordListFragmentBinding.inflate(inflater, container, false)

        binding.coordinatesTextView.text = args.coordinates
        binding.listView.adapter = ResultListAdapter(ResultListAdapter.OnClickListener {
                crime: StreetLevelCrime, view: View ->

            /** NAVIGATE TO CRIME **/
            val action = RecordListFragmentDirections.actionRecordListFragmentToRecordDetailFragment(crime)
            this.findNavController().navigate(action)
        })

        viewModel.properties.observe(viewLifecycleOwner, Observer {
            val adapter = binding.listView.adapter as ResultListAdapter
            adapter.submitList(it)
            binding.infoTextView.text = getString(R.string.incidents_found_number, it.size)
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            when (it) {
                RecordListViewModel.PoliceApiStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val coordinates = getCoordinatesFromArgs(args.coordinates)
        viewModel.getStreetLevelCrimes(coordinates[0], coordinates[1])
    }


    private fun getCoordinatesFromArgs(coordinates: String): List<String> {
        return coordinates.split(",")
    }
}