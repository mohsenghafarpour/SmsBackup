package com.mo22sen.smsbackup.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.mo22sen.smsbackup.utils.Event
import com.mo22sen.smsbackup.utils.NavigationCommand

abstract class BaseViewModel : ViewModel() {

    val navigationCommand = MutableLiveData<Event<NavigationCommand>>()

    fun navigateTo(directions: NavDirections) {
        navigationCommand.postValue(Event(NavigationCommand.To(directions)))
    }

    fun navigateToWithFinish(actionId: Int) {
        navigationCommand.postValue(Event(NavigationCommand.ToActionWithFinish(actionId)))
    }

    fun navigateBack() {
        navigationCommand.postValue(Event(NavigationCommand.Back))
    }


}