package com.kkiruru.example.lifecycle


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.kkiruru.example.lifecycle.MainActivity.Companion
import com.kkiruru.example.lifecycle.databinding.ActivityTwoBinding

class TwoActivity : ComponentActivity() {

    private var _binding: ActivityTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TwoActivity", "onCreate : savedInstanceState ${savedInstanceState}")
        intent?.let {
            Log.e("TwoActivity", "__ intent ${it.extras?.getString(INTENT_KEY_TEST)}")
        }
        enableEdgeToEdge()
        _binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.moveToThree.setOnClickListener {
            ThreeActivity.startActivity(this, "from Two")
        }

        binding.moveToMain.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java).apply {
//                setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//            }
//            startActivity(intent)
            MainActivity.startActivity(this@TwoActivity, "from Two")
        }

        binding.moveToA.setOnClickListener {
            val intent = Intent(this, AActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }

        binding.moveToOne.setOnClickListener {
            val intent = Intent(this, OneActivity::class.java).apply {
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        Log.e("TwoActivity", "onNewIntent : ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onStart() {
        super.onStart()
        Log.e("TwoActivity", "onStart")
        intent?.let {
            Log.e("TwoActivity", "__ intent ${it.extras?.getString(INTENT_KEY_TEST)}")
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("TwoActivity", "onRestart : intent ${intent?.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onResume() {
        super.onResume()
        Log.e("TwoActivity", "onResume : intent ${intent?.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("state", "life-cycle test")
        super.onSaveInstanceState(outState)
        Log.e("TwoActivity", "onSaveInstanceState ${outState}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("TwoActivity", "onRestoreInstanceState ${savedInstanceState}")
    }

    override fun onPause() {
        super.onPause()
        Log.e("TwoActivity", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("TwoActivity", "onDestroy")
    }

    companion object {
        private const val INTENT_KEY_TEST = "INTENT_KEY_TEST"
        fun startActivity(context: Context, mode: String = "two Activity") {
            val intent = Intent(context, TwoActivity::class.java).apply {
                putExtras(
                    Bundle().apply {
                        putString(INTENT_KEY_TEST, mode)
                    }
                )
            }
            context.startActivity(intent)
        }
    }
}
