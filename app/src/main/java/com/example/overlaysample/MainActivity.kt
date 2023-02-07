package com.example.overlaysample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var intentService: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intentService = Intent(application, TestService::class.java)

        val buttonStart: Button = findViewById(R.id.button_start)
        buttonStart.setOnClickListener {
            if (Settings.canDrawOverlays(this)) {
                // 許可されている
                startForegroundService(intentService)
            }
            else {
                // 許可されていない
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                startActivity(intent)
                // 設定画面に移行
                launcher.launch(intent)
            }
        }
    }

    private var launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        // 許可されたか再確認
        if (Settings.canDrawOverlays(this)) {
            // Serviceに跳ぶ
            startForegroundService(intentService)
        }
        else {
            //
            Toast.makeText(applicationContext, R.string.message, Toast.LENGTH_LONG).show()
        }
    }
}
