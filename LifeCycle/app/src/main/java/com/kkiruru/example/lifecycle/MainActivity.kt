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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kkiruru.example.lifecycle.ui.theme.LifeCycleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MainActivity", "onCreate : ${this.hashCode()}  ${savedInstanceState}")
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
                                text = "Main Activity",
                                modifier = Modifier,
                                fontSize = 20.sp
                            )

                            Spacer(modifier = Modifier.height(40.dp))

                            Button(
                                modifier = Modifier,
                                onClick = {
                                    val intent = Intent(this@MainActivity, AActivity::class.java).apply {
//                                        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                    }
                                    startActivity(intent)
                                }
                            ) {
                                Text(
                                    text = "A Activity",
                                    modifier = Modifier,
                                )
                            }
                            Button(
                                modifier = Modifier,
                                onClick = {
                                    OneActivity.startActivityNewTask(this@MainActivity)
                                }
                            ) {
                                Text(
                                    text = "One Activity new Task",
                                    modifier = Modifier,
                                )
                            }

                            Button(
                                modifier = Modifier,
                                onClick = {
//                                    OneActivity.startActivity(this@MainActivity)

                                    val intent = Intent(this@MainActivity, OneActivity::class.java).apply {
//                                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    }
                                    startActivity(intent)
                                }
                            ) {
                                Text(
                                    text = "One Activity",
                                    modifier = Modifier,
                                )
                            }

                            Button(
                                modifier = Modifier,
                                onClick = {
//                                    val intent = Intent(this@MainActivity, MainActivity::class.java).apply {
//                                        removeFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                        removeFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                                        removeFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                                    }
//                                    startActivity(intent)
//
                                    MainActivity.startActivity(this@MainActivity, "from Self")
                                }
                            ) {
                                Text(
                                    text = "Main Self",
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
        Log.e("MainActivity", "onStart ${this.hashCode()}")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("MainActivity", "onRestart ${this.hashCode()}")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onResume() {
        super.onResume()
        Log.e("MainActivity", "onResume ${this.hashCode()}")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putString(INTENT_KEY_TEST, intent.extras?.getString(INTENT_KEY_TEST))
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
        Log.e("MainActivity", "onStop ${this.hashCode()}")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onPause() {
        super.onPause()
        Log.e("MainActivity", "onPause ${this.hashCode()}")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("MainActivity", "onDestroy ${this.hashCode()}")
        Log.e("MainActivity", "__ intent ${intent.extras?.getString(INTENT_KEY_TEST)}")
    }

    companion object {
        private const val INTENT_KEY_TEST = "INTENT_KEY_TEST"

        fun startMainWithClearTop(context: Context, message: String) {
            val intent = Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                putExtras(
                    Bundle().apply {
                        putString(INTENT_KEY_TEST, message)
                    }
                )
            }
            context.startActivity(intent)
        }

        fun startActivity(context: Context, message: String) {
            val intent = Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
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
