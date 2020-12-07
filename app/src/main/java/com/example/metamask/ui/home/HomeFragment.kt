package com.example.metamask.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.metamask.R
import com.example.metamask.databinding.FragmentHomeBinding
import com.mindorks.retrofit.coroutines.data.api.ApiHelper
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.ui.base.ViewModelFactory
import com.mindorks.retrofit.coroutines.ui.main.viewmodel.HomeViewModel
import com.mindorks.retrofit.coroutines.utils.Status

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    private lateinit var viewModel: HomeViewModel

    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param1?.let { it1 -> Log.d("DEBUG", it1) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(
            inflater,
             R.layout.fragment_home, container, false)

        setupViewModel()

        setupObservers()

        return binding.root
    }


    private fun setupViewModel(){
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(HomeViewModel::class.java)
    }

    private fun setupObservers() {
        param1?.let {
            viewModel.getBalance(it).observe(this, Observer {
                it?.let { resource ->
                    Log.d("DEBUG", resource.status.toString())
                    when (resource.status) {
                        Status.SUCCESS -> {
                            //recyclerView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            resource.data?.let { seed -> Log.d("DEBUG", seed.toString())

                                binding.tvBalance.setText("Balance: " + seed.account_balance)
                                binding.tvAccount.setText("Account: " + seed.account_address)

                                //view?.findNavController()?.navigate(CreateWalletFragmentDirections.actionCreateFragmentWalletToVerificationFragment(seed.seed_phrase, seed.account_address))
                            }


                        }
                        Status.ERROR -> {
                            //recyclerView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            //Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                            // recyclerView.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}