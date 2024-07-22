package com.kkiruru.example.lifecycle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.kkiruru.example.lifecycle.databinding.ActivityOneBinding

class OneActivity : ComponentActivity() {

    private var _binding: ActivityOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("OneActivity", "onCreate : ${savedInstanceState}")
        Log.e("OneActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
        enableEdgeToEdge()
        _binding = ActivityOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.moveToTwo.setOnClickListener {
            TwoActivity.startActivity(this, "from One")
        }

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        Log.e("OneActivity", "onNewIntent : ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onStart() {
        super.onStart()
        Log.e("OneActivity", "onStart")
        Log.e("OneActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("OneActivity", "onRestart")
        Log.e("OneActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onResume() {
        super.onResume()
        Log.e("OneActivity", "onResume")
        Log.e("OneActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("state", "life-cycle test")
        super.onSaveInstanceState(outState)
        Log.e("OneActivity", "onSaveInstanceState ${outState}")
        Log.e("OneActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("OneActivity", "onRestoreInstanceState ${savedInstanceState}")
        Log.e("OneActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onPause() {
        super.onPause()
        Log.e("OneActivity", "onPause")
        Log.e("OneActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onStop() {
        super.onStop()
        Log.e("OneActivity", "onPause")
        Log.e("OneActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("OneActivity", "onDestroy")
        Log.e("OneActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    companion object {
        private const val INTENT_KEY_TEST = "INTENT_KEY_TEST"
        fun startActivity(context: Context) {
            val intent = Intent(context, OneActivity::class.java).apply {
                putExtras(
                    Bundle().apply {
                        putString(INTENT_KEY_TEST, "addressEntryMode")
                    }
                )
            }
            context.startActivity(intent)
        }
    }
}
