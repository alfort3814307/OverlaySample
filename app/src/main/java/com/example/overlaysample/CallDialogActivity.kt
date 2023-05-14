package com.example.overlaysample

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

class CallDialogActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_dialog)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select a direction")
        builder.setMessage("メッセージ")
        builder.setPositiveButton("OK", { dialog, which ->
            //
        })
        builder.create()
    }
}