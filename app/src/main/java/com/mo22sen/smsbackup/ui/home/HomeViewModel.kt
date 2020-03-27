package com.mo22sen.smsbackup.ui.home

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import com.mo22sen.smsbackup.base.BaseViewModel
import com.mo22sen.smsbackup.utils.ktx.logD

class HomeViewModel(private val application: Application) : BaseViewModel() {

    private lateinit var content: ContentResolver
    private lateinit var messageInbox: Cursor

    @SuppressLint("Recycle")
    fun getMessages() {
        content = application.contentResolver
        messageInbox =
            content.query(
                Uri.parse("content://sms/inbox"), null, null
                , null, null
            ) ?: return
        val indexBody: Int = messageInbox.getColumnIndex("body")
        val indexAddress: Int = messageInbox.getColumnIndex("address")
        if (indexBody < 0 || !messageInbox.moveToFirst()) return
        do {
            val str = """
            SMS From: ${messageInbox.getString(indexAddress)}
            ${messageInbox.getString(indexBody)}
            """.trimIndent()
            logD("xxx = $str")
        } while (messageInbox.moveToNext())
    }

}