package com.mo22sen.smsbackup.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.mo22sen.smsbackup.utils.EventObserver
import com.mo22sen.smsbackup.utils.FragmentOnBackPressed
import com.mo22sen.smsbackup.utils.NavigationCommand

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    abstract val viewModel: VM
    abstract val layoutRes: Int
    abstract val navigationId: Int

    val binding by lazy {
        DataBindingUtil.setContentView(this, layoutRes) as DB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        configEvents()
        bindObservables()
        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.navigationCommand.observe(this, EventObserver { command ->
            val navController = Navigation.findNavController(this, navigationId)
            when (command) {
                is NavigationCommand.To ->
                    navController.navigate(command.directions)
                is NavigationCommand.ToActionWithFinish -> {
                    navController.navigate(command.actionId)
                    finish()
                }
            }
        })
    }


    abstract fun configEvents()

    abstract fun bindObservables()

    abstract fun initBinding()

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onBackPressed() {
        val currentFragment = getCurrentFragment()
        if (currentFragment !is FragmentOnBackPressed) super.onBackPressed()
        (currentFragment as? FragmentOnBackPressed)?.onBackPressed()?.let {
            if (it) super.onBackPressed()
        }
    }

    private fun getCurrentFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.findFragmentById(navigationId)
        val currentFragments = navHostFragment?.childFragmentManager?.fragments
        return currentFragments?.let {
            if (it.isNotEmpty()) it[0]
            else null
        }
    }

}
