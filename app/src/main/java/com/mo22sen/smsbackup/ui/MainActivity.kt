package com.mo22sen.smsbackup.ui

import android.Manifest.permission.READ_SMS
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mo22sen.smsbackup.R
import com.mo22sen.smsbackup.base.BaseActivity
import com.mo22sen.smsbackup.databinding.ActivityMainBinding
import com.mo22sen.smsbackup.utils.ktx.logD
import org.koin.androidx.viewmodel.ext.android.viewModel

const val SMS_PERMISSION = 1

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModel()

    override val layoutRes: Int = R.layout.activity_main

    override val navigationId: Int = R.id.main_navigation

    override fun configEvents() {

        if (ContextCompat.checkSelfPermission(
                this@MainActivity, READ_SMS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            logD("permission is ok!!!")
        } else {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(READ_SMS), SMS_PERMISSION
            )
        }

    }

    override fun bindObservables() {
    }

    override fun initBinding() {
        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
            executePendingBindings()
        }
    }
}
