package com.example.metamask.ui.createWallet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.metamask.CreateWalletFragmentDirections
import com.example.metamask.R
import com.example.metamask.databinding.FragmentCreateWalletBinding
import com.mindorks.retrofit.coroutines.data.api.ApiHelper
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.ui.base.ViewModelFactory
import com.mindorks.retrofit.coroutines.ui.main.viewmodel.CreateWalletViewModel
import com.mindorks.retrofit.coroutines.utils.Status
import kotlinx.android.synthetic.main.fragment_create_wallet.*


/**
 * A simple [Fragment] subclass.
 * Use the [CreateWalletFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateWalletFragment : Fragment() {

    private lateinit var viewModel: CreateWalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCreateWalletBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create_wallet, container, false)

        //val s = "hellow i am here"
        binding.btnLogin.setOnClickListener{view ->
            setupObservers()
            //view?.findNavController()?.navigate(CreateWalletFragmentDirections.actionCreateFragmentWalletToVerificationFragment(s))
        }

        setupViewModel()


        return binding.root
    }

    private fun setupViewModel(){

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(CreateWalletViewModel::class.java)

    }

    private fun setupObservers() {
        viewModel.getSeed().observe(this, Observer {
            it?.let { resource ->
                Log.d("DEBUG", resource.status.toString())
                when (resource.status) {
                    Status.SUCCESS -> {
                        //recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { seed -> Log.d("DEBUG", seed.toString())


                            view?.findNavController()?.navigate(
                                CreateWalletFragmentDirections.actionCreateFragmentWalletToVerificationFragment(
                                    seed.seed_phrase,
                                    seed.account_address
                                )
                            )
                        }


                    }
                    Status.ERROR -> {
                        //recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        //Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                       // recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CreateWalletFragment()
    }
}