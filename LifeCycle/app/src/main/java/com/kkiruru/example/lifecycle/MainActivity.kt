package com.kkiruru.example.lifecycle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kkiruru.example.lifecycle.ui.theme.LifeCycleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MainActivity", "onCreate : ${savedInstanceState}")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")

        enableEdgeToEdge()
        setContent {
            LifeCycleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(innerPadding),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = "Main",
                                modifier = Modifier,
                            )

                            Button(
                                modifier = Modifier,
                                onClick = {
                                    startActivity(
                                        Intent(this@MainActivity, SecondActivity::class.java)
                                    )
                                }
                            ) {
                                Text(
                                    text = "Dialog Activity",
                                    modifier = Modifier,
                                )
                            }
                            Button(
                                modifier = Modifier,
                                onClick = {
                                    startActivity(
                                        Intent(this@MainActivity, OneActivity::class.java)
                                    )
                                }
                            ) {
                                Text(
                                    text = "One Activity",
                                    modifier = Modifier,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        Log.e("MainActivity", "onNewIntent : ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onStart() {
        super.onStart()
        Log.e("MainActivity", "onStart")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("MainActivity", "onRestart")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onResume() {
        super.onResume()
        Log.e("MainActivity", "onResume")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(INTENT_KEY_TEST, intent.extras?.getString(INTENT_KEY_TEST))
        super.onSaveInstanceState(outState)
        Log.e("MainActivity", "onSaveInstanceState ${outState}")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("MainActivity", "onRestoreInstanceState ${savedInstanceState}")
        savedInstanceState.getString(INTENT_KEY_TEST)?.let {
            intent.apply {
                putExtras(
                    Bundle().apply {
                        putString(INTENT_KEY_TEST, it)
                    }
                )
            }
        }

        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onStop() {
        super.onStop()
        Log.e("MainActivity", "onStop")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onPause() {
        super.onPause()
        Log.e("MainActivity", "onPause")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("MainActivity", "onDestroy")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    companion object {
        private const val INTENT_KEY_TEST = "INTENT_KEY_TEST"
        fun startMainWithClearTop(context: Context, message: String) {
            val intent = Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                putExtras(
                    Bundle().apply {
                        putString(INTENT_KEY_TEST, message)
                    }
                )
            }
            context.startActivity(intent)
        }

        fun startMain(context: Context, message: String) {
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtras(
                    Bundle().apply {
                        putString(INTENT_KEY_TEST, message)
                    }
                )
            }
            context.startActivity(intent)
        }
    }
}
