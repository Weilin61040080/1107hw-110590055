package com.example.lab6

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val items = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupWindowInsets()

        findViewById<Button>(R.id.btnToast).setOnClickListener {
            showToast("預設 Toast")
        }

        findViewById<Button>(R.id.btnSnackBar).setOnClickListener { view ->
            Snackbar.make(view, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("按鈕") { showToast("已回應") }
                .show()
        }

        findViewById<Button>(R.id.btnDialog1).setOnClickListener {
            showButtonAlertDialog()
        }

        findViewById<Button>(R.id.btnDialog2).setOnClickListener {
            showListAlertDialog()
        }

        findViewById<Button>(R.id.btnDialog3).setOnClickListener {
            showSingleChoiceAlertDialog()
        }
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showButtonAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("按鈕式 AlertDialog")
            .setMessage("AlertDialog 內容")
            .setNeutralButton("左按鈕") { _, _ -> showToast("左按鈕") }
            .setNegativeButton("中按鈕") { _, _ -> showToast("中按鈕") }
            .setPositiveButton("右按鈕") { _, _ -> showToast("右按鈕") }
            .show()
    }

    private fun showListAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("列表式 AlertDialog")
            .setItems(items) { _, i -> showToast("你選的是${items[i]}") }
            .show()
    }

    private fun showSingleChoiceAlertDialog() {
        var selectedPosition = 0
        AlertDialog.Builder(this)
            .setTitle("單選式 AlertDialog")
            .setSingleChoiceItems(items, 0) { _, i -> selectedPosition = i }
            .setPositiveButton("確定") { _, _ -> showToast("你選的是${items[selectedPosition]}") }
            .show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
