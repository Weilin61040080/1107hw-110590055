package com.example.lab4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // 宣告 ActivityResultLauncher，用於處理 SecActivity 回傳結果
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        // 檢查回傳結果
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { intent ->
                val drink = intent.getStringExtra("drink")
                val sugar = intent.getStringExtra("sugar")
                val ice = intent.getStringExtra("ice")
                
                findViewById<TextView>(R.id.tvMeal).text = 
                    "飲料：$drink\n\n甜度：$sugar\n\n冰塊：$ice"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 定義按鈕，並設定點擊事件以啟動 SecActivity
        findViewById<Button>(R.id.btnChoice).setOnClickListener {
            Intent(this, SecActivity::class.java).also { startForResult.launch(it) }
        }
    }
}
