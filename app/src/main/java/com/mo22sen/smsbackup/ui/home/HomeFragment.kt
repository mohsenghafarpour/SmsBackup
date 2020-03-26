package com.mo22sen.smsbackup.ui.home

import com.mo22sen.smsbackup.R
import com.mo22sen.smsbackup.base.BaseFragment
import com.mo22sen.smsbackup.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_home

    override fun configEvents() {
    }

    override fun bindObservables() {
    }

    override fun initBinding() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            executePendingBindings()
        }
    }
}