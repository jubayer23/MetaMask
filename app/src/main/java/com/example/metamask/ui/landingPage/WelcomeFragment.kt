package com.example.metamask.ui.landingPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.metamask.R
import com.example.metamask.databinding.FragmentWelcomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome, container, false)


        binding.btnCreate.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_createFragmentWallet)
        )

        binding.btnImport.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_importWalletFragment)
        )
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            WelcomeFragment()
    }
}