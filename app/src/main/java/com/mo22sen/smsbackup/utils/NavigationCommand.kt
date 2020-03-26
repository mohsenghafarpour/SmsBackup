package com.mo22sen.smsbackup.utils

import androidx.navigation.NavDirections


sealed class NavigationCommand {
  data class To(val directions: NavDirections): NavigationCommand()
  data class ToActionWithFinish(val actionId: Int): NavigationCommand()
  object Back: NavigationCommand()

}