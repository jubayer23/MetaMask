package com.example.metamask.ui.importWallet

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
import com.example.metamask.R
import com.example.metamask.databinding.FragmentImportWalletBinding
import com.mindorks.retrofit.coroutines.data.api.ApiHelper
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.ui.base.ViewModelFactory
import com.mindorks.retrofit.coroutines.ui.main.viewmodel.ImportWalletViewModel
import com.mindorks.retrofit.coroutines.utils.Status


/**
 * A simple [Fragment] subclass.
 * Use the [ImportWalletFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImportWalletFragment : Fragment() {

    private lateinit var viewModel: ImportWalletViewModel

    lateinit var  binding: FragmentImportWalletBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(
            inflater,
             R.layout.fragment_import_wallet, container, false)

        setupViewModel()

        binding.btnLogin.setOnClickListener{
            setupObservers()
        }

        return binding.root
    }

    private fun setupViewModel(){
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(ImportWalletViewModel::class.java)
    }

    private fun setupObservers() {

            viewModel.getAccount(binding.edSeed.text.toString()).observe(this, Observer {
                it?.let { resource ->
                    Log.d("DEBUG", resource.status.toString())
                    when (resource.status) {
                        Status.SUCCESS -> {
                            //recyclerView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE

                            resource.data?.let { seed -> Log.d("DEBUG", seed.toString())

                                view?.findNavController()?.navigate(
                                    ImportWalletFragmentDirections.actionImportWalletFragmentToHomeActivity(
                                        seed.account_address
                                    )
                                )

                               // binding.tvBalance.setText("Account Balance: " + seed.account_balance)

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

    companion object {
        @JvmStatic
        fun newInstance() =
            ImportWalletFragment()
    }
}