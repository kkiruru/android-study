package com.kkiruru.example.lifecycle


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.kkiruru.example.lifecycle.databinding.ActivityThreeBinding

class ThreeActivity : ComponentActivity() {

    private var _binding: ActivityThreeBinding? = null
    private val binding get() = _binding!!

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("ThreeActivity", "onCreate : ${savedInstanceState}")
        enableEdgeToEdge()
        _binding = ActivityThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startOne.setOnClickListener {
            startActivity(
                Intent(this@ThreeActivity, OneActivity::class.java)
            )
        }

        binding.startTwo.setOnClickListener {
            TwoActivity.startActivity(this@ThreeActivity, "from Three")
        }

        binding.startThree.setOnClickListener {
            ThreeActivity.startActivity(this@ThreeActivity, "self")
        }
        Log.e("ThreeActivity", "__  intent.getInt(INTENT_KEY_COUNT) ${intent.extras?.getInt(INTENT_KEY_COUNT, -1)}")
        val foo = intent.extras?.getInt(INTENT_KEY_COUNT, -1)
        foo?.let {
            if (foo != -1) {
                count = foo
            }
        }
        Log.e("ThreeActivity", "__ count : ${count}")
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        count++
        Log.e("ThreeActivity", "onNewIntent : ${intent.extras?.getString(INTENT_KEY_TEST)}")
        Log.e("ThreeActivity", "__ count = ${count}")
    }

    override fun onStart() {
        super.onStart()
        Log.e("ThreeActivity", "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("ThreeActivity", "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("ThreeActivity", "onResume")
        intent?.let {
            Log.e("ThreeActivity", "__ intent ${it.extras?.getString(INTENT_KEY_TEST)}")
            Log.e("ThreeActivity", "__ intent ${it.extras?.getInt(INTENT_KEY_COUNT)}")
        }

        Log.e("ThreeActivity", "__ count = ${count}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(INTENT_KEY_COUNT, count)
        super.onSaveInstanceState(outState)
        Log.e("ThreeActivity", "onSaveInstanceState ${outState}")
        Log.e("ThreeActivity", "__  outState.putInt(INTENT_KEY_COUNT} ${count}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("ThreeActivity", "onRestoreInstanceState ${savedInstanceState}")
        val foo = savedInstanceState.getInt(INTENT_KEY_COUNT, -1)
        if (foo != -1) {
            Log.e("ThreeActivity", "__  savedInstanceState.getInt(INTENT_KEY_COUNT} ${foo}")
            count = foo
        }
    }

    override fun onPause() {
        super.onPause()
        Log.e("ThreeActivity", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("ThreeActivity", "onDestroy")
    }

    companion object {
        private const val INTENT_KEY_TEST = "INTENT_KEY_TEST"
        private const val INTENT_KEY_COUNT = "INTENT_KEY_COUNT"
        fun startActivity(context: Context, mode: String = "addressEntryMode") {
            val intent = Intent(context, ThreeActivity::class.java).apply {
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
