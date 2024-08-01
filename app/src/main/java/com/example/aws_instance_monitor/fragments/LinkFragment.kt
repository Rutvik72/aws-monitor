package com.example.aws_instance_monitor.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.aws_instance_monitor.R
import com.example.aws_instance_monitor.databinding.FragmentLinkBinding


class LinkFragment : Fragment() {

    private var link: String? = null
    private lateinit var linkBinding: FragmentLinkBinding
    private val binding get() = linkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        linkBinding = FragmentLinkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(isLinkAvailable())
            findNavController().navigate(R.id.action_linkFragment_to_homeFragment)

        binding.acceptButton.setOnClickListener {
            val apiLink = binding.linkEditText.text.toString()

            if(apiLink.isNullOrBlank())
                binding.inputTextLayout.error = "Please input the api link to proceed"
            else {
                onLinkEntered(apiLink)
                findNavController().navigate(R.id.action_linkFragment_to_homeFragment)
            }
        }

        binding.exitButton.setOnClickListener {
            requireActivity().finishAffinity()
        }
    }

    private fun onLinkEntered(api:String) {
        val sharedPreferences =
            requireActivity().getSharedPreferences("apiLink", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("api", api)
        editor.apply()
    }

    private fun isLinkAvailable():Boolean {
        val sharedPreferences =
            requireActivity().getSharedPreferences("apiLink", Context.MODE_PRIVATE)
        val api = sharedPreferences.getString("api", "")
        link = api
        return !api.isNullOrBlank()
    }
}