package com.example.metamask.ui.seedVerification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.metamask.R
import com.example.metamask.VerificationFragmentArgs
import com.example.metamask.VerificationFragmentDirections
import com.example.metamask.databinding.FragmentVerificationBinding


/**
 * A simple [Fragment] subclass.
 * Use the [VerificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VerificationFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentVerificationBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_verification, container, false)

        val args =
            VerificationFragmentArgs.fromBundle(arguments!!)

        binding.tvSeed.setText(args.phrase)
        //Toast.makeText(context, "NumCorrect: ${args.phrase}", Toast.LENGTH_LONG).show()


        binding.btnLogin.setOnClickListener{

            view?.findNavController()?.navigate(
                VerificationFragmentDirections.actionVerificationFragmentToHomeActivity(
                    args.account
                )
            )
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            VerificationFragment()
    }
}